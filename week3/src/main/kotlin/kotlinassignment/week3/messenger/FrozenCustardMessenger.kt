package kotlinassignment.week3.messenger

class FrozenCustardMessenger {

    fun writeMenu() {
        println("\n[FrozenCustard MENU]")
        println("1. 준비 중입니다. - 매장에서 만든 아이스크림")
        println("0. 뒤로가기\t\t\t| 뒤로가기")
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }
}
