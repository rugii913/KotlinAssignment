package kotlinassignment.week3.menuItem

import kotlinassignment.week3.menuGroup.FrozenCustard

class ClassicHandSpunShakes: MenuItem {
    override val menuGroup = FrozenCustard
    override val name = "Classic Hand-Spun Shakes"
    override val price = 6_500
    override val information = "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크"
}
