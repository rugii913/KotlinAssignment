package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class Floats: MenuItem {
    override val menuGroup = MenuGroup.FrozenCustard
    override val name = "Floats"
    override val price = 6_500
    override val information = "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료"
}
