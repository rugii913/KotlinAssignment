package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.order.OrderService

class OrderGuide(private val orderService: OrderService) : Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분
        val cartItemList = flowState.cart.getAll()
        val totalPrice = cartItemList.sumOf { it.price }

        flowState.outputMessenger.writeOrderList(
            cartItemList,
            totalPrice
        ) // TODO 양자택일 선택지 출력 메서드 따로 있는 편이 나을 듯 - 입력이랑 묶어서

        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt(2)

        if (inputStatus != InputMessenger.InputStatus.SUCCESS) {
            return // 입력이 잘못된 경우 다시 이 OrderGuide로 들어온다.
        } else if (selectedNumber == 2) {
            flowState.nextGuide = flowState.menuGroupGuide
            return // 입력이 2인 경우 menuGroupGuide로 보낸다.
        } else if (cartItemList.size == 0) {
            flowState.outputMessenger.writeWarningOrderRequestForEmptyOrderList()
            flowState.nextGuide = flowState.menuGroupGuide
            return
        }

        // 사용자의 입력 처리에 대한 부분 - 주문 요청 - 결제 시도
        val (paymentStatus, balance) = orderService.requestOrder(flowState.cart, totalPrice)
        flowState.outputMessenger.writePaymentResult(paymentStatus, balance, totalPrice)

        flowState.nextGuide = flowState.menuGroupGuide // 여기까지 온 경우, 결제 실패든 성공이든 menuGoupGuide(메인 메뉴)로 간다
    }
}
