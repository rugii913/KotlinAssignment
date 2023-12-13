package kotlinassignment.week3.guide

import kotlinassignment.week3.menu.menuItem.MenuItemRepository
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
        val selectedNumber: Int
        try {
            selectedNumber = InputMessenger().readInt()
        } catch (e: NumberFormatException) {
            menuItemMessenger.writeExceptionWarningIntRequired()
            return // 다시 같은 메뉴로 돌아오기 위해 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return
            // TODO 공통 처리할 수 있는 방법 없을지 고민
        }

        if (selectedNumber == 0) {
            continueState.nextGuide = continueState.previousGuide
        } else if (selectedNumber <= menuItemList.size) {
            println("이번에 선택한 메뉴의 이름은 ${menuItemList[selectedNumber - 1].name}!")
            println("이번에 선택한 메뉴의 가격은 ${menuItemList[selectedNumber - 1].price}원!")
            println("끝내려면 0을 입력하기!")
        } else {
            menuItemMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            // 다시 같은 메뉴로 돌아오기 위해서 nextGuide를 set하지 않는다.
        }
    }
}
