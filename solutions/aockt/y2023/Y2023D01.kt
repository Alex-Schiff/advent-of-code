package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D01 : Solution {
    private fun parseInput(input: String): Int =
        input
            .lines()
            .map { it ->
                """^(?<prefix>[^\d]*)(?<firstDigit>\d?)(?<mainBody>.*)(?<secondDigit>\d)(?<suffix>[^\d]*)$"""
                    .toRegex()
                    .find(it)!!
            }
            .map {
                (if (it.groups["firstDigit"]!!.value.isBlank()) it.groups["secondDigit"]!!.value
                else it.groups["firstDigit"]!!.value) + it.groups["secondDigit"]!!.value
            }
            .map { it.toInt() }
            .sum()

    override fun partOne(input: String) = parseInput(input)
}
