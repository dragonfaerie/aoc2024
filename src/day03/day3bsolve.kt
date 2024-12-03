package day03

import java.io.File

fun day3partb() {
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day03/day3input"
    var acc = 0
    var isEnabled = true

    File(fileName).forEachLine { line ->
        val tokens = Regex("(do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))").findAll(line)

        tokens.forEach { token ->
            when {
                token.value.startsWith("do()") -> isEnabled = true
                token.value.startsWith("don't()") -> isEnabled = false
                token.value.startsWith("mul(") && isEnabled -> {
                    val (a, b) = token.groupValues[2].toInt() to token.groupValues[3].toInt()
                    acc += a * b
                }
            }
        }
    }

    println(acc)
}

//149510138 is too high