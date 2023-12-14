package kotlinassignment.utilities

import kotlin.random.Random

object SomeExternalInterfaceRepresentingPayments {

    private var balance = Random.nextInt(1, 10) * 1000

    fun pay(paymentPrice: Int): Pair<PaymentStatus, Int> {
        return if (balance < paymentPrice) {
            Pair(PaymentStatus.EXCEPTION_INSUFFICIENT_FUNDS, balance)
        } else {
            balance -= paymentPrice
            Pair(PaymentStatus.SUCCESS, balance)
        }
    }

    enum class PaymentStatus {
        SUCCESS, EXCEPTION_INSUFFICIENT_FUNDS
    }
}