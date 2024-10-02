

import java.io.File
import java.util.TreeSet

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

fun main(){
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

    val name = "John Doe"
    println(name.nameMonogram())

    val list = listOf("a", "b", "c")
    println(list.joinElements(", "))
}