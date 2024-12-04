package day04

import java.io.File

fun day4parta() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day04/day4input"
    val file = File(fileName)
    var acc = 0
    val content: List<String> = file.readLines()

    val word1 = "XMAS"

    acc = searchWord(content, word1)

    println(acc)
}

fun searchWord(grid: List<String>, word: String): Int {
    var acc = 0
    val numRows = grid.size
    val numCols = grid[0].length
    val directions = listOf(
        Pair(0, 1),  // Right
        Pair(0, -1), // Left
        Pair(1, 0),  // Down
        Pair(-1, 0), // Up
        Pair(1, 1),  // Diagonal down-right
        Pair(-1, -1),// Diagonal up-left
        Pair(1, -1), // Diagonal down-left
        Pair(-1, 1)  // Diagonal up-right
    )

    fun isValid(x: Int, y: Int) = x in 0 until numRows && y in 0 until numCols

    fun searchFrom(x: Int, y: Int, word: String): Boolean {
        for (dir in directions) {
            var match = true
            for (k in word.indices) {
                val newX = x + dir.first * k
                val newY = y + dir.second * k
                if (!isValid(newX, newY) || grid[newX][newY] != word[k]) {
                    match = false
                    break
                }
            }
            if (match) acc += 1
        }
        return false
    }

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == word[0] && searchFrom(i, j, word)) {
                return acc
            }
        }
    }
    return acc
}
