package kotlinassignment.week3.guide

import kotlinassignment.week3.KioskMain
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.ContinueState
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
        val selectedNumber = InputMessenger().readInt()

        if (selectedNumber == null) {
            menuGroupMessenger.write(Message.NOT_INT_INPUT)
            // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
        } else if (selectedNumber == 0) {
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
