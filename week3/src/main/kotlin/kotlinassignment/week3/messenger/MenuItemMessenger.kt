package kotlinassignment.week3.messenger

import kotlinassignment.week3.menu.menuGroup.Burgers
import kotlinassignment.week3.menu.menuGroup.FrozenCustard
import kotlinassignment.week3.menu.menuGroup.MenuGroup
import kotlinassignment.week3.menu.menuItem.MenuItem

class MenuItemMessenger {

    fun writeMenuItems(menuList: List<MenuItem>, menuGroup: MenuGroup) {

        when (menuGroup) {
            // TODO 매끄럽지 않은 처리, 여기서 구현체를 알 필요가 있을까?
            Burgers -> write(Message.MENU_GROUP_BURGERS_INTRODUCE)
            FrozenCustard -> write(Message.MENU_GROUP_FROZEN_CUSTARD_INTRODUCE)
        }

        for (index in menuList.indices) {
            println("${index + 1}. ${String.format("%-28s", menuList[index].name)} | W ${menuList[index].price} | ${menuList[index].information}")
        }

        write(Message.MENU_BACK)
    }

    fun write(message: Message) {
        println(message.messegeValue)
    }
}
