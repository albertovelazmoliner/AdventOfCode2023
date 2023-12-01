import java.util.stream.IntStream.range

fun main() {
    fun part1(input: List<String>): Int {
        var calibrationSum = 0
        for (text in input) {
            val numbers = text.replace("[^0-9]".toRegex(), "")
            val calibration = "${numbers[0]}${numbers[numbers.length - 1]}"
            calibrationSum += calibration.toInt()
        }
        return calibrationSum
    }

    val translationMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun part2(input: List<String>): Int {
        var calibrationSum = 0
        for (line in input) {
            val numbers = mutableListOf<String>()

            for (i in line.indices) {
                if (line[i].isDigit()) {
                    numbers.add(line[i].toString())
                } else {
                    val parsedNumber = translationMap.keys.firstOrNull { line.substring(i).startsWith(it) }
                    if (parsedNumber != null) {
                        numbers.add(translationMap.getValue(parsedNumber))
                    }
                }
            }
            calibrationSum += "${numbers.first()}${numbers.last()}".toInt()
        }
        return calibrationSum
    }

    val testInput = readInput("Test1Day1")

    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)
}
