package kotlinassignment.filter

class IntInputFilter {

    fun filterToInt(inputString: String): Int? {
        val input = inputString.toIntOrNull()
        if (input == null) println("\n잘못된 입력입니다. 숫자를 입력해주세요.")

        return input
    }
}