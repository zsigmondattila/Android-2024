import kotlin.random.Random

class TextGenerator {
    val wordPairs = mutableMapOf<Pair<String, String>, MutableList<String>>()
    var firstTwoWords: Pair<String, String>? = null

    fun learnWords(input: String) {
        val words = input.split(" ")
        
        if (words.size < 3) return
        
        firstTwoWords = Pair(words[0], words[1])
        
        for (i in 0 until words.size - 2) {
            val prefix = Pair(words[i], words[i + 1])
            val postfix = words[i + 2]
            wordPairs.getOrPut(prefix) { mutableListOf() }.add(postfix)
        }
    }

    fun generateText(): String {
        val result = StringBuilder()
        var currentPrefix = firstTwoWords
        
        if (currentPrefix != null) {
            result.append(currentPrefix.first).append(" ").append(currentPrefix.second)
        }

        while (currentPrefix != null) {
            val postfixes = wordPairs[currentPrefix]

            if (postfixes.isNullOrEmpty()) break

            val nextWord = postfixes[Random.nextInt(postfixes.size)]
            result.append(" ").append(nextWord)

            currentPrefix = Pair(currentPrefix.second, nextWord)
        }

        return result.toString()
    }
}

fun main() {
    testLearnWords()
    testGenerateText_correctFirstTwoWords()
    testGenerateText_generatesTextBasedOnLearnedPairs()
    
    val input = "Now is not the time for sleep, now is the time for party!"
    val generator = TextGenerator()
    generator.learnWords(input)
    val generatedText = generator.generateText()
    println(generatedText)
}


fun testLearnWords() {
    val input = "Now is the time for sleep, now is the time for party!"
    val generator = TextGenerator()
    generator.learnWords(input)

    val expectedPostfixesForTimeFor = listOf("sleep,", "party!")
    val actualPostfixesForTimeFor = generator.wordPairs[Pair("time", "for")]

    if (actualPostfixesForTimeFor != null && actualPostfixesForTimeFor.containsAll(expectedPostfixesForTimeFor)) {
        println("testLearnWords PASSED")
    } else {
        println("testLearnWords FAILED")
    }
}

fun testGenerateText_correctFirstTwoWords() {
    val input = "Now is not the time for sleep, now is the time for party!"
    val generator = TextGenerator()
    generator.learnWords(input)

    val generatedText = generator.generateText()
    if (generatedText.startsWith("Now is")) {
        println("testGenerateText_correctFirstTwoWords PASSED")
    } else {
        println("testGenerateText_correctFirstTwoWords FAILED")
    }
}

fun testGenerateText_generatesTextBasedOnLearnedPairs() {
    val input = "Now is not the time for sleep, now is the time for party!"
    val generator = TextGenerator()
    generator.learnWords(input)

    val generatedText = generator.generateText()
    val words = generatedText.split(" ")

    if (words.size >= 6 && words[0] == "Now" && words[1] == "is") {
        println("testGenerateText_generatesTextBasedOnLearnedPairs PASSED")
    } else {
        println("testGenerateText_generatesTextBasedOnLearnedPairs FAILED")
    }
}
