package day02

import java.io.File

fun day2partb() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day02/day2input"
    var acc = 0

    File(fileName).forEachLine { line ->
        val reports = line.split(" ").map { it.toInt() }
        if (isSafeReport(reports) || canBeMadeSafe(reports)) {
            acc++
        }
    }

    println("Number of safe reports (Part 2): $acc")
}

fun canBeMadeSafe(reports: List<Int>): Boolean {
    if (reports.size < 3) return false // Removing a single level must leave at least 2 levels

    for (i in reports.indices) {
        val modifiedReports = reports.toMutableList().apply { removeAt(i) }
        if (isSafeReport(modifiedReports)) {
            return true
        }
    }

    return false
}