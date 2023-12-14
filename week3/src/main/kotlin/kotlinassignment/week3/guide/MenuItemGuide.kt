package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.menu.menuItem.MenuItem
import kotlinassignment.week3.menu.menuItem.MenuItemRepository
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class MenuItemGuide: Guide {

    private val menuItemRepository = MenuItemRepository()

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분
        val currentMenuGroup = flowState.nextMenuGroup
        val menuItemList = menuItemRepository.findByMenuGroup(currentMenuGroup)
        flowState.outputMessenger.writeMenuItems(menuItemList, currentMenuGroup)

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt()
        if (inputStatus == InputMessenger.InputStatus.ABNORMAL) return // 다시 같은 메뉴로 돌아오기 위해 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return

        when (selectedNumber) {
            0 -> flowState.nextGuide = flowState.previousGuide
            in 1..menuItemList.size -> {
                addToCart(menuItemList[selectedNumber - 1], flowState)
                flowState.nextGuide = flowState.menuGroupGuide // 메인 메뉴로
            }
            else -> flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER) // 다시 같은 메뉴로 돌아오기 위해서 nextGuide를 set하지 않는다.
        }
    }

    private fun addToCart(menuItem: MenuItem, flowState: FlowState) {
        var pair: Pair<InputMessenger.InputStatus, Int>

        while (true) { // TODO 여기에서도 while을 없앨 수 있는 방법 고민
            println("\n${menuItem.name} | W ${menuItem.price} | ${menuItem.information}") // TODO 메시지 출력은 OutputMessenger로
            println("\n위 메뉴를 장바구니에 추가하시겠습니까?")
            println("\n1. 확인\t\t2. 취소")
            pair = flowState.inputMessenger.readInt()

            if (pair.second !in 1..2) { // TODO 입력값이 1, 2가 아닐 때 더 깔끔하게 처리할 수 있는 방법?
                flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
                continue
            }

            if (pair.first == InputMessenger.InputStatus.NORMAL) break
        }

        if (pair.second == 1) flowState.cart.add(menuItem)
            .run { println("\n${menuItem.name}가 장바구니에 추가되었습니다.") }
    }
}
