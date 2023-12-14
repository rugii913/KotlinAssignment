package kotlinassignment.week3.guide

import kotlinassignment.utilities.SomeExternalInterfaceRepresentingPayments
import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message
import java.time.LocalDateTime
import java.time.LocalTime
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
                if (cartItemList.size == 0) { // TODO 여기서 분기 처리 하지 말고 더 나은 방법이 있을 것 같다.
                    println("\n장바구니에 아무 것도 없는 경우 주문할 수 없어요.")
                    flowState.nextGuide = flowState.menuGroupGuide
                    return
                }

                val (paymentStatus, balance) = SomeExternalInterfaceRepresentingPayments.pay(totalPrice)

                when (paymentStatus) {
                    SomeExternalInterfaceRepresentingPayments.PaymentStatus.SUCCESS -> {
                        flowState.cart.clear()
                        println("\n결제를 완료했어요. (${LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))})")
                    }
                    SomeExternalInterfaceRepresentingPayments.PaymentStatus.EXCEPTION_INSUFFICIENT_FUNDS -> {
                        println("\n현재 잔액은 ${balance}원으로 ${totalPrice - balance}원이 부족해서 주문할 수 없어요.")
                    }
                    SomeExternalInterfaceRepresentingPayments.PaymentStatus.EXCEPTION_NOT_AVAILABLE_TIME -> {
                        val inspectionStartTime =  SomeExternalInterfaceRepresentingPayments.inspectionStartTime
                        val inspectionEndTime = SomeExternalInterfaceRepresentingPayments.inspectionEndTime

                        println("\n현재 시각은 ${LocalTime.now().format(DateTimeFormatter.ofPattern("HH시 mm분"))}이에요.")
                        println("\n은행 점검 시간($inspectionStartTime ~ ${inspectionEndTime}) 중이라 결제할 수 없어요.")
                    }
                }
                flowState.nextGuide = flowState.menuGroupGuide
            }
            2 -> {
                flowState.nextGuide = flowState.menuGroupGuide
            }
            else -> { // TODO 입력값이 1, 2가 아닐 때와 문자 입력값이 들어왔을 때를 한꺼번에 처리할 수 있는 방법?
                flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER) // KioskMain까지 나갔다가 while 후에 OrderGuide로 돌아온다.
            }
        }
    }
}
