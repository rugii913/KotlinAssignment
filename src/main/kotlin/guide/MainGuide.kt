package guide

object MainGuide {
    private var continueFlag = true

    fun guide() {
        var inputString: String

        while (continueFlag) {
            print(
                """
            |원하시는 서비스의 숫자를 입력해주세요.
            |${SERVICE_NUMBER_MENU_EXIT}. $MENU_EXIT
            |${SERVICE_NUMBER_MENU_CALCULATOR}. $MENU_CALCULATOR
            |${SERVICE_NUMBER_MENU_HOTEL}. $MENU_HOTEL
            |>>> 
            """.trimMargin()
            )

            inputString = readln()
            val input = numberInputFilter(inputString)?: continue // 숫자가 아닐 경우 다시 while 앞으로

            when (input) {
                0 -> break
                1 -> CalculatorGuide.guide()
            }
        }
    }

    private fun numberInputFilter(inputString: String): Int? {
        val input = inputString.toIntOrNull()
        if (input == null) println(ALERT_MESSAGE_NOT_A_NUMBER)

        return input
    }
}
