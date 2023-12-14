package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class MenuGroupGuide: Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분
        val menuGroupEntries = MenuGroup.entries
        flowState.outputMessenger.writeMenuGroup(menuGroupEntries) // 메뉴 그룹 출력
        flowState.outputMessenger.writeOrderMenu(menuGroupEntries.size + 1) // 주문 관련 출력

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt()
        if (inputStatus == InputMessenger.InputStatus.ABNORMAL) return // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return
        // TODO 여기서 InputMessenger 내부의 enum을 참조하고 있어서 InputMessenger에 의존함, 의존하지 않을 수 있는 방법?

        when (selectedNumber) {
            0 -> flowState.outputMessenger.write(Message.EXIT).also { flowState.nextGuide = null }
            in 1..menuGroupEntries.size -> {
                flowState.nextGuide = MenuItemGuide()
                flowState.nextMenuGroup = menuGroupEntries[selectedNumber - 1]
            }
            // TODO 더 깔끔하게 만들기
            menuGroupEntries.size + 1 -> {
                // 장바구니 내용 출력 // TODO 입력에 따른 새로운 출력처리를 해야하므로 OrderGuide로 분리해야함
                flowState.nextGuide = flowState.orderGuide
            }
            menuGroupEntries.size + 2 -> {
                flowState.cart.clear()
                flowState.outputMessenger.writeCartClearMessage()
            }
            else -> flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER) // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
        }
    }
}
