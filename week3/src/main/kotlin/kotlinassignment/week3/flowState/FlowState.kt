package kotlinassignment.week3.flowState

import kotlinassignment.week3.cart.Cart
import kotlinassignment.week3.guide.CartGuide
import kotlinassignment.week3.guide.Guide
import kotlinassignment.week3.guide.MenuGroupGuide
import kotlinassignment.week3.guide.MenuItemGuide
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.menu.menuItem.MenuItem
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.OutputMessenger

class FlowState {

    val outputMessenger = OutputMessenger()
    val inputMessenger = InputMessenger(outputMessenger)
    val menuGroupGuide: Guide = MenuGroupGuide()
    val menuItemGuide: Guide = MenuItemGuide()
    val cartGuide: Guide = CartGuide()
    val cart = Cart()

    var nextGuide: Guide? = menuGroupGuide
    var previousGuide: Guide = menuGroupGuide
    lateinit var nextMenuGroup: MenuGroup

    lateinit var targetMenuItem: MenuItem
}
