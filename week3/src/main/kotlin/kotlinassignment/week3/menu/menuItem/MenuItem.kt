package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

interface MenuItem {

    val name: String
    val price: Int
    val information: String
    val menuGroup: MenuGroup
}
