package day02

import java.io.File

fun day2partb() {
    val fileName = "/Users/kit/Documents/code/aoc2024/src/day02/day2input"
//    val fileName = "/Users/kit/Documents/code/aoc2024/src/exampledata"
    var acc = 0
    var status = false
    var overallStatus = true
    var fatalStatus = false
    var mathUsed = ""

    File(fileName).forEachLine { line ->
        val reports = line.split(" ")
        var nextReport = 0
        var currentReport = 0

        println(reports)
        reports.forEachIndexed { index, report ->
            if (index + 1 < reports.size) {
                nextReport = reports[index + 1].toInt()

                currentReport = report.toInt()

                if (currentReport < nextReport ) {
                    val temp = nextReport - currentReport
                    if (temp <= 3) {
                        status = true
                    } else {
                        if (index + 2 < reports.size) {
                            fatalStatus = checkIfFatalFlaw(currentReport, reports[index + 2].toInt(), mathUsed)
                        } else {
                            if (!overallStatus) {
                                fatalStatus = true
                            }
                        }
                        status = false
                    }
                    if (index == 0) {
                        mathUsed = "A"
                    } else {
                        if (mathUsed == "B") {
                            if (index + 2 < reports.size) {
                                fatalStatus = checkIfFatalFlaw(currentReport, reports[index + 2].toInt(), mathUsed)
                            } else {
                                if (!overallStatus) {
                                    fatalStatus = true
                                }
                            }
                            status = false
                        }
                    }
                } else if ( nextReport < currentReport) {
                    val temp = currentReport - nextReport
                    if (temp <= 3) {
                        status = true
                    } else {
                        if (index + 2 < reports.size) {
                            fatalStatus = checkIfFatalFlaw(currentReport, reports[index + 2].toInt(), mathUsed)
                        } else {
                            if (!overallStatus) {
                                fatalStatus = true
                            }
                        }
                        status = false
                    }
                    if (index == 0) {
                        mathUsed = "B"
                    } else {
                        if (mathUsed == "A") {
                            if (index + 2 < reports.size) {
                                fatalStatus = checkIfFatalFlaw(currentReport, reports[index + 2].toInt(), mathUsed)
                            } else {
                                if (!overallStatus) {
                                    fatalStatus = true
                                }
                            }
                            status = false
                        }
                    }
                } else {
                    println("The reports are the same $currentReport, $nextReport")
                    if (index + 2 < reports.size) {
                        fatalStatus = checkIfFatalFlaw(currentReport, reports[index + 2].toInt(), mathUsed)
                    } else {
                        if (!overallStatus) {
                            fatalStatus = true
                        }
                    }
                    status = false
                }
                println("Current Report is: $currentReport, Next Report is: $nextReport, Status: $status")
            }
            if (!status) {
                overallStatus = false
            }

            if (fatalStatus) {
                overallStatus = false
            } else {
                overallStatus = true
            }
        }

        if (overallStatus) {
            acc += 1
        } else {
            overallStatus = true
            fatalStatus = false
        }
    }

    println(acc)
}

fun checkIfFatalFlaw(currentReport: Int, nextReport: Int, mathUsed: String): Boolean {
    var status: Boolean
    if (currentReport < nextReport ) {
        val temp = nextReport - currentReport
        if (temp <= 3) {
            status = false
        } else {
            status = true
        }
        if (mathUsed == "B") {
            status = true
        }
    } else if ( nextReport < currentReport) {
        val temp = currentReport - nextReport
        if (temp <= 3) {
            status = false
        } else {
            status = true
        }
            if (mathUsed == "A") {
                status = true
            }
    } else {
         status = true
    }

    println(status)

    return status
}