package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class FrozenCustardMessenger {

    private val intInputFilter = IntInputFilter()

    fun write() {
        println("\n[FrozenCustard MENU]")
        println("1. 준비 중입니다. - 매장에서 만든 아이스크림")
        println("0. 뒤로가기\t\t\t| 뒤로가기")
    }

    fun read() = print(">>> ").run { readln() }

    fun readInt() = intInputFilter.filterToInt(read())
}