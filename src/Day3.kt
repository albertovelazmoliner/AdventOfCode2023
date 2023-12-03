import kotlin.math.pow
import kotlin.math.sqrt

fun main() {

    fun part1(input: List<String>): Int {
        var totalSum = 0
        val rows = input.size - 1
        val numbers = mutableListOf<Pair<Int, MutableList<Pair<Int,Int>>>>()
        val symbols = mutableListOf<Pair<Int, Int>>()
        for (i in 0..rows) {
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
                    if (number.isNotEmpty()) {
                        numbers.add(Pair(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = mutableListOf()
                    }
                }

                if (row[j] != ".".single() && !row[j].isDigit()) {
                    symbols.add(Pair(i, j))
                }

            }
        }

        for (symbol in symbols) {
            for (number in numbers) {
                for (position in number.second) {
                    val distance = sqrt((symbol.first - position.first).toDouble().pow(2) + (symbol.second - position.second).toDouble().pow(2))
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
        val rows = input.size - 1
        val numbers = mutableListOf<Pair<Int, MutableList<Pair<Int,Int>>>>()
        val symbols = mutableListOf<Pair<Int, Int>>()
        for (i in 0..rows) {
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
                    if (number.isNotEmpty()) {
                        numbers.add(Pair(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = mutableListOf()
                    }
                }

                if (row[j] == "*".single() && !row[j].isDigit()) {
                    symbols.add(Pair(i, j))
                }

            }
        }

        for (symbol in symbols) {
            for (number in numbers) {
                for (position in number.second) {
                    val distance = sqrt((symbol.first - position.first).toDouble().pow(2) + (symbol.second - position.second).toDouble().pow(2))
                    if (distance<2) {
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
        println(gears)
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