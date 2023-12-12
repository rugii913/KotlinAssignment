package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.FrontMessenger
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class FrontGuide: Guide {
    private val frontMessenger = FrontMessenger()

    override fun guide(continueState: ContinueState) {
        frontMessenger.writeMenu()
        val selectedNumber = InputMessenger().readInt()

        when (selectedNumber) {
            1 -> continueState.nextGuide = BurgersGuide()
            2 -> continueState.nextGuide = FrozenCustardGuide()
            0 -> {
                frontMessenger.write(Message.EXIT)
                continueState.nextGuide = null
            }
            null -> {
                frontMessenger.write(Message.NOT_INT_INPUT)
                // 다시 FrontGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
            }
            else -> {
                frontMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
                // 다시 FrontGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
            }
        }
    }
}