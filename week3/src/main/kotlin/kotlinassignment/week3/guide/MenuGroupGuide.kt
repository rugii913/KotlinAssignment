package kotlinassignment.week3.guide

import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.MenuGroupMessenger
import kotlinassignment.week3.messenger.Message

class MenuGroupGuide: Guide {
    private val menuGroupMessenger = MenuGroupMessenger()

    override fun guide(continueState: ContinueState) {
        // 메뉴 출력에 대한 부분
        val menuGroupEntries = MenuGroup.entries
        menuGroupMessenger.writeMenuGroup(menuGroupEntries)
        
        // 사용자의 입력 처리에 대한 부분
        val selectedNumber: Int
        try {
            selectedNumber = InputMessenger().readInt()
        } catch (e: NumberFormatException) {
            menuGroupMessenger.writeExceptionWarningIntRequired()
            return // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return
            // TODO 공통 처리할 수 있는 방법 없을지 고민
        }

        if (selectedNumber == 0) {
            menuGroupMessenger.write(Message.EXIT)
            continueState.nextGuide = null
        } else if (selectedNumber <= menuGroupEntries.size) {
            continueState.nextGuide = MenuItemGuide()
            continueState.nextMenuGroup = menuGroupEntries[selectedNumber - 1]
        } else {
            menuGroupMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
        }
    }
}
