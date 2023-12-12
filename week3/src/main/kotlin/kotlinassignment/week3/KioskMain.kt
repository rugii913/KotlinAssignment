package kotlinassignment.week3

import kotlinassignment.week3.guide.FrontGuide
import kotlinassignment.week3.guide.Guide
import kotlinassignment.week3.messenger.ContinueState

object KioskMain {
    fun run() {
        val continueState = ContinueState()

        while (continueState.nextGuide != null) {
            continueState.nextGuide?.guide(continueState)
        }

        println("프로그램을 종료합니다.")
    }
}