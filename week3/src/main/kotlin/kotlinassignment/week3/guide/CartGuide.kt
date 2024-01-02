package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.messenger.InputMessenger

class CartGuide : Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분
        val menuItem = flowState.targetMenuItem
        flowState.outputMessenger.writeCartConfirmMessage(menuItem) // ? 양자택일 선택지 출력 메서드 따로 있는 편이 나을 듯 - 입력이랑 묶어서

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt(2)
        if (inputStatus != InputMessenger.InputStatus.SUCCESS) return

        when (selectedNumber) {
            1 -> {
                flowState.cart.add(menuItem).also { flowState.outputMessenger.writeCartAddMessage(menuItem.name) }
                flowState.nextGuide = flowState.menuGroupGuide
                return
            }

            2 -> { // nextMenuGroup은 바뀌지 않았을 것이므로, 같은 MenuGroup을 바라보는 MenuItemGuide로 들어갈 것
                flowState.nextGuide = flowState.menuItemGuide
                return
            }
        }
    }
}
