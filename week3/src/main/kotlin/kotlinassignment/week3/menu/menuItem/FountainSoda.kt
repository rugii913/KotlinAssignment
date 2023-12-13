package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class FountainSoda: MenuItem {
    override val menuGroup = MenuGroup.Drinks
    override val name = "Fountain Soda"
    override val price = 2_900
    override val information = "탄산음료"
}
