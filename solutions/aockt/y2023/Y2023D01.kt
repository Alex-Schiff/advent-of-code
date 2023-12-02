package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D01 : Solution {
    private const val DIGIT = """one|two|three|four|five|six|seven|eight|nine|\d"""
    private val DIGITS_MAP: Map<String, Int> =
        mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

    override fun partOne(input: String) =
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

    override fun partTwo(input: String) =
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
}
