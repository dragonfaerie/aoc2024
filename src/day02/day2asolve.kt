package day02

import java.io.File

fun day2parta() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
        val fileName = "/Users/kit/Documents/code/aoc2024/src/day02/day2input"

    var acc = 0

    File(fileName).forEachLine { line ->
        val reports = line.split(" ").map { it.toInt() }
        if (isSafeReport(reports)) {
            acc++
        }
    }

    println("Number of safe reports: $acc")
}

fun isSafeReport(reports: List<Int>): Boolean {
    if (reports.size < 2) return false

    // Determine trend: +1 for increasing, -1 for decreasing, 0 if invalid
    val trend = when {
        reports[1] > reports[0] -> 1
        reports[1] < reports[0] -> -1
        else -> return false // Equal values are invalid
    }

    for (i in 0..<reports.size - 1) {
        val difference = reports[i + 1] - reports[i]
        when {
            difference == 0 -> return false // Adjacent values must differ
            difference !in -3..3 -> return false // Difference must be between 1 and 3
            difference.sign != trend -> return false // Must maintain consistent trend
        }
    }

    return true
}

private val Int.sign: Int
    get() = when {
        this > 0 -> 1
        this < 0 -> -1
        else -> 0
    }