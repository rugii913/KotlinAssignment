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

    fun existsOverlappedReservationByRoomNumber(newReservation: HotelReservationInput): Boolean {
        val targetList = reservationList.asSequence()
            .filter { it.roomNumber == newReservation.roomNumber }
            .filter { it.dateCheckIn < newReservation.dateCheckOut && it.dateCheckOut > newReservation.dateCheckIn }
            .toList()

        if (targetList.isNotEmpty()) { // 날짜가 겹치는지 확인하는 알고리즘 검색 https://way-be-developer.tistory.com/54
            println("해당 날짜에 이미 예약되어있는 방입니다. 다른 날짜를 입력해주세요.")
            return true
        }
        return false
    }

    /*
    // 메서드를 이렇게 만드려다가 반환 타입에 일관성이 없어지는 듯해서 다른 방향으로 전환함
    // 데이터를 반환하려면 확실하게 반환해야될 듯
    // 메시지를 보여주는 부분을 쪼갤 필요성이 느껴짐...
    fun findAllByRoomNumber(roomNumber: Int): List<HotelReservationInput> { // cf. https://revf.tistory.com/270 // 만약 JPA라면... findAllBy와 findBy의 차이
        val listOfSameRoomNumber = reservationList.asSequence().filter { it.roomNumber == roomNumber }
    }
     */
}