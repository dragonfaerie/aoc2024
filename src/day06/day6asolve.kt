package day06

import java.io.File
fun day6parta() {
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
    var currentDirectionIndex = 0
    var currentRow = startPosition.keys.first()
    var currentCol = startPosition.values.first()
    val visitedLocations = mutableSetOf<Pair<Int, Int>>() // Use a Set to track unique locations

    println("Start Position: $currentRow, $currentCol")

    while (true) {
        // Record the current position as visited
        visitedLocations.add(Pair(currentRow, currentCol))

        // Calculate the next position
        val nextRow = currentRow + directions[currentDirectionIndex].first
        val nextCol = currentCol + directions[currentDirectionIndex].second

        // Check bounds
        if (nextRow !in 0 until maxLines || nextCol !in 0 until maxWidth) {
            println("Exited board at $currentRow, $currentCol after visiting ${visitedLocations.size} unique locations")
            break
        }

        // Check the next cell
        if (content[nextRow][nextCol] == '.' || content[nextRow][nextCol] == '^') {
            // Valid move
            currentRow = nextRow
            currentCol = nextCol
            println("Moved to: $currentRow, $currentCol")
        } else {
            // Obstacle, turn right
            currentDirectionIndex = (currentDirectionIndex + 1) % directions.size
            println("Turned right at obstacle at $nextRow, $nextCol. Now facing: ${directions[currentDirectionIndex]}")
        }
    }

    println("Total unique locations visited: ${visitedLocations.size}")
}