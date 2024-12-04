package day04

import java.io.File

fun day4partb() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day04/day4input"
    val file = File(fileName)
    var acc = 0
    val content: List<String> = file.readLines()

    val matchingCenters = searchXPattern(content)
    matchingCenters.forEach { acc += 1 }
    println("Matching centers for the X pattern: $matchingCenters")
    println(acc)
}

fun searchXPattern(grid: List<String>): List<Pair<Int, Int>> {
    val numRows = grid.size
    val numCols = grid[0].length

    fun isValid(x: Int, y: Int) = x in 0 until numRows && y in 0 until numCols

    fun matchesXPattern(centerX: Int, centerY: Int): Boolean {
        // Coordinates for the "X" pattern around (centerX, centerY)
        val positions = listOf(
            Pair(centerX - 1, centerY - 1), // Top-left diagonal
            Pair(centerX - 1, centerY + 1), // Top-right diagonal
            Pair(centerX, centerY),         // Center
            Pair(centerX + 1, centerY - 1), // Bottom-left diagonal
            Pair(centerX + 1, centerY + 1)  // Bottom-right diagonal
        )

        // Check bounds for all positions
        if (positions.any { !isValid(it.first, it.second) }) return false

        // Extract characters
        val chars = positions.map { (x, y) -> grid[x][y] }

        // Center must always be 'A'
        if (chars[2] != 'A') return false

        // Check pattern: top diagonals and bottom diagonals can be M or S, center must be A
        return chars[2] == 'A' && // Center must be 'A'
                ((chars[0] == 'M' && chars[4] == 'S') && (chars[1] == 'M' && chars[3] == 'S')) ||
                ((chars[0] == 'M' && chars[4] == 'S') && (chars[1] == 'S' && chars[3] == 'M')) ||
                ((chars[0] == 'S' && chars[4] == 'M') && (chars[1] == 'M' && chars[3] == 'S')) ||
                ((chars[0] == 'S' && chars[4] == 'M') && (chars[1] == 'S' && chars[3] == 'M'))
    }

    val matchingCenters = mutableListOf<Pair<Int, Int>>()
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (matchesXPattern(i, j)) {
                matchingCenters.add(Pair(i, j))
            }
        }
    }

    //2110 is too high
    return matchingCenters
}
