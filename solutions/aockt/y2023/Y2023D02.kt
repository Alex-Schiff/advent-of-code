package aockt.y2023

import io.github.jadarma.aockt.core.Solution
import aockt.y2023.Match

object Y2023D02 : Solution {
    private const val MAIN_REGEX_STRING =
        """^(?<gamePrefix>Game\s)(?<gameId>[1-9]|[1-9]\d|100):\s(?<matches>.*)$"""
        private const val RED_REGEX_STRING = """^(?:.*)(?<count>[1-9]|[1-9]\d)(?<color>\sred)?(?:.*)$"""
        private const val GREEN_REGEX_STRING = """^(?:.*)(?<count>[1-9]|[1-9]\d)(?<color>\sgreen)?(?:.*)$"""
        private const val BLUE_REGEX_STRING = """^(?:.*)(?<count>[1-9]|[1-9]\d)(?<color>\sblue)?(?:.*)$"""

    private fun parseMatchesString(matchesString: String) = matchesString.split(";").map{
            val redMatch = RED_REGEX_STRING.toRegex().find(it)!!
            val greenMatch = GREEN_REGEX_STRING.toRegex().find(it)!!
            val blueMatch = BLUE_REGEX_STRING.toRegex().find(it)!!
            return Match(
                if(redMatch.groups["color"]!!.value.isBlank()) 0 else redMatch.groups["count"]!!.value.toInt(),
                if(greenMatch.groups["color"]!!.value.isBlank()) 0 else greenMatch.groups["count"]!!.value.toInt(),
                if(blueMatch.groups["color"]!!.value.isBlank()) 0 else blueMatch.groups["count"]!!.value.toInt()
                )
        }.filter{it.isValid()}
    

    override fun partOne(input: String) = input.lines().map { MAIN_REGEX_STRING.toRegex().find(it)!! }.map{
        it.groups["gameId"]!!.value.toInt() to parseMatchesString(it.groups["matches"]!!.value)}
        
    }
