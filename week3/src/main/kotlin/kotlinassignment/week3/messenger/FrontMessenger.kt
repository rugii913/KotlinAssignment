package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class FrontMessenger {

    private val intInputFilter = IntInputFilter()

    fun write() {
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n")
        println("[SHAKESHACK MENU]")
        println("1. Burgers\t\t| 앵거스 비프 통살을 다져만든 버거")
        println("0. 종료\t\t\t| 프로그램 종료")
    }

    fun read() = print(">>> ").run { readln() }

    fun readInt() = intInputFilter.filterToInt(read())
}