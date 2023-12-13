package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.menuGroup.MenuGroup

interface MenuItem {

    val name: String
    val price: Int
    val information: String
    val menuGroup: MenuGroup
}
