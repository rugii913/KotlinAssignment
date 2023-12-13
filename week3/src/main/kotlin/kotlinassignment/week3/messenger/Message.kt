package kotlinassignment.week3.messenger

enum class Message(val messegeValue: String) {
    EXIT("\n프로그램을 종료하는 중이에요."),
    NOT_INT_INPUT("\n잘못 입력했어요. 숫자를 입력해주세요."),
    NO_CORRESPONDING_SERVICE_NUMBER("\n잘못된 번호를 입력했어요. 다시 입력해주세요.");
}
