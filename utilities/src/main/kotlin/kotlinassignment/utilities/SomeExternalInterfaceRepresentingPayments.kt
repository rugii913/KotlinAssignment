package kotlinassignment.utilities

import java.time.LocalTime
import kotlin.random.Random

object SomeExternalInterfaceRepresentingPayments {

    private var balance = Random.nextInt(50, 100) * 1000
    val inspectionStartTime = LocalTime.of(0, 5)
    val inspectionEndTime = LocalTime.of(0, 20)

    fun pay(paymentPrice: Int): Pair<PaymentStatus, Int> {

        if (!isAvailableTime(LocalTime.now())) {
            return Pair(PaymentStatus.EXCEPTION_NOT_AVAILABLE_TIME, balance)
        }

        return if (balance < paymentPrice) {
            Pair(PaymentStatus.EXCEPTION_INSUFFICIENT_FUNDS, balance)
        } else {
            balance -= paymentPrice
            Pair(PaymentStatus.SUCCESS, balance)
        }
    }

    private fun isAvailableTime(currentTime: LocalTime): Boolean {
        return currentTime < inspectionStartTime || currentTime > inspectionEndTime
    }

    enum class PaymentStatus {
        SUCCESS, EXCEPTION_INSUFFICIENT_FUNDS, EXCEPTION_NOT_AVAILABLE_TIME
    }
}