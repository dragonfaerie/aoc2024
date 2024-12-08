package day06

import java.io.File

fun day6partb() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day06/day6input"
    val file = File(fileName)
    val content: List<String> = file.readLines()

    val maxLines = content.size
    val maxWidth = content[0].length

    val startPosition = mutableMapOf<Int, Int>()
    content.forEachIndexed { index, line ->
        val temp = line.indexOf('^')
        if (temp >= 0) {
            startPosition[index] = temp
        }
    }

    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // up, right, down, left
    val originalGrid = content.map { it.toCharArray() } // Use a mutable grid for simulation
    val startRow = startPosition.keys.first()
    val startCol = startPosition.values.first()

    fun causesLoop(obstacleRow: Int, obstacleCol: Int): Boolean {
        val grid = originalGrid.map { it.clone() } // Copy the grid
        grid[obstacleRow][obstacleCol] = '#' // Place the obstacle

        var currentRow = startRow
        var currentCol = startCol
        var currentDirectionIndex = 0
        val visitedStates = mutableSetOf<Triple<Int, Int, Int>>() // Track position + direction

        while (true) {
            // Check if the current state has been visited
            val currentState = Triple(currentRow, currentCol, currentDirectionIndex)
            if (currentState in visitedStates) {
                return true // Loop detected
            }
            visitedStates.add(currentState)

            // Calculate the next position
            val nextRow = currentRow + directions[currentDirectionIndex].first
            val nextCol = currentCol + directions[currentDirectionIndex].second

            // Check bounds
            if (nextRow !in 0 until maxLines || nextCol !in 0 until maxWidth) {
                return false // Exited the board, no loop
            }

            // Check the next cell
            when (grid[nextRow][nextCol]) {
                '.' -> {
                    // Move forward
                    currentRow = nextRow
                    currentCol = nextCol
                }
                '^' -> {
                    // Move forward
                    currentRow = nextRow
                    currentCol = nextCol
                }
                '#' -> {
                    // Obstacle, turn right
                    currentDirectionIndex = (currentDirectionIndex + 1) % directions.size
                }
                else -> {
                    return false // Invalid state
                }
            }
        }
    }

    // Count valid obstacle positions
    var loopObstacleCount = 0

    for (row in 0 until maxLines) {
        for (col in 0 until maxWidth) {
            if (originalGrid[row][col] == '.') { // Only consider empty cells
                if (causesLoop(row, col)) {
                    loopObstacleCount++
                }
            }
        }
    }

    println("Number of obstacle positions causing a loop: $loopObstacleCount")
}