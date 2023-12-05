package calculator

abstract class AbstractOperation {
    abstract fun operate(number1: Double, number2: Double): Double
}

class AddOperation: AbstractOperation() {
    override fun operate(number1: Double, number2: Double) = number1 + number2
}

class SubtractOperation: AbstractOperation() {
    override fun operate(number1: Double, number2: Double) = number1 - number2
}

class MultiplyOperation: AbstractOperation() {
    override fun operate(number1: Double, number2: Double) = number1 * number2
}

class DivideOperation: AbstractOperation() {
    override fun operate(number1: Double, number2: Double) = number1 / number2
}
