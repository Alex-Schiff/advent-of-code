package aockt.y2023

typealias Grid = List<List<Char>>

typealias Point = Pair<Int, Int>

fun Grid.get(point: Point): Char = this[point.first][point.second]

fun List<String>.toGrid(): Grid = this.map { it.toCharArray().toList() }

fun String.toGrid(): Grid = this.lines().toGrid()

fun Grid.getAdjacents(point: Point): List<Char> {
    val list = mutableListOf<Char>()
    if (point.first > 0) {
        if (point.second > 0) {
            list.add(this.get(Point(point.first - 1, point.second - 1)))
        }
        list.add(this.get(Point(point.first - 1, point.second)))
        if (point.second < (this.first().size - 1)) {
            list.add(this.get(Point(point.first - 1, point.second + 1)))
        }
    }

    if (point.second > 0) {
        list.add(this.get(Point(point.first, point.second - 1)))
    }

    if (point.second < (this.first().size - 1)) {
        list.add(this.get(Point(point.first, point.second + 1)))
    }

    if (point.first < (this.size - 1)) {
        if (point.second > 0) {
            list.add(this.get(Point(point.first + 1, point.second - 1)))
        }
        list.add(this.get(Point(point.first + 1, point.second)))
        if (point.second < (this.first().size - 1)) {
            list.add(this.get(Point(point.first + 1, point.second + 1)))
        }
    }

    return list.toList()
}

fun Grid.isValidDigit(point: Point): Boolean =
    getAdjacents(point).any { !it.isDigit() && it != '.' }

fun String.extractValidNumbersSum(grid: Grid, lineIndex: Int): Int {
    var sum = 0

    var currentNumber = ""
    var index = 0
    var isValid = false
    while (index < this.length) {
        if (!this[index].isDigit()) {
            if (currentNumber.isNotEmpty() && isValid) sum += currentNumber.toInt()
            currentNumber = ""
            isValid = false
        } else {
            currentNumber += this[index]
            isValid = isValid || grid.isValidDigit(lineIndex to index)
        }
        index++
    }

    if (currentNumber.isNotEmpty() && isValid) sum += currentNumber.toInt()
    return sum
}

fun Grid.extractAllStarPositions(): List<Point> = flatMapIndexed { rowIndex, row ->
    row.mapIndexedNotNull { colIndex, value ->
        if (value == '*') {
            rowIndex to colIndex
        } else {
            null
        }
    }
}

fun Grid.extractAllAdjacentNumbers(point: Point): List<Int> {
    val sameLineLeft = extractAdjacentSameLineLeft(point)
    val sameLineRight = extractAdjacentSameLineRight(point)
    val prevLine = extractAdjacentPrevLine(point)
    val nextLine = extractAdjacentNextLine(point)
    return listOfNotNull(sameLineLeft?.toInt(), sameLineRight?.toInt()) + prevLine + nextLine
}

fun Grid.extractAdjacentSameLineLeft(point: Point): String? {
    val line = this[point.first]
    var index = point.second - 1
    var ret = ""
    while (index >= 0) {
        if (line[index].isDigit()) {
            ret += line[index]
        } else {
            break
        }
        index--
    }
    return if (ret.isNotEmpty()) {
        ret.reversed()
    } else {
        null
    }
}

fun Grid.extractAdjacentSameLineRight(point: Point): String? {
    val line = this[point.first]
    var index = point.second + 1
    var ret = ""
    while (index > 0 && index < line.size) {
        if (line[index].isDigit()) {
            ret += line[index]
        } else {
            break
        }
        index++
    }
    return ret.ifEmpty { null }
}

fun Grid.extractAdjacentPrevLine(point: Point): List<Int> {
    if (point.first <= 0) {
        return listOf()
    }

    val upper = point.first - 1 to point.second
    return extractAdjacentToPoint(upper)
}

fun Grid.extractAdjacentNextLine(point: Point): List<Int> {
    if (point.first >= this.size - 1) {
        return listOf()
    }

    val lower = point.first + 1 to point.second
    return extractAdjacentToPoint(lower)
}

fun Grid.extractAdjacentToPoint(point: Point): List<Int> {
    return if (!get(point).isDigit()) {
        listOfNotNull(
            extractAdjacentSameLineLeft(point)?.toInt(),
            extractAdjacentSameLineRight(point)?.toInt(),
        )
    } else {
        val left = (extractAdjacentSameLineLeft(point) ?: "").toString()
        val right = (extractAdjacentSameLineRight(point) ?: "").toString()
        val ret = "$left${get(point)}$right".toInt()
        listOf(ret)
    }
}
