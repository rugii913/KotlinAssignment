package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.menuGroup.Burgers

class ShroomBurger: MenuItem {
    override val menuGroup = Burgers
    override val name = "Shroom Burger"
    override val price = 9_400
    override val information = "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"
}
