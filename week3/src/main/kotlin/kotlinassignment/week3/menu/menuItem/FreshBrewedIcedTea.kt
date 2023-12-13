package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class FreshBrewedIcedTea: MenuItem {
    override val menuGroup = MenuGroup.Drinks
    override val name = "Fresh Brewed Iced Tea"
    override val price = 3_500
    override val information = "직접 유기농 홍차를 우려낸 아이스 티"
}
