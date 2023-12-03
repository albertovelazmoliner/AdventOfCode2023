import kotlin.math.pow
import kotlin.math.sqrt

fun main() {

    fun distanceBetweenPoints(symbol: Pair<Int, Int>, position: Pair<Int, Int>) =
        sqrt((symbol.first - position.first).toDouble().pow(2) + (symbol.second - position.second).toDouble().pow(2))

    fun part1(input: List<String>): Int {
        var totalSum = 0
        val numbers = mutableListOf<Pair<Int, MutableList<Pair<Int,Int>>>>()
        val symbols = mutableListOf<Pair<Int, Int>>()
        for (i in input.indices) {
            val row = input[i]
            var number = ""
            var numberPositions = mutableListOf<Pair<Int, Int>>()
            for (j in row.indices) {
                if (row[j].isDigit()) {
                    number += row[j].toString()
                    numberPositions.add(Pair(i, j))
                    if (j == row.length - 1) {
                        numbers.add(Pair(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = mutableListOf()
                    }
                } else {
                    if (row[j] != ".".single()) symbols.add(Pair(i, j))
                    if (number.isNotEmpty()) {
                        numbers.add(Pair(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = mutableListOf()
                    }
                }
            }
        }

        for (symbol in symbols) {
            for (number in numbers) {
                for (position in number.second) {
                    val distance = distanceBetweenPoints(symbol, position)
                    if (distance<2) {
                        totalSum += number.first
                        break
                    }
                }
            }
        }
        return totalSum
    }

    fun part2(input: List<String>): Int {
        var engineSchematic = 0
        val gears = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
        val numbers = mutableListOf<Pair<Int, MutableList<Pair<Int,Int>>>>()
        val symbols = mutableListOf<Pair<Int, Int>>()
        for (i in input.indices) {
            val row = input[i]
            var number = ""
            var numberPositions = mutableListOf<Pair<Int, Int>>()
            for (j in row.indices) {
                if (row[j].isDigit()) {
                    number += row[j].toString()
                    numberPositions.add(Pair(i, j))
                    if (j == row.length - 1) {
                        numbers.add(Pair(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = mutableListOf()
                    }
                } else {
                    if (row[j] == "*".single()) symbols.add(Pair(i, j))
                    if (number.isNotEmpty()) {
                        numbers.add(Pair(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = mutableListOf()
                    }
                }
            }
        }

        for (symbol in symbols) {
            for (number in numbers) {
                for (position in number.second) {
                    val distance = distanceBetweenPoints(symbol, position)
                    if (distance < 2) {
                        if (gears.containsKey(Pair(symbol.first, symbol.second))) {
                            gears[Pair(symbol.first, symbol.second)]?.add(number.first)
                        } else {
                            gears[Pair(symbol.first, symbol.second)] = mutableListOf(number.first)
                        }
                        break
                    }
                }
            }
        }
        for (gear in gears) {
            if (gear.value.size == 2) {
                engineSchematic += gear.value[0] * gear.value[1]
            }
        }
        return engineSchematic
    }

    val testInput = readInput("TestDay3")

    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)
}
