package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.FrontMessenger
import kotlinassignment.week3.messenger.InputMessenger

class FrontGuide: Guide {

    override fun guide(continueState: ContinueState) {
        FrontMessenger().write()
        val selectedNumber = InputMessenger().readInt()

        when (selectedNumber) { // TODO readInt()에서 null 처리를 안 하고 있는데 selectedNumber 괜찮은지...
            1 -> continueState.nextGuide = BurgersGuide()
            2 -> continueState.nextGuide = FrozenCustardGuide()
            0 -> continueState.nextGuide = null
        }
    }
}