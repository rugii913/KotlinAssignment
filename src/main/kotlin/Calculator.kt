class Calculator {

    fun calculate(number1: Double, number2: Double, operator: String): Double {
        return when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> number1 / number2
            else -> {
                throw RuntimeException("잘못된 연산자 입력")
            }
        }
    }
}
