package kotlinassignment.week3

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.guide.OrderGuide
import kotlinx.coroutines.*

object KioskMain {

    @OptIn(DelicateCoroutinesApi::class)
    fun run() {
        val flowState = FlowState()

        GlobalScope.launch {
            while (true) {
                println("\n현재 대기 주문수: ${(flowState.orderGuide as OrderGuide).orderService.orderRepository.count()}")
                delay(10000)
            }
        }

        while (flowState.nextGuide != null) {
            flowState.nextGuide?.guide(flowState)
            runBlocking { delay(1000) }
        }
    }
}
