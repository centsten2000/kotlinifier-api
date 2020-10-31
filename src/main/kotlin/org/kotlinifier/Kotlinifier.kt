package org.kotlinifier

import java.nio.file.Files
import java.nio.file.Paths

private val WORDS = Files.readAllLines(
    Paths.get(Kotlinifier::class.java.classLoader.getResource("words.txt").toURI()))
    .asSequence()
    .filter { it.contains(Regex("[kc]t")) }
    .toSet()

private val MAX_OUTPUT_SIZE = 10

class Kotlinifier {

    fun kotlinify(input: String): List<String> {
        val result = LinkedHashSet<String>()
        // replace every ct with kt
        if (input.contains("ct")) {
            result.addAll(input.replaceAllOccurrences("ct", "kt"))
        }
        // add t to every k
        if (input.contains("k")) {
            result.addAll(input.replaceAllOccurrences("k", "kt"))
        }
        // replace every c with k
        if (input.contains('c')) {
            result.addAll(input.replaceAllOccurrences("c", "k"))
        }
        return result.take(MAX_OUTPUT_SIZE);
    }

    fun getRandomWords(): List<String> {
        val result = ArrayList<String>()
        for (i in 1..MAX_OUTPUT_SIZE) {
            val word = WORDS.random()
            if (word.contains("kt")) {
                result.add(word)
            } else {
                result.add(word.replace("ct", "kt"))
            }
        }
        return result
    }

    private fun String.replaceAllOccurrences(target: String, replacement: String): List<String> {
        if (!this.contains(target)) {
            return emptyList()
        }
        val result = ArrayList<String>()
        var lastIndex = 0
        while (lastIndex != -1) {
            lastIndex = this.indexOf(target, lastIndex)
            if (lastIndex != -1) {
                val replaced = this.substring(0, lastIndex) +
                        replacement +
                        (if (lastIndex < this.length) this.substring(lastIndex + target.length) else "")
                result.add(replaced)
                lastIndex += target.length
            }
        }
        return result
    }
}
