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
        val (inputStatus, selectedNumber) = InputMessenger().readInt()
        if (inputStatus == InputMessenger.InputStatus.ABNORMAL) return // 다시 같은 메뉴로 돌아오기 위해 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return

        if (selectedNumber == 0) {
            continueState.nextGuide = continueState.previousGuide
        } else if (selectedNumber <= menuItemList.size) {
            println("${menuItemList[selectedNumber - 1].name} | W ${menuItemList[selectedNumber - 1].price} | ${menuItemList[selectedNumber - 1].information}")
            println("위 메뉴를 장바구니에 추가하시겠습니까?")
            println("1. 확인\t\t2. 취소")
            println("${menuItemList[selectedNumber - 1].name}가 장바구니에 추가되었습니다.")
            continueState.nextGuide = continueState.previousGuide
        } else {
            menuItemMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            // 다시 같은 메뉴로 돌아오기 위해서 nextGuide를 set하지 않는다.
        }
    }
}
