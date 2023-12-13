package kotlinassignment.week3.guide

import kotlinassignment.week3.menu.menuItem.MenuItemRepository
import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.MenuItemMessenger
import kotlinassignment.week3.messenger.Message

class MenuItemGuide: Guide {
    private val menuItemMessenger = MenuItemMessenger()
    private val menuItemRepository = MenuItemRepository()

    override fun guide(continueState: ContinueState) {
        // 메뉴 출력에 대한 부분
        val currentMenuGroup = continueState.nextMenuGroup
        val menuItemList = menuItemRepository.findByMenuGroup(currentMenuGroup)
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
