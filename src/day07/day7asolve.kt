package day07

import java.io.File

fun day7parta() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day07/day7input"
    val file = File(fileName)
    val content: List<String> = file.readLines()
    var acc:Long = 0
    var isValid: Boolean = false

    content.forEach { line ->
        val temp = line.split(":")
        val answer = temp.first().trim().toLong()
        val input = temp.last().trim().split(" ")

        isValid = evaluateNumbers(answer, input)

        println("Value $answer is $isValid")

        if (isValid) {
            acc += answer
        }

    }

    println(acc)
}

private fun evaluateNumbers(answer: Long, input: List<String>): Boolean {
    if (input.size !in 2..20) return false // Ensure valid input size

    // Helper function to recursively evaluate all combinations
    fun evaluateAllCombinations(numbers: List<Long>, operators: List<Char>, currentIndex: Int, currentResult: Long): Boolean {
        // Base case: if we've reached the end of operators
        if (currentIndex == operators.size) {
            return evaluateAnswer(answer, currentResult)
        }

        val nextNumber = numbers[currentIndex + 1]
        val nextOperator = operators[currentIndex]

        // Apply the current operator
        val newResult = when (nextOperator) {
            '+' -> currentResult + nextNumber
            '*' -> currentResult * nextNumber
            else -> throw IllegalArgumentException("Invalid operator: $nextOperator")
        }

        // Recursively evaluate the next operator
        return evaluateAllCombinations(numbers, operators, currentIndex + 1, newResult)
    }

    // Generate all combinations of operators
    val operatorCombos = generateOperatorCombinations(input.size - 1)

    // Convert input strings to integers
    val numbers = input.map { it.toLong() }

    // Try each operator combination
    for (operators in operatorCombos) {
        if (evaluateAllCombinations(numbers, operators, 0, numbers[0])) {
            return true
        }
    }

    return false
}

private fun evaluateAnswer(possibleAnswer: Long, answer: Long): Boolean {
    return possibleAnswer == answer
}

private fun generateOperatorCombinations(length: Int): List<List<Char>> {
    if (length == 0) return emptyList()

    val operators = listOf('+', '*')
    return operators.flatMap { op ->
        if (length == 1) listOf(listOf(op))
        else generateOperatorCombinations(length - 1).map { listOf(op) + it }
    }
}