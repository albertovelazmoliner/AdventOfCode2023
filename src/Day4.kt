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
    
    val testInput = readInput("TestDay4")

    val result = part1(testInput)
    println("result => $result")

}
