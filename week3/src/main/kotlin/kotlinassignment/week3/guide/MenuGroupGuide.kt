package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.MenuGroupMessenger
import kotlinassignment.week3.messenger.Message

class MenuGroupGuide: Guide {
    private val menuGroupMessenger = MenuGroupMessenger()

    override fun guide(continueState: ContinueState) {
        menuGroupMessenger.writeMenu()
        val selectedNumber = InputMessenger().readInt()

        when (selectedNumber) {
            1 -> continueState.nextGuide = BurgersGuide()
            2 -> continueState.nextGuide = FrozenCustardGuide()
            0 -> {
                menuGroupMessenger.write(Message.EXIT)
                continueState.nextGuide = null
            }
            null -> {
                menuGroupMessenger.write(Message.NOT_INT_INPUT)
                // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
            }
            else -> {
                menuGroupMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
                // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
            }
        }
    }
}
