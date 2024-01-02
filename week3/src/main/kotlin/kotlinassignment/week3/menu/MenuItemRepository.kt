package kotlinassignment.week3.menu

class MenuItemRepository {

    private val menuItemList: ArrayList<MenuItem> = ArrayList()

    init {
        // ? 자동으로 객체들을 추가하는 개선 방법 생각할 것
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
                "Shroom Burger", 9_400, "치즈로 속을 채워 바삭하게 튀겨낸 포토벨로 버섯 패티에 채소를 올린 베지테리안 버거", MenuGroup.Burgers
            )
        )
        menuItemList.add(
            MenuItem(
                "Shack Stack", 14_800, "포토벨로 버섯 패티, 비프 패티와 함께 토마토, 양상추, 쉑소스가 토핑된 치즈버거", MenuGroup.Burgers
            )
        )
        menuItemList.add(
            MenuItem(
                "Hamburger", 6_800, "포테이토 번과 비프 패티를 기본으로 신선한 채소를 취향에 따라 선택할 수 있는 버거", MenuGroup.Burgers
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
                "Hot dog", 4_800, "참나무 칩으로 훈연한 비프 소시지와 토종효모 포테이토 번을 사용한 핫 도그", MenuGroup.FlatTopDogs
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
                "Fifty/Fifty", 3_800, "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 시그니처 음료", MenuGroup.Drinks
            )
        )
        menuItemList.add(
            MenuItem(
                "Fountain Soda", 2_900, "탄산음료", MenuGroup.Drinks
            )
        )
        menuItemList.add(
            MenuItem(
                "Abita Root Beer", 4_800, "청량감 있는 독특한 미국식 무알콜 탄산음료", MenuGroup.Drinks
            )
        )
        menuItemList.add(
            MenuItem(
                "Hot Tea", 3_400, "보성 유기농 찻잎을 우려낸 녹차, 홍차, 페퍼민트 & 레몬그라스", MenuGroup.Drinks
            )
        )
    }

    fun findByMenuGroup(menuGroup: MenuGroup) = menuItemList.filter { it.menuGroup == menuGroup }
}