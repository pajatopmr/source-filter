package sample

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SampleTests {
    @Test
    fun `when path is the empty string verify the return is an empty list`() {
        assertEquals(listOf<String>(), filterSources("", ".swift", listOf<String>()), "")
    }

    @Test
    fun `when path is invalid verify that the return value is an empty list`() {
        assertEquals(listOf(), filterSources("@"))
    }

    @Test
    fun `when path is a file ending in dot kt verify that the correct list is returned`() {
        assertEquals(listOf("src/commonTest/resources/foo.kt"), filterSources("src/commonTest/resources/foo.kt"))
    }

    @Test
    fun `when path is a file not ending in dot kt verify that the correct (empty) list is returned`() {
        assertEquals(listOf(), filterSources("src/commonTest/resources/foo.swift"))
    }

    @Test
    fun `when path is a directory not ending in dot kt verify that the return list has the correct size`() {
        assertEquals(2, filterSources("src/commonTest/resources/").size)
    }

    @Test
    fun `when path is a directory and contains an excluded name verify that the return list has the correct size`() {
        val dir = "src/commonTest/resources"
        val list = listOf("level1")
        assertEquals(1, filterSources(path = dir, excludes = list).size)
    }

    @Test
    fun `when path is a directory but is excluded verify that the return list has the correct size`() {
        val dir = "src/commonTest/resources"
        val list = listOf("resources")
        assertEquals(0, filterSources(path = dir, excludes = list).size)
    }

    @Test
    fun `when path has a recursive file ending in dot kt but excluded verify that the return list has the correct size`() {
        val dir = "src/commonTest/resources"
        val list = listOf("level1.kt")
        assertEquals(1, filterSources(path = dir, excludes = list).size)
    }

    @Test
    fun `print the results`() {
        for (name in filterSources("src/commonTest/resources/"))
            println("Found file: ($name)")
        assertTrue(true)
    }
}