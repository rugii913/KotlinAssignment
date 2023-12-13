package kotlinassignment.week3.menu.menuItem

import kotlinassignment.week3.menu.MenuGroup

class MenuItemRepository {

    private val menuItemList: ArrayList<MenuItem> = ArrayList()

    init {
        // TODO 자동으로 객체들을 추가하는 개선 방법 생각할 것
        menuItemList.add(ShackBurger())
        menuItemList.add(SmokeShack())
        menuItemList.add(ShroomBurger())
        //
        menuItemList.add(ClassicHandSpunShakes())
        menuItemList.add(CupAndCones())
        menuItemList.add(Floats())
        //
        menuItemList.add(Lemonade())
        menuItemList.add(FreshBrewedIcedTea())
        menuItemList.add(FountainSoda())
    }

    fun findByMenuGroup(menuGroup: MenuGroup) = menuItemList.filter { it.menuGroup == menuGroup }
}