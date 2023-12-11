package calculator

interface Operation {
    fun operate(number1: Double, number2: Double): Double
}

object AddOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 + number2
}

object SubtractOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 - number2
}

object MultiplyOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 * number2
}

object DivideOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 / number2
}

/*
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
 */
