package kotlinassignment.week3

import kotlinassignment.week3.menu.menuItem.*
import kotlinassignment.week3.messenger.ContinueState

object KioskMain {
    val menu: ArrayList<MenuItem> = ArrayList()

    fun run() {
        val continueState = ContinueState()

        // TODO 자동으로 객체들을 추가하는 개선 방법 생각할 것
        initializeMenu()

        while (continueState.nextGuide != null) {
            continueState.nextGuide?.guide(continueState)
        }
    }

    private fun initializeMenu() {
        menu.add(ShackBurger())
        menu.add(SmokeShack())
        menu.add(ShroomBurger())
        //
        menu.add(ClassicHandSpunShakes())
        menu.add(CupAndCones())
        menu.add(Floats())
        //
        menu.add(Lemonade())
        menu.add(FreshBrewedIcedTea())
        menu.add(FountainSoda())
    }
}
