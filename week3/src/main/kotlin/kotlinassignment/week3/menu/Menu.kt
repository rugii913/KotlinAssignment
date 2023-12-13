package kotlinassignment.week3.menu

interface Menu {
    /*
    * TODO 현재 Menu와 MenuGroup이 같다. MenuGroup, MenuItem을 한 번에 묶는 타입이 필요해서 Menu를 만들었을 뿐이다.
    *  - 더 나은 방법이 있는지 생각할 것
    * */

    val name: String
    val information: String
}