package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.menuGroup.Drinks

class Lemonade: MenuItem {
    override val menuGroup = Drinks
    override val name = "Lemonade"
    override val price = 4_300
    override val information = "매장에서 직접 만드는 상큼한 레몬에이드"
}
