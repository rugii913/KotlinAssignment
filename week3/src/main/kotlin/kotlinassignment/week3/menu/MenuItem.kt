package kotlinassignment.week3.menu

data class MenuItem(
    val name: String,
    val price: Int,
    val information: String,
    val menuGroup: MenuGroup,
)