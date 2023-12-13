package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.Menu
import kotlinassignment.week3.menu.menuGroup.MenuGroup

interface MenuItem: Menu {

    val menuGroup: MenuGroup
    override val name: String
    val price: Int
    override val information: String
}
