import java.io.File

class ItemController(private val itemRepository: ItemRepository) {
    fun selectRandomItems(nr: Int): List<Item> {
        if (nr > itemRepository.size()) {
            throw IllegalArgumentException("Not enough items in the repository")
        }

        val items = mutableListOf<Item>()

        while (items.size < nr) {
            val item = itemRepository.randomItem()
            if (!items.contains(item)) {
                items.add(item)
            }
        }

        return items
    }
}

class ItemService(private val itemRepository: ItemRepository) {
    fun selectRandomItems(nr: Int): List<Item> {
        return ItemController(itemRepository).selectRandomItems(nr)
    }
}

class ItemRepository {

    private var items = mutableListOf<Item>()

    init {
        val lines = File("resources/quiz.txt").readLines()

        for (line in lines) {
            val parts = line.split(";")
            if (parts.size < 4) continue

            val question = parts[0]
            val correctAnswer = parts[1]
            val answers = mutableMapOf<String, String>()

            for (i in 2 until parts.size) {
                val answerPart = parts[i].split(":")
                if (answerPart.size == 2) {
                    val answerKey = answerPart[0].trim()
                    val answerValue = answerPart[1].trim()
                    answers[answerKey] = answerValue
                }
            }

            save(Item(question = question, answers = answers, correctAnswer = correctAnswer))
        }
    }

    fun randomItem(): Item {
        return items.random()
    }

    fun save(item: Item) {
        items.add(item)
    }

    fun size(): Int {
        return items.size
    }
}

data class Item(
    val question: String,
    val answers: MutableMap<String, String>,
    val correctAnswer: String
)

fun main() {
    val repo = ItemRepository()
    val service = ItemService(repo)

    val quizItems = service.selectRandomItems(5)

    for (item in quizItems) {
        println(item.question)
        item.answers.forEach { (key, value) ->
            println("$key: $value")
        }
        println("Correct Answer: ${item.correctAnswer}")
    }
}
