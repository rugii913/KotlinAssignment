package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.menu.menuItem.MenuItem
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

    fun write(message: Message) {
        println(message.messegeValue)
    }

    fun writeExceptionWarningIntRequired() {
        write(Message.EXCEPTION_WARNING_INT_REQUIRED)
    }
}
