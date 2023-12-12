package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.FrozenCustardMessenger
import kotlinassignment.week3.messenger.ContinueState

class FrozenCustardGuide: Guide {

    override fun guide(continueState: ContinueState) {
        FrozenCustardMessenger().write()
        val selectedNumber = FrozenCustardMessenger().readInt()

        if (selectedNumber == 0) continueState.nextGuide = continueState.previousGuide
    }
}