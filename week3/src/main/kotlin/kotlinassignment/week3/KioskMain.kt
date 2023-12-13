package kotlinassignment.week3

import kotlinassignment.week3.messenger.ContinueState

object KioskMain {
    val menu: ArrayList<Any> = TODO()

    fun run() {
        val continueState = ContinueState()

        while (continueState.nextGuide != null) {
            continueState.nextGuide?.guide(continueState)
        }
    }
}
