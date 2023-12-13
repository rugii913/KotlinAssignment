package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.Menu

class MenuGroupMessenger {

    fun writeMenu(menuList: List<Menu>) {
        write(Message.MENU_MAIN_INTRODUCE)

        for (index in menuList.indices) {
            println("${index + 1}. ${String.format("%-20s", menuList[index].name)} | ${menuList[index].information}")
        }

        write(Message.MENU_EXIT)
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }
}
