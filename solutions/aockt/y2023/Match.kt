package aockt.y2023

/** Match is a class that holds information about how many balls were withdrawn from the bag. */
public data class Match(val redCount: Int, val greenCount: Int, val blueCount: Int)

val MATCH = Match(12, 13, 14)

fun Match.isValid() =
    redCount <= MATCH.redCount && greenCount <= MATCH.greenCount && blueCount <= MATCH.greenCount
