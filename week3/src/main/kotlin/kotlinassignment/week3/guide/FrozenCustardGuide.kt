package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.FrozenCustardMessenger
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class FrozenCustardGuide: Guide {
    private val frozenCustardMessenger = FrozenCustardMessenger()

    override fun guide(continueState: ContinueState) {
        frozenCustardMessenger.writeMenu()
        val selectedNumber = InputMessenger().readInt()

        when (selectedNumber) {
            0 -> {
                continueState.nextGuide = continueState.previousGuide
            }
            null -> {
                frozenCustardMessenger.write(Message.NOT_INT_INPUT)
            }
            else -> {
                frozenCustardMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            }
        }
    }
}
