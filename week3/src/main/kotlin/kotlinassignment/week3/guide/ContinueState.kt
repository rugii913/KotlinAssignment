package kotlinassignment.week3.guide

import kotlinassignment.week3.menu.MenuGroup

class ContinueState {

    var nextGuide: Guide? = MenuGroupGuide()
    var previousGuide: Guide = MenuGroupGuide()
    lateinit var nextMenuGroup: MenuGroup
}
