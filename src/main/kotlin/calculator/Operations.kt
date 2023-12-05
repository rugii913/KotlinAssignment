package calculator

interface Operation {
    fun operate(number1: Double, number2: Double): Double
}

class AddOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 + number2
}

class SubtractOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 - number2
}

class MultiplyOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 * number2
}

class DivideOperation: Operation {
    override fun operate(number1: Double, number2: Double) = number1 / number2
}
