package kotlinassignment.week3.guide

import kotlinassignment.week3.KioskMain
import kotlinassignment.week3.menu.menuGroup.Burgers
import kotlinassignment.week3.menu.menuGroup.Drinks
import kotlinassignment.week3.menu.menuGroup.FrozenCustard
import kotlinassignment.week3.menu.menuGroup.MenuGroup
import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.MenuGroupMessenger
import kotlinassignment.week3.messenger.Message

class MenuGroupGuide: Guide {
    private val menuGroupMessenger = MenuGroupMessenger()

    override fun guide(continueState: ContinueState) {
        // 메뉴 출력에 대한 부분
        val menuGroupList = KioskMain.menu.filterIsInstance<MenuGroup>()
        menuGroupMessenger.writeMenu(menuGroupList)
        
        // 사용자의 입력 처리에 대한 부분
        val selectedNumber = InputMessenger().readInt()
        when (selectedNumber) {
            1 -> {
                continueState.nextGuide = MenuItemGuide()
                continueState.nextMenuGroup = Burgers
            }
            2 -> {
                continueState.nextGuide = MenuItemGuide()
                continueState.nextMenuGroup = FrozenCustard
            }
            3 -> {
                continueState.nextGuide = MenuItemGuide()
                continueState.nextMenuGroup = Drinks
            }
            0 -> {
                menuGroupMessenger.write(Message.EXIT)
                continueState.nextGuide = null
            }
            null -> {
                menuGroupMessenger.write(Message.NOT_INT_INPUT)
                // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
            }
            else -> {
                menuGroupMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
                // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
            }
        }
    }
}
