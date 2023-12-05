import calculator.*

fun main() {
    println("number1, number2 두 수에 대한 계산을 진행합니다.")
    println("number1 operator number2 형태로 계산합니다. (예: 5 + 3)")

    var number1: Double
    var number2: Double
    var operator: String
    while (true) {
        print("\nnumber1을 입력해주세요: ")
        while (true) {
            try {
                number1 = readln().toDouble()
                break
            } catch (e: NumberFormatException) {
                print("\n잘못된 입력입니다. 숫자를 입력해주세요: ")
            }
        }

        print("\n연산자 operator를 입력해주세요(+, -, /, * 중 선택): ")
        while (true) {
            operator = readln()
            if (operator !in arrayOf("+", "-", "*", "/")) {
                print("\n잘못된 입력입니다. 연산자(+, -, *, / 중 선택)를 입력해주세요: ")
                continue
            }
            break
        }

        print("\nnumber2를 입력해주세요: ")
        while (true) {
            try {
                number2 = readln().toDouble()
                break
            } catch (e: NumberFormatException) {
                print("\n잘못된 입력입니다. 숫자를 입력해주세요: ")
            }
        }

        break
    }

    val calculator = Calculator()
    val result = when (operator) {
        "+" -> calculator.addOperation(AddOperation(), number1, number2)
        "-" -> calculator.subtractOperation(SubtractOperation(), number1, number2)
        "*" -> calculator.multiplyOperation(MultiplyOperation(), number1, number2)
        "/" -> calculator.divideOperation(DivideOperation(), number1, number2)
        else -> {
            throw RuntimeException("잘못된 연산자 입력")
        }
    }

    println("\n결과는 ${result}입니다.")
}
