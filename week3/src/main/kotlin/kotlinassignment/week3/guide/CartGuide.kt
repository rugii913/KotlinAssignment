package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class CartGuide: Guide {

    override fun guide(flowState: FlowState) { // TODO while 없애기는 유지했으나, FlowState에서 XxxGuide 타입 변수 관련 교통정리가 필요할 듯함
        // 메뉴 출력에 대한 부분 // TODO 메시지 출력은 OutputMessenger로
        val menuItem = flowState.targetMenuItem
        println("\n${menuItem.name} | W ${menuItem.price} | ${menuItem.information}")
        println("\n위 메뉴를 장바구니에 추가하시겠습니까?")
        println("\n1. 확인\t\t2. 취소")

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt()

        if (inputStatus == InputMessenger.InputStatus.ABNORMAL) { // 현재 예외 처리 방식에서는 숫자 입력 필요한 곳에 문자 들어온 경우
            return // KioskMain로 나가서 while 돌고 targetMenuItem 유지한 채로 다시 이 CartGuide로 돌아온다.
        }

        when (selectedNumber) {
            1 -> {
                flowState.cart.add(menuItem).also { println("\n${menuItem.name}가 장바구니에 추가되었습니다.") }
                flowState.nextGuide = flowState.menuGroupGuide
                return
            }
            2 -> { // nextMenuGroup은 바뀌지 않았을 것이므로, 같은 MenuGroup을 바라보는 MenuItemGuide로 들어갈 것
                flowState.nextGuide = flowState.menuItemGuide
                return
            }
            else -> { // TODO 입력값이 1, 2가 아닐 때와 문자 입력값이 들어왔을 때를 한꺼번에 처리할 수 있는 방법?
                flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
                return // KioskMain까지 나갔다가 while 후에 targetMenuItem 유지한 채로 다시 이 CartGuide로 돌아온다.
            }
        }
    }
}
