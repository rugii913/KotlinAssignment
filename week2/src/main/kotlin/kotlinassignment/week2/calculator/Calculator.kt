package calculator

object Calculator {
    fun calculate(operation: Operation, number1: Double, number2: Double): Double {
        return operation.operate(number1, number2)
    }
}
