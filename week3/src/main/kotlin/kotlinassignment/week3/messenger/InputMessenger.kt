package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class InputMessenger {

    private val intInputFilter = IntInputFilter()
    private val outputMessenger = OutputMessenger()

    fun readInt(): Pair<InputStatus, Int> = print(">>> ").run {
        val selectedNumber: Int

        try {
            selectedNumber = intInputFilter.filterToInt(readln())
        } catch (e: NumberFormatException) {
            outputMessenger.writeExceptionWarningIntRequired()
            return Pair(InputStatus.ABNORMAL, Int.MIN_VALUE)
        }

        return Pair(InputStatus.NORMAL, selectedNumber)
    }

    enum class InputStatus {
        NORMAL, ABNORMAL
    }
}
