package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D02 : Solution {
    private const val MAIN_REGEX_STRING =
        """^\s*(?<gamePrefix>Game)\s(?<gameId>[1-9]|[1-9]\d|100):\s(?<matches>.*)$"""
    private const val RED_REGEX_STRING = """^.*\s(?<count>[1-9]|[1-9]\d)\s(?<color>red).*$"""
    private const val GREEN_REGEX_STRING = """^.*\s(?<count>[1-9]|[1-9]\d)\s(?<color>green).*$"""
    private const val BLUE_REGEX_STRING = """^.*\s(?<count>[1-9]|[1-9]\d)\s(?<color>blue).*$"""

    private fun parseMatchesString(matchesString: String): List<Match> =
        matchesString.split(";").map {
            val redMatch = RED_REGEX_STRING.toRegex().find(it)
            val redValue: Int =
                if (redMatch?.groups?.get("color") == null) 0
                else redMatch.groups["count"]!!.value.toInt()

            val greenMatch = GREEN_REGEX_STRING.toRegex().find(it)
            val greenValue: Int =
                if (greenMatch?.groups?.get("color") == null) 0
                else greenMatch.groups["count"]!!.value.toInt()

            val blueMatch = BLUE_REGEX_STRING.toRegex().find(it)
            val blueValue: Int =
                if (blueMatch?.groups?.get("color") == null) 0
                else blueMatch.groups["count"]!!.value.toInt()
            Match(redValue, greenValue, blueValue)
        }

    override fun partOne(input: String): Int =
        input
            .lines()
            .map { MAIN_REGEX_STRING.toRegex().find(it)!! }
            .map {
                it.groups["gameId"]!!.value.toInt() to
                    parseMatchesString(it.groups["matches"]!!.value)
            }
            .filter { it.second.all { match -> match.isValid() } }
            .sumOf { it.first }
}
