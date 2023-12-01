package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D01 : Solution {
    private fun parseInputForPartOne(input: String): Int =
        input
            .lines()
            .map {
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

    val digit = """one|two|three|four|five|six|seven|eight|nine|\d"""
    private fun parseInputForPartTwo(input: String): Int =
        input
            .lines()
            .map {
                "^((?!$digit).)*(?<firstDigit>$digit?)(?<mainBody>.*)(?<secondDigit>$digit)((?!$digit).)*$"
                    .toRegex()
                    .find(it)!!
            }
            .map {
               Pair((if (it.groups["firstDigit"]!!.value.isBlank()) it.groups["secondDigit"]!!.value
                else it.groups["firstDigit"]!!.value), it.groups["secondDigit"]!!.value)
            }
            .map {
               Pair( try {
                    Digits.valueOf(it.first).digitValue
                } catch {
                    it.first.toInt()
                }, try {
                    Digits.valueOf(it.second).digitValue
                } catch {
                    it.second.toInt()
                })
            }
            .map{ "$it.first$it.second".toInt() }
            .sum()
            

    override fun partOne(input: String) = parseInputForPartOne(input)

    override fun partTwo(input: String) = parseInputForPartTwo(input)
}
