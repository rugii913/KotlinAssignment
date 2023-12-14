package kotlinassignment.week3

import kotlinassignment.week3.flowState.FlowState

object KioskMain {

    fun run() {
        val flowState = FlowState()

        while (flowState.nextGuide != null) {
            flowState.nextGuide?.guide(flowState)
        }
    }
}
