package kotlinassignment.week3.guide

import kotlinassignment.utilities.SomeExternalInterfaceRepresentingPayments
import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.order.Order
import kotlinassignment.week3.order.OrderRepository
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class OrderGuide(val orderRepository: OrderRepository): Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분 // TODO Order 관련 메뉴 출력은 OutputMessenger로 옮겼으나, 입력들어온 후 출력에 대해서 정리 필요
        val cartItemList = flowState.cart.getAll()
        val totalPrice = cartItemList.sumOf { it.price }

        flowState.outputMessenger.writeOrderList(cartItemList, totalPrice)

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt(2)
        if (inputStatus != InputMessenger.InputStatus.SUCCESS) return

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
                        orderRepository.save(Order(cartItemList))
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
        }
    }
}
