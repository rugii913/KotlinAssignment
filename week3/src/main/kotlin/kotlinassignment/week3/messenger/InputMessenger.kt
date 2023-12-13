package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class InputMessenger {

    private val intInputFilter = IntInputFilter()

    fun readInt() = print(">>> ").run { intInputFilter.filterToInt(readln()) }
}