package guide

import hotel.HotelReservationService

object HotelGuide {
    var continueFlag = true

    fun guide() {
        while (true) {
            print(
                """
            |
            |호텔예약 프로그램입니다.
            |[메뉴]
            |1. 방예약, 2. 예약목록 출력, 3. 예약목록(정렬) 출력, 4. 호텔 예약 프로그램 종료, 5. 금액 입금-출금 내역 목록 출력, 6. 예약 변경/취소
            |>>> 
            """.trimMargin()
            )

            val serviceNumberInput = MainGuide.numberInputFilter(readln()) ?: continue

            when (serviceNumberInput) {
                1 -> HotelReservationService.reserve()
                2 -> HotelReservationService.showReservations()
                3 -> HotelReservationService.showReservationsSorted()
                4 -> exit()
                else -> { println("\n${GuideMessages.ALERT_MESSAGE_WRONG_NUMBER}") }
            }
        }
    }

    fun exit() {
        println("\n호텔 예약 프로그램을 종료합니다.")
        continueFlag = false
    }
}