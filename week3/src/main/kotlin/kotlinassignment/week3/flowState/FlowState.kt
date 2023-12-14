package kotlinassignment.week3.flowState

import kotlinassignment.week3.guide.Guide
import kotlinassignment.week3.guide.MenuGroupGuide
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.OutputMessenger

class FlowState {

    val outputMessenger = OutputMessenger()
    val inputMessenger = InputMessenger(outputMessenger)

    var nextGuide: Guide? = MenuGroupGuide()
    var previousGuide: Guide = MenuGroupGuide()
    lateinit var nextMenuGroup: MenuGroup
}
