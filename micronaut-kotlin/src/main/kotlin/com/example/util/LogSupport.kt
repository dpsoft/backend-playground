package com.example.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface LogSupport {
    val log: Logger get() = LoggerFactory.getLogger(javaClass::class.java)
}