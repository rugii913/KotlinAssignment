package hotel

object HotelReservationService {
    fun reserve() {
        val newReservation = HotelReservationInput.Builder()
            .customerName().roomNumber().dateCheckIn().dateCheckOut().build()

        HotelReservationRepository.hotelReservationList.add(newReservation)

        // Kotlin 랜덤 참고 - https://codechacha.com/ko/kotlin-random-number/
        // - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/random.html
        // 이 Random Default랑 Random은 뭔지 찾아볼 것
        // - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/-default/
        var deposit = (50_000..200_000).random() / 10_000 * 10_000 // deposit 줄게 해놓지는 않았음
        val price = (10_000..deposit).random() / 10_000 * 10_000
        println("결제 전 보유액 = $deposit")
        println("결제 금액 = $price")
        println("결제 후 잔액 = ${deposit - price}")

        println("\n호텔 예약이 완료되었습니다.\n")
    }

    fun showReservations() {
        println("\n호텔 예약자 목록입니다.")

        val hotelReservationList = HotelReservationRepository.hotelReservationList
        if (hotelReservationList.isEmpty()) {
            println("현재 호텔 전체에 예약된 내역이 없습니다.")
        }
        for (it in HotelReservationRepository.hotelReservationList) {
            println("${it.id}. 사용자: ${it.customerName}, 방번호: ${it.roomNumber}, 체크인: ${it.dateCheckIn}, 체크아웃: ${it.dateCheckOut}")
        }
    }

    fun showReservationsSorted() {
        println("\n호텔 예약자 목록입니다. (체크인 날짜 순 정렬)")

        val hotelReservationList = HotelReservationRepository.hotelReservationList.asSequence().sortedBy { it.dateCheckIn }.toList()
        if (hotelReservationList.isEmpty()) {
            println("현재 호텔 전체에 예약된 내역이 없습니다.")
        }
        for (i in hotelReservationList.indices) {
            val it = hotelReservationList[i]
            println("$i. 사용자: ${it.customerName}, 방번호: ${it.roomNumber}, 체크인: ${it.dateCheckIn}, 체크아웃: ${it.dateCheckOut}")
        }
    }
}