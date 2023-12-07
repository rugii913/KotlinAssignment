package guide

import kotlin.reflect.KCallable

object GuideMessages {
    const val ALERT_MESSAGE_NOT_A_NUMBER = "\n잘못된 입력입니다. 숫자를 입력해주세요."
    const val ALERT_MESSAGE_WRONG_NUMBER = "\n잘못된 입력입니다. 서비스의 번호를 확인해주세요."

    const val MENU_EXIT = "원하는 서비스가 없습니다."
    const val MENU_CALCULATOR = "계산기"
    const val MENU_HOTEL = "호텔"

    var menuList: MutableList<Pair<String, KCallable<Unit>>> = mutableListOf()

    fun menuInit() {
        // menuList.add(Pair(메뉴 메시지, 호출할 함수 참조))
        menuList.add(Pair(MENU_EXIT, MainGuide::exit))
        menuList.add(Pair(MENU_CALCULATOR, CalculatorGuide::guide))
    }
}