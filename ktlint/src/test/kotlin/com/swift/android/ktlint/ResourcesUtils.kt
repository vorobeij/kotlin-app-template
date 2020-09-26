package com.swift.android.ktlint

import java.io.File

object ResourcesUtils {

    fun getResourceAsString(resourceId: String): String {
        return this::class.java.classLoader
            ?.getResourceAsStream(resourceId)
            ?.bufferedReader(Charsets.UTF_8)?.use {
                it.readText()
            }.orEmpty()
    }

    fun getResourceAsLines(resourceId: String): List<String> {
        return this::class.java.classLoader
            ?.getResourceAsStream(resourceId)
            ?.bufferedReader(Charsets.UTF_8)?.use {
                it.lineSequence().toList()
            }.orEmpty()
    }

    fun getFile(resourceId: String): File = File(this::class.java.classLoader!!.getResource(resourceId)!!.file)
}