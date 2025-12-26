package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D02 : Solution {
  private fun findRequiredDice(line: String, color: String): Int =
    "\\d+(?=\\s$color)".toRegex().findAll(line).toList().maxOfOrNull { it.value.toInt() } ?: 0

  override fun partOne(input: String): Int =
    input
      .lines()
      .mapNotNull {
        if (
          findRequiredDice(it, "red") <= 12 &&
            findRequiredDice(it, "green") <= 13 &&
            findRequiredDice(it, "blue") <= 14
        )
          """\d+""".toRegex().find(it)!!.value.toInt()
        else null
      }
      .sum()

  override fun partTwo(input: String): Int =
    input.lines().sumOf {
      findRequiredDice(it, "red") * findRequiredDice(it, "green") * findRequiredDice(it, "blue")
    }
}
