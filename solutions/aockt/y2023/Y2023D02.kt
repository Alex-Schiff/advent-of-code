package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D02 : Solution {
    private fun findRequiredDice(line: String, color: String): Int {
        return Regex("\\d+(?= $color)").findAll(line).toList().maxOfOrNull { it.value.toInt() } ?: 0
    }

    override fun partOne(input: String): Int =
        input
            .lines()
            .mapNotNull { line ->
                // Determine which games would have been possible if the bag had been loaded with
                // only
                // 12 red cubes, 13 green cubes, and 14 blue cubes.
                if (
                    findRequiredDice(line, "red") <= 12 &&
                        findRequiredDice(line, "green") <= 13 &&
                        findRequiredDice(line, "blue") <= 14
                ) {
                    // game is possible, grab the game # which is the first num in the line
                    Regex("\\d+").find(line)!!.value.toInt()
                } else {
                    null // not possible so use null to drop this game from our mapping
                }
            }
            .sum()

    override fun partTwo(input: String): Int =
        input
            .lines()
            .map {
                val redNeeded = findRequiredDice(it, "red")
                val greenNeeded = findRequiredDice(it, "green")
                val blueNeeded = findRequiredDice(it, "blue")
                redNeeded * greenNeeded * blueNeeded
            }
            .sum()
}
