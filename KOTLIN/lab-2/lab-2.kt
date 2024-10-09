

import java.io.File
import java.util.TreeSet
import java.time.LocalDate
import kotlin.random.Random

//PROBLEM 1

interface IDictionary {
    fun add(word: String): Boolean
    fun find(word: String): Boolean
    fun size(): Int

    companion object {
        const val DICTIONARY_NAME = "/home/zsigmond/SAPIENTIA/IV-1/Android-2024/KOTLIN/lab-2/resources/dict.txt"
    }
}

object ListDictionary : IDictionary {
    private val words = mutableListOf<String>()

    init {
        File(IDictionary.DICTIONARY_NAME).forEachLine {
            add(it)
        }
    }

    override fun add(word: String): Boolean 
    { 
        if(words.contains(word)){
            return false
        }
        words.add(word)
        return true
    }

    override fun find(word: String): Boolean { 
        return words.contains(word)
    }

    override fun size(): Int { 
        return words.size
    }
}

enum class DictionaryType {
    ARRAY_LIST,
    TREE_SET,
    HASH_SET
}

object TreeSetDictionary : IDictionary {
    private var words = TreeSet<String>()

    init {
        File(IDictionary.DICTIONARY_NAME).forEachLine {
            add(it)
        }
    }

    override fun add(word: String): Boolean 
    { 
        if(words.contains(word)){
            return false
        }
        words.add(word)
        return true
    }

    override fun find(word: String): Boolean { 
        return words.contains(word)
    }

    override fun size(): Int { 
        return words.size
    }
}

object HashSetDictionary : IDictionary {
    private val words = HashSet<String>()

    init {
        File(IDictionary.DICTIONARY_NAME).forEachLine {
            add(it)
        }
    }

    override fun add(word: String): Boolean 
    { 
        if(words.contains(word)){
            return false
        }
        words.add(word)
        return true
    }

    override fun find(word: String): Boolean { 
        return words.contains(word)
    }

    override fun size(): Int { 
        return words.size
    }
}

class DictionaryProvider {
    companion object {
        fun createDictionary(type: DictionaryType): IDictionary {
            return when (type) {
                DictionaryType.ARRAY_LIST -> ListDictionary
                DictionaryType.TREE_SET -> TreeSetDictionary
                DictionaryType.HASH_SET -> HashSetDictionary
            }
        }
    }
}

// PROBLEM 2
fun String.nameMonogram(): String {
    return this.split(" ").map { it[0] }.joinToString(".")
}

fun List<String>.joinElements(separator: String): String {
    return this.joinToString()
}

fun List<String>.longestString(): String {
    return this.maxBy { it.length } ?: ""
}

// PROBLEM 3
data class Date(var year: Int = LocalDate.now().year, var month: Int = LocalDate.now().monthValue, var day: Int = LocalDate.now().dayOfMonth) : Comparable<Date> {
    
    override fun compareTo(other: Date): Int {
        return when {
            this.year != other.year -> this.year - other.year
            this.month != other.month -> this.month - other.month
            else -> this.day - other.day
        }
    }

    override fun toString(): String {
        return "$year-$month-$day"
    }
}

fun Date.isLeapYear(): Boolean {
    return (this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)
}

fun Date.isValid(): Boolean {
    return try {
        LocalDate.of(this.year, this.month, this.day)
        true
    } catch (e: Exception) {
        false
    }
}

fun generateRandomDate(): Date {
    val year = Random.nextInt(1900, 2100)
    val month = Random.nextInt(1, 30)
    val day = Random.nextInt(1, 60)
    return Date(year, month, day)
}

fun main(){
    // PROBLEM 1
    // val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
    // println("Number of words: ${dict.size()}")
    // var word: String?
    // while(true){
    //     print("What to find? ")
    //     word = readLine()
    //     if( word.equals("quit")){
    //         break
    //     }
    //     println("Result: ${word?.let { dict.find(it) }}")
    // }

    // PROBLEM 2
    // val name = "John Doe"
    // println(name.nameMonogram())

    // val list = listOf("a", "b", "c")
    // println(list.joinElements(", "))

    // val list2 = listOf("aaa", "bb", "c")
    // println(list2.longestString())

    // PROBLEM 3
    val validDates = mutableListOf<Date>()
    
    while (validDates.size < 10) {
        val randomDate = generateRandomDate()
        if (randomDate.isValid()) {
            validDates.add(randomDate)
        } else {
            println("Invalid date: $randomDate")
        }
    }
    
    println("\nValid dates:")
    validDates.forEach { println(it) }
    
    validDates.sort()
    println("\nSorted dates:")
    validDates.forEach { println(it) }
    
    validDates.reverse()
    println("\nReversed sorted dates:")
    validDates.forEach { println(it) }
    
    validDates.sortWith(compareBy({ it.month }, { it.day }))
    println("\nCustom sorted dates:")
    validDates.forEach { println(it) }
}