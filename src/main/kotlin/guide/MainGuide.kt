package guide

object MainGuide {
    private var continueFlag = true

    fun guide() {
        var inputString: String

        GuideMessages.menuInit()

        while (continueFlag) {
            println("원하시는 서비스의 숫자를 입력해주세요.")
            for (i in 0..<GuideMessages.menuList.size) {
                println("$i. ${GuideMessages.menuList[i].first}")
            }
            print(">>> ")

            inputString = readln()
            val input = numberInputFilter(inputString)?: continue // 숫자가 아닐 경우 다시 while 앞으로

            if (input < GuideMessages.menuList.size) {
                GuideMessages.menuList[input].second.call()
            } else {
                println(GuideMessages.ALERT_MESSAGE_WRONG_NUMBER)
            }
        }
    }

    private fun numberInputFilter(inputString: String): Int? {
        val input = inputString.toIntOrNull()
        if (input == null) println(GuideMessages.ALERT_MESSAGE_NOT_A_NUMBER)

        return input
    }

    internal fun exit() {
        continueFlag = false
        println("프로그램을 종료합니다.")
    }
}
