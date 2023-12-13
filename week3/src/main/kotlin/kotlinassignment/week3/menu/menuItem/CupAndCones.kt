package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.menuGroup.FrozenCustard

class CupAndCones: MenuItem {
    override val menuGroup = FrozenCustard
    override val name = "Cup & Cones"
    override val price = 5_400
    override val information = "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림"
}
