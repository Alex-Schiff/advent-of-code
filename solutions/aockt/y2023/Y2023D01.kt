package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D01 : Solution {
    private fun parseInputForPartOne(input: String): Int =
        input
            .lines()
            .map {
                """^(?<prefix>\D*)(?<firstDigit>\d?)(?<mainBody>.*)(?<secondDigit>\d)(?<suffix>\D*)$"""
                    .toRegex()
                    .find(it)!!
            }
            .map {
                (it.groups["firstDigit"]!!.value.ifBlank { it.groups["secondDigit"]!!.value }) +
                    it.groups["secondDigit"]!!.value
            }
            .sumOf { it.toInt() }

    private const val DIGIT = """one|two|three|four|five|six|seven|eight|nine|\d"""

    private fun parseInputForPartTwo(input: String): Int =
        input
            .lines()
            .asSequence()
            .map {
                "^((?!$DIGIT).)*(?<firstDigit>$DIGIT?)(?<mainBody>.*)(?<secondDigit>$DIGIT)((?!$DIGIT).)*$"
                    .toRegex()
                    .find(it)!!
            }
            .map {
                Pair(
                    (it.groups["firstDigit"]!!.value.ifBlank { it.groups["secondDigit"]!!.value }),
                    it.groups["secondDigit"]!!.value
                )
            }
            .map {
                Pair(
                    try {
                        Digits.valueOf(it.first).digitValue
                    } catch (e: IllegalArgumentException) {
                        it.first.toInt()
                    },
                    try {
                        Digits.valueOf(it.second).digitValue
                    } catch (e: IllegalArgumentException) {
                        it.second.toInt()
                    }
                )
            }
            .map { "${it.first}${it.second}".toInt() }
            .sum()

    override fun partOne(input: String) = parseInputForPartOne(input)

    override fun partTwo(input: String) = parseInputForPartTwo(input)
}
