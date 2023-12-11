package calculator

enum class OperationsEnum(
    val operatorString: String,
): Operation {
    ADDITION("+") {
        override fun operate(number1: Double, number2: Double) = number1 + number2
    },
    SUBTRACTION("-") {
        override fun operate(number1: Double, number2: Double) = number1 - number2
    },
    MULTIPLICATION("*") {
        override fun operate(number1: Double, number2: Double) = number1 * number2
    },
    DIVISION("/") {
        override fun operate(number1: Double, number2: Double) = number1 / number2
    };
}