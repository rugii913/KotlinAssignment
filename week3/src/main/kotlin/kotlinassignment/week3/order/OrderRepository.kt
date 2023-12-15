package kotlinassignment.week3.order

class OrderRepository {
    private val orderList = mutableListOf<Order>()

    fun save(order: Order) {
        orderList.add(order)
    }

    fun count(): Int {
        return orderList.size
    }
}
