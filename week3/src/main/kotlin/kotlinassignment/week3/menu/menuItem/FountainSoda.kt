package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.menuGroup.Drinks

class FountainSoda: MenuItem {
    override val menuGroup = Drinks
    override val name = "Fountain Soda"
    override val price = 2_900
    override val information = "탄산음료"
}
