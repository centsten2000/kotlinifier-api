package org.kotlinifier

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KotlinifierTest {

    private val kotlinifier = Kotlinifier()

    @Test
    fun `should return random word with kt for empty input`() {
        val words = kotlinifier.getRandomWords()
        assertEquals(10, words.size)
    }

    @Test
    fun `should replace all of ct with kt`() {
        val words = kotlinifier.kotlinify("ctactbct")
        assertEquals(listOf("ktactbct", "ctaktbct", "ctactbkt"), words)
    }

    @Test
    fun `should add t to all k`() {
        val words = kotlinifier.kotlinify("kakokkek")
        assertEquals(listOf("ktakokkek", "kaktokkek", "kakoktkek", "kakokktek", "kakokkekt"), words)
    }

    @Test
    fun `should replace every c with k`() {
        val words = kotlinifier.kotlinify("cacoccec")
        assertEquals(listOf("kacoccec", "cakoccec", "cacokcec", "cacockec", "cacoccek"), words)
    }
}
