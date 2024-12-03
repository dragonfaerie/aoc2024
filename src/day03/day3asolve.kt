package day03

import java.io.File

fun day3parta() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day03/day3input"

    var acc = 0

    File(fileName).forEachLine { line ->
        val regex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")

        val matchResults = regex.findAll(line)
        matchResults.forEach { matchResult ->
            val matchSplit = matchResult.value.split("(", ",", ")")

            acc = acc + (matchSplit[1].toInt() * matchSplit[2].toInt())
        }
    }
    println(acc)
}
