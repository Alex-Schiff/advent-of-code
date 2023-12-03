package aockt.y2023

typealias Grid = List<List<Char>>
typealias Point = Pair<Int, Int>
fun Grid.get(coordinates: Point?): Char? {
    return if (coordinates == null) {
        null
    } else {
        this[coordinates.first][coordinates.second]
    }
}

fun List<String>.toGrid(): Grid = this.map { line -> line.toCharArray().toList() }

fun Grid.getAdjacents(coords: Point): List<Char> {
    val ret = mutableListOf<Char>()
    if(coords.first > 0) {
        if(coords.second > 0) {
            ret.add(this[coords.first-1][coords.second-1])
        }
        ret.add(this[coords.first-1][coords.second])
        if(coords.second < (this.first().size -1)) {
            ret.add(this[coords.first-1][coords.second+1])
        }
    }

    if(coords.second > 0) {
        ret.add(this[coords.first][coords.second-1])
    }

    if(coords.second < (this.first().size -1)) {
        ret.add(this[coords.first][coords.second+1])
    }

    if(coords.first < (this.size -1)) {
        if(coords.second > 0) {
            ret.add(this[coords.first+1][coords.second-1])
        }
        ret.add(this[coords.first+1][coords.second])
        if(coords.second < (this.first().size -1)) {
            ret.add(this[coords.first+1][coords.second+1])
        }
    }

    return ret.toList()
}

fun Grid.isValidDigit(coords: Point): Boolean = getAdjacents(coords).any { !it.isDigit() && it != '.'}