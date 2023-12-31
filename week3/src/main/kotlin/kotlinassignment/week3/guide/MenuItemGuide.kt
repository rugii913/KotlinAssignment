package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.menu.MenuItemRepository
import kotlinassignment.week3.messenger.InputMessenger

class MenuItemGuide(private val menuItemRepository: MenuItemRepository) : Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분
        val currentMenuGroup = flowState.nextMenuGroup
        val menuItemList = menuItemRepository.findByMenuGroup(currentMenuGroup)
        flowState.outputMessenger.writeMenuItems(menuItemList, currentMenuGroup)

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt(menuItemList.size)
        if (inputStatus != InputMessenger.InputStatus.SUCCESS) return // 다시 같은 메뉴로 돌아오기 위해 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return

        when (selectedNumber) {
            0 -> flowState.nextGuide = flowState.menuGroupGuide
            in 1..menuItemList.size -> {
                flowState.nextGuide = flowState.cartGuide
                flowState.targetMenuItem = menuItemList[selectedNumber - 1]
            }
        }
    }
}
