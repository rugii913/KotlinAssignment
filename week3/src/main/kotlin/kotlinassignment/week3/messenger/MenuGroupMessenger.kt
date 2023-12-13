package kotlinassignment.week3.messenger

class MenuGroupMessenger {

    fun writeMenu() {
        println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        println("\n[SHAKESHACK MENU]")
        println("1. Burgers\t\t\t| 앵거스 비프 통살을 다져만든 버거")
        println("2. Frozen Custard\t| 매장에서 신선하게 만드는 아이스크림")
        println("0. 종료\t\t\t\t| 프로그램 종료")
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }
}