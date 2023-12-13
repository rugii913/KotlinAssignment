package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.menu.menuItem.MenuItem

class MenuItemMessenger {

    fun writeMenuItems(menuList: List<MenuItem>, menuGroup: MenuGroup) {
        println("\n[${menuGroup.name} MENU]")

        for (index in menuList.indices) {
            println("${index + 1}. ${String.format("%-28s", menuList[index].name)} | W ${menuList[index].price} | ${menuList[index].information}")
        }

        write(Message.MENU_BACK)
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }

    fun writeExceptionWarningIntRequired() {
        write(Message.EXCEPTION_WARNING_INT_REQUIRED)
    }
}
