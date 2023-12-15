package kotlinassignment.week3.order

import kotlinassignment.utilities.SomeExternalInterfaceRepresentingPayments
import kotlinassignment.week3.cart.Cart

class OrderService(private val orderRepository: OrderRepository) {

    fun requestOrder(cart: Cart, totalPrice: Int): Pair<SomeExternalInterfaceRepresentingPayments.PaymentStatus, Int> {
        val responsePair = SomeExternalInterfaceRepresentingPayments.pay(totalPrice)

        if (responsePair.first == SomeExternalInterfaceRepresentingPayments.PaymentStatus.SUCCESS) {
            orderRepository.save(Order(cart.getAll()))
            cart.clear()
        }

        return responsePair
    }
}