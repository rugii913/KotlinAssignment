package kotlinassignment.week3.messenger

import kotlinassignment.filter.IntInputFilter

class BurgersMessenger {

    private val intInputFilter = IntInputFilter()

    fun write() {
        println("\n[Burgers MENU]")
        println("1. ShackBurger\t\t| W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거")
        println("2. SmokeShack\t\t| W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거")
        println(".. ..\t\t| .. | ..")
        println("0. 뒤로가기\t\t\t| 뒤로가기")
    }

    fun read() = print(">>> ").run { readln() }

    fun readInt() = intInputFilter.filterToInt(read())
}