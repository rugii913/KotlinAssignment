package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class InputMessenger(private val outputMessenger: OutputMessenger) {

    private val intInputFilter = IntInputFilter()

    fun readInt(): Pair<InputStatus, Int> = print(">>> ").run {
        val selectedNumber: Int

        try {
            selectedNumber = intInputFilter.filterToInt(readln())
        } catch (e: NumberFormatException) {
            outputMessenger.writeExceptionWarningIntRequired()
            return Pair(InputStatus.FAIL_INT_REQUIRED, Int.MIN_VALUE)
        }

        return Pair(InputStatus.SUCCESS, selectedNumber)
    }

    fun readInt(endInt: Int): Pair<InputStatus, Int> = print(">>> ").run {
        val selectedNumber: Int

        try {
            selectedNumber = intInputFilter.filterToInt(readln())
        } catch (e: NumberFormatException) {
            outputMessenger.writeExceptionWarningIntRequired()
            return Pair(InputStatus.FAIL_INT_REQUIRED, Int.MIN_VALUE)
        }

        if (selectedNumber > endInt || selectedNumber < 0) {
            outputMessenger.writeWarningIntOutOfRange()
            return Pair(InputStatus.FAIL_INT_OUT_OF_RANGE, selectedNumber)
        } else {
            return Pair(InputStatus.SUCCESS, selectedNumber)
        }
    }

    enum class InputStatus {
        SUCCESS, FAIL_INT_REQUIRED, FAIL_INT_OUT_OF_RANGE
    }
}
