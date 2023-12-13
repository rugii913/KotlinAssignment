package kotlinassignment.week3

import kotlinassignment.week3.guide.ContinueState

object KioskMain {

    fun run() {
        val continueState = ContinueState()

        while (continueState.nextGuide != null) {
            continueState.nextGuide?.guide(continueState)
        }
    }
}
