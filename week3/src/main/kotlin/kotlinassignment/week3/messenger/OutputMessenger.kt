package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.menu.MenuItem
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

    fun writeCartConfirmMessage(menuItem: MenuItem) {
        println("\n${menuItem.name} | W ${menuItem.price} | ${menuItem.information}")
        println("\n위 메뉴를 장바구니에 추가하시겠습니까?")
        println("\n1. 확인\t\t2. 취소")
    }

    fun writeCartAddMessage(menuItemName: String) {
        println("\n${menuItemName}가 장바구니에 추가되었습니다.")
    }

    fun writeCartClearMessage() {
        println("\n장바구니의 모든 항목이 삭제되었습니다.")
    }

    fun writeOrderList(cartItemList: MutableList<MenuItem>, totalPrice: Int) {
        println("\n아래와 같이 주문 하시겠습니까?")
        println("\n[Orders]")
        for (menuItem in cartItemList) {
            println("${String.format("%-28s", menuItem.name)} | W ${menuItem.price} | ${menuItem.information}")
        }

        println("\n[Total]")
        println("W $totalPrice")

        println("1. 주문   2. 메뉴로 돌아가기")
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }

    fun writeWarningIntOutOfRange() {
        write(Message.WARNING_INT_OUT_OF_RANGE)
    }

    fun writeExceptionWarningIntRequired() {
        write(Message.EXCEPTION_WARNING_INT_REQUIRED)
    }
}
