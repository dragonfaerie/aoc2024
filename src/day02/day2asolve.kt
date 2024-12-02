package day02

import java.io.File


fun day2parta() {
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day02/day2input"
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    var acc = 0
    var status = false
    var overallStatus = true
    var mathUsed = ""

    File(fileName).forEachLine { line ->
        val reports = line.split(" ")
        var nextReport = 0
        var currentReport = 0

        reports.forEachIndexed { index, report ->
            if (index + 1 < reports.size) {
                nextReport = reports[index + 1].toInt()

                currentReport = report.toInt()

                if (currentReport < nextReport ) {
                    val temp = nextReport - currentReport
                    if (temp <= 3) {
                        status = true
                    } else {
                        status = false
                    }
                    if (index == 0) {
                        mathUsed = "A"
                    } else {
                        if (mathUsed == "B") {
                            status = false
                        }
                    }
                } else if ( nextReport < currentReport) {
                    val temp = currentReport - nextReport
                    if (temp <= 3) {
                        status = true
                    } else {
                        status = false
                    }
                    if (index == 0) {
                        mathUsed = "B"
                    } else {
                        if (mathUsed == "A") {
                            status = false
                        }
                    }
                } else {
                    status = false
                }
            }
            if (!status) {
                overallStatus = false
            }
        }

        if (overallStatus) {
            acc += 1
        } else {
            overallStatus = true
        }
    }

    println(acc)
}