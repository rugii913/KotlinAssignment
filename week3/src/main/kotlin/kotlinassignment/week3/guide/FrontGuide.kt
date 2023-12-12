package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.FrontMessenger
import kotlinassignment.utilities.ContinueState

class FrontGuide {

    fun guide(continueState: ContinueState) {
        FrontMessenger().write()
        val selectedNumber = FrontMessenger().readInt()

        if (selectedNumber == 0) continueState.isContinued = false
    }
}