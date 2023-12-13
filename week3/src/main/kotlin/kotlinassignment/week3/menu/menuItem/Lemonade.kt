package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class Lemonade: MenuItem {
    override val menuGroup = MenuGroup.Drinks
    override val name = "Lemonade"
    override val price = 4_300
    override val information = "매장에서 직접 만드는 상큼한 레몬에이드"
}
