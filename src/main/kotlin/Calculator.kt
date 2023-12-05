class Calculator {
    fun calculate() {
        println("number1, number2 두 수에 대한 계산을 진행합니다.")
        println("number1 operator number2 형태로 계산합니다. (예: 5 + 3)")

        var number1: Int
        var number2: Int
        var operator: String
        while (true) {
            print("\nnumber1을 입력해주세요: ")
            while (true) {
                try {
                    number1 = readln().toInt()
                    break
                } catch (e: NumberFormatException) {
                    print("\n잘못된 입력입니다. 숫자를 입력해주세요: ")
                }
            }

            print("\n연산자 op를 입력해주세요(+, -, /, * 중 선택): ")
            while (true) {
                operator = readln()
                if (operator !in arrayOf("+", "-", "*", "/", "%")) {
                    print("\n잘못된 입력입니다. 연산자(+, -, *, /, % 중 선택)를 입력해주세요: ")
                    continue
                }
                break
            }

            print("\nnumber2를 입력해주세요: ")
            while (true) {
                try {
                    number2 = readln().toInt()
                    break
                } catch (e: NumberFormatException) {
                    print("\n잘못된 입력입니다. 숫자를 입력해주세요: ")
                }
            }

            break
        }

        when (operator) {
            "+" -> println("\n결과는 ${number1 + number2}입니다.")
            "-" -> println("\n결과는 ${number1 - number2}입니다.")
            "*" -> println("\n결과는 ${number1 * number2}입니다.")
            "/" -> println("\n결과는 ${number1 / number2}입니다.")
            "%" -> println("\n결과는 ${number1 % number2}입니다.")
        }
    }
}
