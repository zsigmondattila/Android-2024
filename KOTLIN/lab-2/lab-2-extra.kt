import kotlin.random.Random

class TextGenerator {
    private val prefixMap = mutableMapOf<Pair<String, String>, MutableList<String>>()

    fun learnWords(text: String) {

    }

    fun generateText(): String {
        return ""
    }
}

fun main() {
    val text = "Now is not the time for sleep, now is the time for party!"
    val textGenerator = TextGenerator()

    textGenerator.learnWords(text)

    val generatedText = textGenerator.generateText()
    println(generatedText)
}

