package com.practice.gitapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile


@SpringBootApplication
class PracticeApplication

fun main(args: Array<String>) {
    runApplication<PracticeApplication>(*args)
}
