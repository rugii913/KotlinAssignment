package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class SmokeShack: MenuItem {
    override val menuGroup = MenuGroup.Burgers
    override val name = "SmokeShack"
    override val price = 8_900
    override val information = "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
}
