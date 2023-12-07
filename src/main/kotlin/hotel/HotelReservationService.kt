package hotel

object HotelReservationService {
    fun reserve() {
        val newReservation = HotelReservationInput.Builder()
            .customerName().roomNumber().dateCheckIn().dateCheckOut().build()

        HotelReservationRepository.save(newReservation)

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
        HotelReservationRepository.findAll()
    }

    fun showReservationsSorted() {
        println("\n호텔 예약자 목록입니다. (체크인 날짜 순 정렬)")
        HotelReservationRepository.findAllOrderByDateCheckIn()
    }
    
    // 원래는 여기에 showReservationsWhichHasSameRoomNumber 이런 식으로 만드려고 했으나,
    // 방 번호가 같은 목록을 가져오는 일, 목록에서 중복되는 날짜가 있는지 확인하는 일, 검증 관련 등 로직이 섞일 것으로 보임
    // 이 클래스의 메서드들이 복잡해질 듯하여 역할을 Repository로 분리함
    // ==> 결론적으로 reservationList 필드에 접근하는 것을 repository에서만 하도록 만듦(private으로)
}