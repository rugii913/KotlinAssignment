package hotel

import guide.MainGuide
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HotelReservationInput(
    val customerName: String,
    val roomNumber: Int,
    val dateCheckIn: LocalDate,
    val dateCheckOut: LocalDate,
) {
    val id: Int = HotelReservationRepository.previousId.incrementAndGet()

    // TODO: Builder 패턴, apply 알아보기
    data class Builder (
        var customerName: String = "",
        var roomNumber: Int = -1,
        var dateCheckIn: LocalDate = LocalDate.MAX,
        var dateCheckOut: LocalDate = LocalDate.MAX,
    ){
        fun customerName() = apply {
            while (true) {
                print("""
                |
                |예약자 분의 성함을 입력해주세요.
                |>>> """.trimMargin())

                val customerName = readln()
                if (customerName.isBlank()) {
                    println("\n예약자 성함은 반드시 입력되어야 합니다.")
                    continue
                }

                this.customerName = customerName
                break
            }
        }

        fun roomNumber() = apply {
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

                this.roomNumber = roomNumber
                break
            }
        }

        fun dateCheckIn() = apply {
            while (true) {
                print("""
                |
                |체크인 날짜를 입력해주세요. (표기형식 예: 20231231)
                |>>> """.trimMargin())

                val inputString = readln()
                val dateCheckIn = LocalDate.parse(inputString, DateTimeFormatter.BASIC_ISO_DATE) // DateTimeFormatter 기억, 직접 숫자로 처리하고 있었음 - TODO: 예외 처리 - 8자리 넘어가는 경우, 숫자 아닌 문자열 등
                if (dateCheckIn < LocalDate.now()) { // Kotlin 연산자 오버로딩 https://www.devkuma.com/docs/kotlin/operator-overloading/
                    println("\n체크인 날짜로 이미 지난 날짜를 선택할 수 없습니다.")
                    continue
                }

                this.dateCheckIn = dateCheckIn
                break
            }
        }

        fun dateCheckOut() = apply {
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
        }

        fun build() = HotelReservationInput(customerName, roomNumber, dateCheckIn, dateCheckOut)
    }

    /*
    - TODO: while 내에서 input 체크하는 부분을 아예 메서드로 분리하고 싶어서 고민 중 
    fun checkInputDataByWhileStatement(inputTrial: Function<Any>) { // Function? Supplier
        while (true) {
            // input provocation message
            // if (predicate) { rong input message => continue }
            // break
        }
    }*/
}
