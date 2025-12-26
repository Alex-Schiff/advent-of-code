package aockt.y2023

import io.github.jadarma.aockt.core.Solution

object Y2023D03 : Solution {
  override fun partOne(input: String): Int {
    val grid = input.toGrid()
    return input
      .lines()
      .mapIndexed { lineIndex, line -> line.extractValidNumbersSum(grid, lineIndex) }
      .sum()
  }

  override fun partTwo(input: String): Int {
    val grid = input.toGrid()
    return grid
      .extractAllStarPositions()
      .associateWith { grid.extractAllAdjacentNumbers(it) }
      .filter { it.value.size == 2 }
      .map { it.value.fold(1) { total, next -> total * next } }
      .sum()
  }
}
