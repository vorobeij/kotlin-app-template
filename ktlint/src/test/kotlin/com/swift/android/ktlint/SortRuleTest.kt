package com.swift.android.ktlint

import com.pinterest.ktlint.test.format
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class SortRuleTest {

    @Test
    fun sortTest() {

        val input = ResourcesUtils.getFile("SortRuleTestInput.txt").readText()
        SortRule().format(input) shouldBeEqualTo ResourcesUtils.getFile("SortRuleTestOutput.txt").readText()
    }
}