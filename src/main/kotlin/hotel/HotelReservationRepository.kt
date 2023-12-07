package hotel

import java.util.concurrent.atomic.AtomicInteger

object HotelReservationRepository {
    var previousId: AtomicInteger = AtomicInteger()
    private val reservationList: MutableList<HotelReservationInput> = mutableListOf()

    fun save(newReservationInput: HotelReservationInput) {
        reservationList.add(newReservationInput)
    }

    fun findAll() {
        if (reservationList.isEmpty()) {
            println("현재 호텔 전체에 예약된 내역이 없습니다.")
        }

        for (i in reservationList.indices) {
            val it = reservationList[i]
            println("${it.id}. 사용자: ${it.customerName}, 방번호: ${it.roomNumber}, 체크인: ${it.dateCheckIn}, 체크아웃: ${it.dateCheckOut}")
        }
    }

    fun findAllOrderByDateCheckIn() {
        val sortedList = reservationList.asSequence().sortedBy { it.dateCheckIn }.toList()

        if (sortedList.isEmpty()) {
            println("현재 호텔 전체에 예약된 내역이 없습니다.")
        }
        for (i in sortedList.indices) {
            val it = sortedList[i]
            println("$i. 사용자: ${it.customerName}, 방번호: ${it.roomNumber}, 체크인: ${it.dateCheckIn}, 체크아웃: ${it.dateCheckOut}")
        }
    }
}