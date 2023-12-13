package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.MenuGroup
import kotlin.enums.EnumEntries

class MenuGroupMessenger {

    fun writeMenuGroup(menuGroupEntries: EnumEntries<MenuGroup>) {
        write(Message.MENU_MAIN_INTRODUCE)

        for (entry in menuGroupEntries) {
            println("${entry.ordinal + 1}. ${String.format("%-20s", entry.name)} | ${entry.information}")
        }

        write(Message.MENU_EXIT)
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }
}
