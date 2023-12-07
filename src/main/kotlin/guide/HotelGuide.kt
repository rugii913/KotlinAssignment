package guide

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object HotelGuide {
    fun guide() {

        while (true) {
            print(
                """
            |
            |호텔예약 프로그램입니다.
            |[메뉴]
            |1. 방예약, 2. 예약목록 출력, 3. 예약목록 (정렬) 출력, 4. 호텔 예약 프로그램 종료, 5. 금액 입금-출금 내역 목록 출력 6. 예약 변경/취소
            |>>> 
            """.trimMargin()
            )
            val serviceNumberInput = MainGuide.numberInputFilter(readln()) ?: continue
            when (serviceNumberInput) {
                1 -> break
                4 -> { println("\n호텔 예약 프로그램을 종료합니다."); return }
                else -> { println("\n${GuideMessages.ALERT_MESSAGE_WRONG_NUMBER}") }
            }
        }

        print("""
        |
        |예약자 분의 성함을 입력해주세요.
        |>>> """.trimMargin())
        val customerName = readln()

        while (true) {
            print("""
            |
            |예약할 방번호를 입력해주세요.
            |>>> """.trimMargin())
            val roomNumber = MainGuide.numberInputFilter(readln()) ?: continue
            if (roomNumber < 100 || roomNumber >= 1000) {
                println("\n올바르지 않은 방번호입니다. 방 번호는 100 ~ 999 사이의 번호입니다.")
                continue
            }
            break
        }

        var dateCheckIn: LocalDate
        while (true) {
            print("""
            |
            |체크인 날짜를 입력해주세요. (표기형식 예: 20231231)
            |>>> """.trimMargin())
            val inputString = readln()

            dateCheckIn = LocalDate.parse(inputString, DateTimeFormatter.BASIC_ISO_DATE) // DateTimeFormatter 기억, 직접 숫자로 처리하고 있었음 - TODO: 예외 처리 - 8자리 넘어가는 경우, 숫자 아닌 문자열 등
            if (dateCheckIn < LocalDate.now()) { // Kotlin 연산자 오버로딩 https://www.devkuma.com/docs/kotlin/operator-overloading/
                println("\n체크인 날짜로 이미 지난 날짜를 선택할 수 없습니다.")
                continue
            }
            break
        }

        while (true) {
            print("""
            |
            |체크아웃 날짜를 입력해주세요. (표기형식 예: 20231231)
            |>>> """.trimMargin())
            val inputString = readln()

            val dateCheckOut = LocalDate.parse(inputString, DateTimeFormatter.BASIC_ISO_DATE) // TODO: 예외 처리 - 8자리 넘어가는 경우 등, 숫자 아닌 문자열 등
            if (dateCheckOut <= dateCheckIn) {
                println("\n체크아웃 날짜는 체크인 날짜와 같거나 이전일 수 없습니다.")
                continue
            }
            break
        }

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
}