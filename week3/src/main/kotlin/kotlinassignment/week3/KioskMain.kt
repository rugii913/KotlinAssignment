package kotlinassignment.week3

import kotlinassignment.week3.guide.FrontGuide
import kotlinassignment.utilities.ContinueState

object KioskMain {
    fun run() {
        val continueState = ContinueState()

        while (continueState.isContinued) {
            FrontGuide().guide(continueState)
        }

        println("프로그램을 종료합니다.")
    }
}