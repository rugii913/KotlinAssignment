package kotlinassignment.week3.menuItem

import kotlinassignment.week3.menuGroup.MenuGroup

interface MenuItem {

    val menuGroup: MenuGroup
    val name: String
    val price: Int
    val information: String
}
