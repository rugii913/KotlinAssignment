package calculator

class Calculator {

    fun addOperation(operation: AddOperation, number1: Double, number2: Double): Double {
        return operation.operate(number1, number2)
    }

    fun subtractOperation(operation: SubtractOperation, number1: Double, number2: Double): Double {
        return operation.operate(number1, number2)
    }

    fun multiplyOperation(operation: MultiplyOperation, number1: Double, number2: Double): Double {
        return operation.operate(number1, number2)
    }

    fun divideOperation(operation: DivideOperation, number1: Double, number2: Double): Double {
        return operation.operate(number1, number2)
    }
}
