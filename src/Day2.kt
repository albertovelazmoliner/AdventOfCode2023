import java.util.stream.IntStream.range

fun main() {

    val mapColors = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun part1(input: List<String>): Int {
        var idSum = 0
        for (text in input) {
            val data = text.split(":")
            val id = data[0].split(" ")[1].toInt()
            val games = data[1].split("; ")
            var isValid = true
            for (game in games) {
                val gameCubesSets = game.split(", ")
                for (gameCubesSet in gameCubesSets) {
                    val dataSet = gameCubesSet.trim().split(" ")
                    val colorLimit = dataSet[1]
                    if (dataSet[0].toInt() > mapColors[colorLimit]!!) {
                        isValid = false
                        break
                    }
                    if (!isValid) break
                }
            }
            if (isValid) idSum += id
        }
        return idSum
    }


    fun part2(input: List<String>): Int {
        var powerSum = 0
        for (text in input) {
            val games = text.split(":")[1].split("; ")
            val powerMap = mutableMapOf<String, Int>()
            for (game in games) {
                val gameCubesSets = game.split(", ")
                for (gameCubesSet in gameCubesSets) {
                    val dataSet = gameCubesSet.trim().split(" ")
                    val colorLimit = dataSet[1]
                    if (powerMap.containsKey(colorLimit)) {
                        if (dataSet[0].toInt() > powerMap[colorLimit]!!) {
                            powerMap[colorLimit] = dataSet[0].toInt()
                        }
                    } else {
                        powerMap[colorLimit] = dataSet[0].toInt()
                    }
                }
            }

            powerSum = powerMap.values.toList().reduce {
                    power, element -> power * element
            }
        }
        return powerSum
    }

    val testInput = readInput("Test1Day2")

    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)
}
