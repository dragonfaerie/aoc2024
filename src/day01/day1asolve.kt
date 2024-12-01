package day01

import java.io.File

fun day1parta() {
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
        val leftNumber = n
        val rightNumber = rightSet[i]

        if (leftNumber < rightNumber) {
            val temp = rightNumber - leftNumber
            acc += temp
        } else {
            val temp = leftNumber - rightNumber
            acc += temp
        }
    }

    println(acc)
}