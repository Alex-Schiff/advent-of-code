package aockt.y2023

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

/**
 * A test for a fictitious puzzle.
 *
 * ```text
 * The input is a string of numbers separated by a comma.
 * Part 1: Return the sum of the odd numbers.
 * Part 2: Return the product of the numbers.
 * ```
 */
@AdventDay(2023, 1, "Trebuchet?!")
class Y2023D01Test :
    AdventSpec<Y2023D01>({
        partOne {
            "1abc2" shouldOutput 12
            "pqr3stu8vwx" shouldOutput 38
            "treb7uchet" shouldOutput 77
            """
1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
""" shouldOutput 142
        }
    })
