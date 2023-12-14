package kotlinassignment.week3.messenger

import kotlinassignment.week3.cart.Cart
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.menu.menuItem.MenuItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.enums.EnumEntries

class OutputMessenger {

    fun writeMenuGroup(menuGroupEntries: EnumEntries<MenuGroup>) {
        write(Message.MENU_MAIN_INTRODUCE)

        for (entry in menuGroupEntries) {
            println("${entry.ordinal + 1}. ${String.format("%-20s", entry.name)} | ${entry.information}")
        }

        write(Message.MENU_EXIT)
    }

    fun writeMenuItems(menuList: List<MenuItem>, menuGroup: MenuGroup) {
        println("\n[${menuGroup.name} MENU]")

        for (index in menuList.indices) {
            println("${index + 1}. ${String.format("%-28s", menuList[index].name)} | W ${menuList[index].price} | ${menuList[index].information}")
        }

        write(Message.MENU_BACK)
    }

    fun writeOrderMenu(startNumber: Int) {
        println("\n[ORDER MENU]")
        println("$startNumber. ${String.format("%-20s", "Order")} | 장바구니를 확인 후 주문합니다.")
        println("${startNumber + 1}. ${String.format("%-20s", "Cancel")} | 진행 중인 주문을 취소합니다.")
    }

    fun writeCartItemList(cart: Cart) {
        val cartItemList = cart.getAll()

        println("\n[Orders]")
        for (menuItem in cartItemList) {
            println("${String.format("%-28s", menuItem.name)} | W ${menuItem.price} | ${menuItem.information}")
        }

        println("\n[Total]")
        println("W ${cartItemList.sumOf { it.price }}")

        println("1. 주문   2. 메뉴로 돌아가기")
        println("결제를 완료했습니다. (${LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))})")
    }

    fun writeCartClearMessage() {
        println("\n장바구니의 모든 항목이 삭제되었습니다.")
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }

    fun writeExceptionWarningIntRequired() {
        write(Message.EXCEPTION_WARNING_INT_REQUIRED)
    }
}
