package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class InputMessenger(private val outputMessenger: OutputMessenger) {

    private val intInputFilter = IntInputFilter()

    private fun readInt(): Pair<InputStatus, Int> = print(">>> ").run {
        val selectedNumber: Int

        try {
            selectedNumber = intInputFilter.filterToInt(readln())
        } catch (e: NumberFormatException) {
            outputMessenger.writeExceptionWarningIntRequired()
            return Pair(InputStatus.FAIL_INT_REQUIRED, Int.MIN_VALUE)
        }

        return Pair(InputStatus.SUCCESS, selectedNumber)
    }

    fun readInt(endInt: Int): Pair<InputStatus, Int> {
        var (inputStatus, selectedNumber) = readInt()

        if (selectedNumber > endInt || selectedNumber < 0) {
            outputMessenger.writeWarningIntOutOfRange()
            inputStatus = InputStatus.FAIL_INT_OUT_OF_RANGE
        }

        return Pair(inputStatus, selectedNumber)
    }

    enum class InputStatus {
        SUCCESS, FAIL_INT_REQUIRED, FAIL_INT_OUT_OF_RANGE
    }
}
