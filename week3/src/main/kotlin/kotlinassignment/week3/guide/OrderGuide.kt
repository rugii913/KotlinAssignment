package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderGuide: Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분 // TODO 메시지 출력은 OutputMessenger로
        val cartItemList = flowState.cart.getAll()

        println("\n아래와 같이 주문 하시겠습니까?")
        println("\n[Orders]")
        for (menuItem in cartItemList) {
            println("${String.format("%-28s", menuItem.name)} | W ${menuItem.price} | ${menuItem.information}")
        }

        println("\n[Total]")
        val totalPrice = cartItemList.sumOf { it.price }
        println("W $totalPrice")

        println("1. 주문   2. 메뉴로 돌아가기")

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt()

        if (inputStatus == InputMessenger.InputStatus.ABNORMAL) { // 현재 예외 처리 방식에서는 숫자 입력 필요한 곳에 문자 들어온 경우
            return // KioskMain로 나가서 while 돌고 targetMenuItem 유지한 채로 다시 이 OrderGuide로 돌아온다.
        }

        when (selectedNumber) {
            1 -> {
                println("결제를 완료했습니다. (${LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))})")
                flowState.nextGuide = flowState.menuGroupGuide
                return
            }
            2 -> {
                flowState.nextGuide = flowState.menuGroupGuide
                return
            }
            else -> { // TODO 입력값이 1, 2가 아닐 때와 문자 입력값이 들어왔을 때를 한꺼번에 처리할 수 있는 방법?
                flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
                return // KioskMain까지 나갔다가 while 후에 OrderGuide로 돌아온다.
            }
        }
    }
}
