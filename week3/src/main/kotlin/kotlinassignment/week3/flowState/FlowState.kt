package kotlinassignment.week3.flowState

import kotlinassignment.week3.cart.Cart
import kotlinassignment.week3.guide.Guide
import kotlinassignment.week3.guide.MenuGroupGuide
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.OutputMessenger

class FlowState {

    val outputMessenger = OutputMessenger()
    val inputMessenger = InputMessenger(outputMessenger)
    val menuGroupGuide = MenuGroupGuide()
    val cart = Cart()

    var nextGuide: Guide? = menuGroupGuide
    var previousGuide: Guide = menuGroupGuide
    lateinit var nextMenuGroup: MenuGroup
}
