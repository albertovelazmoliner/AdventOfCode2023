import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        var totalWorth = 0
        for (card in input) {
            val winningNumbers = card.replace("   ", " ").replace("  ", " ")
                .split(": ")[1].split(" | ")[0].split(" ")
            val numbers = card.split(": ")[1].split(" | ")[1].split(" ")
            var hasWinningNumber = 0
            numbers.forEach() {
                if (winningNumbers.contains(it)) hasWinningNumber++
            }
            totalWorth += if (hasWinningNumber > 0) 2.0.pow(hasWinningNumber - 1).toInt() else 0
        }
        return totalWorth
    }

    fun part2(input: List<String>): Int {
        val mapOfCards = mutableMapOf<String, Int>()
        for (card in input.indices) {
            if (mapOfCards.containsKey("$card")) mapOfCards["$card"] = mapOfCards["$card"]!! + 1
            else mapOfCards["$card"] = 1
            val winningNumbers = input[card].replace("   ", " ").replace("  ", " ")
                .split(": ")[1].split(" | ")[0].split(" ")
            val numbers = input[card].split(": ")[1].split(" | ")[1].replace("  ", " ")
                .split(" ")
            for (i in 0 until mapOfCards["$card"]!!) {
                var hasWinningNumber = 0
                numbers.forEach() {
                    if (winningNumbers.contains(it)) {
                        hasWinningNumber++
                        if (mapOfCards.containsKey("${card + hasWinningNumber}")) {
                            mapOfCards["${card + hasWinningNumber}"] = mapOfCards["${card + hasWinningNumber}"]!! + 1
                        } else {
                            mapOfCards["${card + hasWinningNumber}"] = 1
                        }
                    }
                }
            }
        }
        return mapOfCards.values.sum()
    }

    val testInput = readInput("TestDay4")

    val result = part1(testInput)
    println("result => $result")

    val result2 = part2(testInput)
    println("result part2 => $result2")

}
