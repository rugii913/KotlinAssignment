package com.teamsparta.courseregistration.infra.aop

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

fun <T> loggingStopWatch(function: () -> T): T {

    val logger = LoggerFactory.getLogger("Execution Time Logger")

    val stopWatch = StopWatch()

    stopWatch.start()

    return function.invoke()
        .also { stopWatch.stop() }
        .also { logger.info("Execution Time: ${stopWatch.totalTimeMillis}ms") }
}
