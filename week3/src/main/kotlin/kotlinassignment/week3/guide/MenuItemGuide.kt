package kotlinassignment.week3.guide

import kotlinassignment.week3.KioskMain
import kotlinassignment.week3.menu.menuGroup.Burgers
import kotlinassignment.week3.menu.menuItem.MenuItem
import kotlinassignment.week3.messenger.*

class MenuItemGuide: Guide {
    private val menuItemMessenger = MenuItemMessenger()

    override fun guide(continueState: ContinueState) {
        // 메뉴 출력에 대한 부분
        val currentMenuGroup = continueState.nextMenuGroup
        val menuItemList = KioskMain.menu.filterIsInstance<MenuItem>().filter { it.menuGroup == currentMenuGroup }
        menuItemMessenger.writeMenuItems(menuItemList, currentMenuGroup)

        // 사용자의 입력 처리에 대한 부분
        val selectedNumber = InputMessenger().readInt()
        when (selectedNumber) {
            0 -> {
                continueState.nextGuide = continueState.previousGuide
            }
            null -> {
                menuItemMessenger.write(Message.NOT_INT_INPUT)
            }
            else -> {
                menuItemMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            }
        }
    }
}
