package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.BurgersMessenger
import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class BurgersGuide: Guide {
    private val burgersMessenger = BurgersMessenger()

    override fun guide(continueState: ContinueState) {
        burgersMessenger.writeMenu()
        val selectedNumber = InputMessenger().readInt()

        when (selectedNumber) {
            0 -> {
                continueState.nextGuide = continueState.previousGuide
            }
            null -> {
                burgersMessenger.write(Message.NOT_INT_INPUT)
            }
            else -> {
                burgersMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            }
        }
    }
}