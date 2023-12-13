package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.menuItem.MenuItem

class BurgersMessenger {

    fun writeMenu(menuList: List<MenuItem>) {
        write(Message.MENU_GROUP_BURGERS_INTRODUCE)

        for (index in menuList.indices) {
            println("${index + 1}. ${String.format("%-20s", menuList[index].name)} | W ${menuList[index].price} | ${menuList[index].information}")
        }

        write(Message.MENU_BACK)
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }
}
