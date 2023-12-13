package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class ClassicHandSpunShakes: MenuItem {
    override val menuGroup = MenuGroup.FrozenCustard
    override val name = "Classic Hand-Spun Shakes"
    override val price = 6_500
    override val information = "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크"
}
