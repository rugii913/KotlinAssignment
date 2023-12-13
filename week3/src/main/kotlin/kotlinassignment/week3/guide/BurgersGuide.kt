package kotlinassignment.week3.guide

import kotlinassignment.week3.KioskMain
import kotlinassignment.week3.menu.menuGroup.Burgers
import kotlinassignment.week3.menu.menuItem.MenuItem
import kotlinassignment.week3.messenger.BurgersMessenger
import kotlinassignment.week3.messenger.ContinueState
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class BurgersGuide: Guide {
    private val burgersMessenger = BurgersMessenger()

    override fun guide(continueState: ContinueState) {
        // 메뉴 출력에 대한 부분
        val burgersList = KioskMain.menu.filterIsInstance<MenuItem>().filter { it.menuGroup == Burgers }
        burgersMessenger.writeMenu(burgersList)

        // 사용자의 입력 처리에 대한 부분
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
