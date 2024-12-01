package day01

import java.io.File

fun day1partb() {
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day01/day1input"
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val leftSet: MutableList<Int> = mutableListOf()
    val rightSet: MutableList<Int> = mutableListOf()
    var acc: Int = 0

    File(fileName).forEachLine { line ->
        val splitline = line.split(',')

        leftSet.add(splitline[0].toInt())
        rightSet.add(splitline[1].toInt())
    }

    leftSet.sort()
    rightSet.sort()

    leftSet.forEachIndexed { i, n ->
        val count = rightSet.count { it == n }
        val similarity = n * count
        acc += similarity
    }

    println(acc)
}
