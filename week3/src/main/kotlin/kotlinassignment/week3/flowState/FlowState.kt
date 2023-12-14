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
    // var previousGuide: Guide = menuGroupGuide // 문제 발생하는 부분으로 주석 처리함, 추후 다시 사용하게 되더라도 안전하게 활용할 수 있는 방법 찾을 것
    lateinit var nextMenuGroup: MenuGroup

    lateinit var targetMenuItem: MenuItem
}
