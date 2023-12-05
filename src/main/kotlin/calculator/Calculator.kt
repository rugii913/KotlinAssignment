package calculator

class Calculator {
    fun calculate(operation: AbstractOperation, number1: Double, number2: Double): Double {
        return operation.operate(number1, number2)
    }
}
