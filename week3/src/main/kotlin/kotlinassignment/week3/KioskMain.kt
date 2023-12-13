package kotlinassignment.week3

import kotlinassignment.week3.menu.Menu
import kotlinassignment.week3.menu.menuGroup.Burgers
import kotlinassignment.week3.menu.menuGroup.FrozenCustard
import kotlinassignment.week3.menu.menuItem.ClassicHandSpunShakes
import kotlinassignment.week3.menu.menuItem.CupAndCones
import kotlinassignment.week3.menu.menuItem.ShackBurger
import kotlinassignment.week3.menu.menuItem.SmokeShack
import kotlinassignment.week3.messenger.ContinueState

object KioskMain {
    val menu: ArrayList<Menu> = ArrayList()

    fun run() {
        val continueState = ContinueState()

        // TODO 자동으로 특정 타입 객체들을 추가하는 개선 방법 생각할 것
        menu.add(Burgers)
        menu.add(FrozenCustard)
        menu.add(ShackBurger())
        menu.add(SmokeShack())
        menu.add(ClassicHandSpunShakes())
        menu.add(CupAndCones())

        while (continueState.nextGuide != null) {
            continueState.nextGuide?.guide(continueState)
        }
    }
}
