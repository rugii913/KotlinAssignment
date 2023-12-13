package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class InputMessenger {

    private val intInputFilter = IntInputFilter()
    private val menuItemMessenger = MenuItemMessenger()

    fun readInt(): Pair<InputStatus, Int> = print(">>> ").run {
        val selectedNumber: Int

        try {
            selectedNumber = intInputFilter.filterToInt(readln())
        } catch (e: NumberFormatException) {
            menuItemMessenger.writeExceptionWarningIntRequired()
            return Pair(InputStatus.ABNORMAL, Int.MIN_VALUE)
        }

        return Pair(InputStatus.NORMAL, selectedNumber)
    }

    enum class InputStatus {
        NORMAL, ABNORMAL
    }
}
