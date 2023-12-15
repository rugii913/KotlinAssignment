package kotlinassignment.week3.cart

import kotlinassignment.week3.menu.MenuItem

class Cart {
    private val cartItemList = mutableListOf<MenuItem>()

    fun add(menuItem: MenuItem) {
        cartItemList.add(menuItem)
    }

    fun getAll(): MutableList<MenuItem> {
        return cartItemList
    }

    fun clear() {
        cartItemList.clear()
    }
}