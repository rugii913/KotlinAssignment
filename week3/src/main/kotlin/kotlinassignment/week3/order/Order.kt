package kotlinassignment.week3.order

import kotlinassignment.week3.menu.menuItem.MenuItem

class Order(cartItemList: MutableList<MenuItem>) {

    val itemList: List<MenuItem> = cartItemList.toList()
}
