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

    fun performQuiz(nr: Int) {
        val items = selectRandomItems(nr)
        var correctAnswers = 0

        for (item in items) {
            println(item.question)
            item.answers.forEach { (key, value) ->
                println("$key: $value")
            }

            print("Your answer: ")
            val userAnswer = readLine()

            if (userAnswer?.trim() == item.correctAnswer) {
                println("Correct!")
                correctAnswers++
            } else {
                println("Wrong! The correct answer is: ${item.correctAnswer}")
            }
            println("-------------------------------------------------")
        }

        println("*************************************************")
        println("Quiz Finished! You got $correctAnswers/${items.size} correct answers.")
    }
}

class ItemService(private val itemRepository: ItemRepository) {
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

class ItemRepository {
    private var items = mutableListOf<Item>()

    init {
        val lines = File("resources/quiz.txt").readLines()

        var question = ""
        var correctAnswer = ""
        val answers = mutableMapOf<String, String>()

        for (line in lines) {
            when {
                line.startsWith("Question:") -> {
                    if (question.isNotEmpty()) {
                        save(Item(question, answers.toMutableMap(), correctAnswer))
                        answers.clear()
                    }
                    question = line.substringAfter("Question: ").trim()
                }
                line.startsWith("Correct:") -> {
                    correctAnswer = line.substringAfter("Correct: ").trim()
                }
                line.matches(Regex("^[a-d]:.*")) -> {
                    val answerKey = line.substringBefore(":").trim()
                    val answerValue = line.substringAfter(":").trim()
                    answers[answerKey] = answerValue
                }
                line.isBlank() -> {}
                else -> {
                    throw IllegalArgumentException("Invalid line in the file: $line")
               }

            }
        }

        if (question.isNotEmpty()) {
            save(Item(question, answers.toMutableMap(), correctAnswer))
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

    fun getAllItems(): List<Item> {
        return items
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
    val controller = ItemController(repo)

    val quizItems = service.selectRandomItems(5)

    // for (item in quizItems) {
    //     println(item.question)
    //     item.answers.forEach { (key, value) ->
    //         println("$key: $value")
    //     }
    //     println("Correct Answer: ${item.correctAnswer}")
    // }

    controller.performQuiz(5)
}
