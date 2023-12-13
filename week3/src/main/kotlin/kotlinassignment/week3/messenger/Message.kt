package kotlinassignment.week3.messenger

enum class Message(val messegeValue: String) {
    EXIT("\n프로그램을 종료하는 중이에요."),
    NOT_INT_INPUT("\n잘못 입력했어요. 숫자를 입력해주세요."),
    NO_CORRESPONDING_SERVICE_NUMBER("\n잘못된 번호를 입력했어요. 다시 입력해주세요."),
    MENU_MAIN_INTRODUCE("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n\n[SHAKESHACK MENU]"),
    MENU_GROUP_BURGERS_INTRODUCE("\n[Burgers MENU]"),
    MENU_GROUP_FROZEN_CUSTARD_INTRODUCE("\n[Frozen Custard MENU]"),
    MENU_EXIT("0. ${String.format("%-20s", "종료")} | 프로그램 종료"),
    MENU_BACK("0. ${String.format("%-20s", "뒤로가기")} | 뒤로가기");
}
