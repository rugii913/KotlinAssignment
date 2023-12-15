package kotlinassignment.week3.menu

class MenuItemRepository {

    private val menuItemList: ArrayList<MenuItem> = ArrayList()

    init {
        // TODO 자동으로 객체들을 추가하는 개선 방법 생각할 것
        menuItemList.add(
            MenuItem(
                "ShackBurger", 6_900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거", MenuGroup.Burgers
            )
        )
        menuItemList.add(
            MenuItem(
                "SmokeShack", 8_900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", MenuGroup.Burgers
            )
        )
        menuItemList.add(
            MenuItem(
                "Shroom Burger", 9_400, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", MenuGroup.Burgers
            )
        )
        //
        menuItemList.add(
            MenuItem(
                "Classic Hand-Spun Shakes", 6_500, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크", MenuGroup.FrozenCustard
            )
        )
        menuItemList.add(
            MenuItem(
                "Cup & Cones", 5_400, "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림", MenuGroup.FrozenCustard
            )
        )
        menuItemList.add(
            MenuItem(
                "Floats", 6_500, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료", MenuGroup.FrozenCustard
            )
        )
        //
        menuItemList.add(
            MenuItem(
                "Lemonade", 4_300, "매장에서 직접 만드는 상큼한 레몬에이드", MenuGroup.Drinks
            )
        )
        menuItemList.add(
            MenuItem(
                "Fresh Brewed Iced Tea", 3_500, "직접 유기농 홍차를 우려낸 아이스 티", MenuGroup.Drinks
            )
        )
        menuItemList.add(
            MenuItem(
                "Fountain Soda", 2_900, "탄산음료", MenuGroup.Drinks
            )
        )
    }

    fun findByMenuGroup(menuGroup: MenuGroup) = menuItemList.filter { it.menuGroup == menuGroup }
}