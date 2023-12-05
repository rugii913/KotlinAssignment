class Calculator {

    fun calculate(number1: Int, number2: Int, operator: String): Int {
        return when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> number1 / number2
            "%" -> number1 % number2
            else -> {
                throw RuntimeException("잘못된 연산자 입력")
            }
        }
    }
}
