import kotlin.math.pow
import kotlin.system.measureTimeMillis

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
        val collectionOfCards = MutableList(input.size) { 0 }
        for (card in input.indices) {
            collectionOfCards[card] = collectionOfCards[card] + 1
            val winningNumbers = input[card].replace("   ", " ").replace("  ", " ")
                .split(": ")[1].split(" | ")[0].split(" ")
            val numbers = input[card].split(": ")[1].split(" | ")[1].replace("  ", " ")
                .split(" ")
            for (i in 0 until collectionOfCards[card]) {
                var hasWinningNumber = 0
                numbers.forEach() {
                    if (winningNumbers.contains(it)) {
                        hasWinningNumber++
                        collectionOfCards[card + hasWinningNumber] = collectionOfCards[card + hasWinningNumber] + 1
                    }
                }
            }
        }
        return collectionOfCards.sum()
    }

    val testInput = readInput("TestDay4")

    val result = part1(testInput)
    println("result => $result")

    val result2 = part2(testInput)
    println("result part2 => $result2")
}
