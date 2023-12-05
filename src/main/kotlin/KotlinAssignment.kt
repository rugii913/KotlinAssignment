import calculator.*

fun main() {
    println("number1, number2 두 수에 대한 계산을 진행합니다.")
    println("number1 operator number2 형태로 계산합니다. (예: 5 + 3)")

    var number1: Double
    var number2: Double
    var operator: String
    val selectedOperation: OperationsEnum
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

        /*
        print("\n연산자 operator를 입력해주세요(+, -, /, * 중 선택): ")
        while (true) {
            operator = readln()
            if (operator !in arrayOf("+", "-", "*", "/")) {
                print("\n잘못된 입력입니다. 연산자(+, -, *, / 중 선택)를 입력해주세요: ")
                continue
            }
            break
        }
         */
        print("\n연산자 operator를 입력해주세요(+, -, /, * 중 선택): ")
        operator@ while (true) {
            operator = readln()
            /*
            if (operator !in OperationsEnum.entries.map { operation -> operation.operatorString }) {
                print("\n잘못된 입력입니다. 연산자(+, -, *, / 중 선택)를 입력해주세요: ")
                continue
            }
             */
            val nullCheck = OperationsEnum.entries.associateBy { it.operatorString }.get(operator)
            if (nullCheck == null) {
                print("\n잘못된 입력입니다. 연산자(+, -, *, / 중 선택)를 입력해주세요: ")
                continue
            }
            selectedOperation = nullCheck
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

    /*
    val result = when (operator) {
        "+" -> Calculator.calculate(AddOperation, number1, number2)
        "-" -> Calculator.calculate(SubtractOperation, number1, number2)
        "*" -> Calculator.calculate(MultiplyOperation, number1, number2)
        "/" -> Calculator.calculate(DivideOperation, number1, number2)
        else -> {
            throw RuntimeException("잘못된 연산자 입력")
        }
    }
     */
    val result = selectedOperation.operate(number1, number2)
    println("\n결과는 ${result}입니다.")
}
