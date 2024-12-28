package day24

import java.io.File

fun day24parta() {
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day24/day24input"
    val content = File(fileName).readLines()

    val initialValues = loadInitialValues(content)
    val wireCombos = loadWireCombos(content)

    val masterValues = mutableMapOf<String, Int>()
    masterValues.putAll(initialValues.mapValues { it.value.toInt() })

    val unresolvedGates = mutableListOf<String>()
    unresolvedGates.addAll(wireCombos)

    while (unresolvedGates.isNotEmpty()) {
        val iterator = unresolvedGates.iterator()

        while (iterator.hasNext()) {
            val combo = iterator.next()
            val parts = combo.split(" ")
            val input1 = parts[0].trim()
            val operator = parts[1].trim()
            val input2 = parts[2].trim()
            val outputWire = parts[4].trim()

            val left = masterValues[input1]
            val right = masterValues[input2]

            if (left != null && right != null) {
                // Compute the output based on the operator
                val result = when (operator) {
                    "OR" -> if (left == 1 || right == 1) 1 else 0
                    "AND" -> if (left == 1 && right == 1) 1 else 0
                    "XOR" -> if ((left == 1 && right == 0) || (left == 0 && right == 1)) 1 else 0
                    else -> throw IllegalArgumentException("Invalid operator: $operator")
                }

                masterValues[outputWire] = result
                iterator.remove() // Remove resolved gate
            }
        }
    }

    // Collect z-wires and construct the binary result
    val regex = Regex("z[0-9][0-9]")
    val binaryResult = masterValues
        .filter { it.key.matches(regex) }
        .toSortedMap() // Sort z-wires by name
        .values
        .joinToString("") { it.toString() }
        .reversed()

    println("Binary Result: $binaryResult")
    println("Decimal Result: ${binaryResult.toInt(2)}")
}

fun loadInitialValues(content: List<String>): Map<String, String> {
    return content
        .filter { it.contains(":") }
        .associate {
            val parts = it.split(":")
            parts[0].trim() to parts[1].trim()
        }
}

fun loadWireCombos(content: List<String>): List<String> {
    return content.filter { it.contains("->") }
}