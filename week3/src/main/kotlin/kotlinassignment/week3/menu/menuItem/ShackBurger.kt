package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class ShackBurger: MenuItem {
    override val menuGroup = MenuGroup.Burgers
    override val name = "ShackBurger"
    override val price = 6_900
    override val information = "토마토, 양상추, 쉑소스가 토핑된 치즈버거"
}
