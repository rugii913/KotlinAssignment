package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.BurgersMessenger
import kotlinassignment.week3.messenger.ContinueState

class BurgersGuide: Guide {

    override fun guide(continueState: ContinueState) {
        BurgersMessenger().write()
        val selectedNumber = BurgersMessenger().readInt()

        if (selectedNumber == 0) continueState.nextGuide = continueState.previousGuide
    }
}