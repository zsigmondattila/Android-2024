import java.util.*
import kotlin.collections.sorted
import kotlin.random.Random

// 1. feladat
fun adder() {
    val a = 2
    val b = 3
        
    val sum = a + b
    
    println("SUM = $sum")
    println("SUM = ${2 + 3}")
}

// 2. feladat
fun days() {
    val daysOfWeek = listOf("Hetfo", "Kedd", "Szerda", "Csutortok", "Pentek", "Szombat", "Vasarnap")
	
    for(day: String in daysOfWeek) {
        println(day)
    }
    
    println("------------")
    
    daysOfWeek.filter  { it.startsWith("S") }.forEach {
        println(it)
    }
    
    println("------------")
    
    daysOfWeek.filter { it.contains("e") }.forEach {
        println(it)
    }
    
    println("------------")

        
    daysOfWeek.filter { it.length == 6 }.forEach {
        println(it)
    }

}

//3. feladat
fun isPrime(num: Int): Boolean {
    if (num < 2) return false
    for (i in 2..num / 2) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}

fun primeRange(lim: Int) {
    for (i in 1..lim) {
        if (isPrime(i)) {
            println("$i: primszam")
        } else {
            println("$i: nem primszam")
        }
    }
}

//4. feladat
fun encode(str: String): String {
    return Base64.getEncoder().encodeToString(str.toByteArray())
}

fun decode(str: String): String {
    return String(Base64.getDecoder().decode(str))
}

fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}

//5. feladat
fun evenNumbersInAList( numbers: IntArray ) = numbers.filter { it % 2 == 0 }.forEach { println(it) }

//6. feladat
fun mapExample() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val daysOfWeek = listOf("Hetfo", "Kedd", "Szerda", "Csutortok", "Pentek", "Szombat", "Vasarnap")
    println("Lista: $list")
    val doubled = list.map {it * 2}
    println("Duplazva: $doubled")
    val capitalizedDaysOfWeek = daysOfWeek.map { it.toUpperCase() }
    println("Nagybetus napok: ${capitalizedDaysOfWeek}")
    val firstChars = daysOfWeek.map { it[0].toLowerCase() }
    println("Kezdobetuk: $firstChars")
    val nrChars = daysOfWeek.map { it.length }
    println("Naphosszak: $nrChars")
    val avgChars = daysOfWeek.map { it.length }.average()
    println("Atlag: ${avgChars}")
}

//7. feladat
fun mutableExample() {
    val daysOfWeek = listOf("Hetfo", "Kedd", "Szerda", "Csutortok", "Pentek", "Szombat", "Vasarnap")
    var mutableDaysOfWeek = daysOfWeek.toMutableList()

    mutableDaysOfWeek.removeIf{ it.contains("n") }
    println(mutableDaysOfWeek)

    var indexDaysOfWeek = mutableDaysOfWeek.withIndex()
    for ((index, day) in indexDaysOfWeek ) {
        println("Item at $index is $day")
    }

    mutableDaysOfWeek.sort()
    println(mutableDaysOfWeek)
}

//8. feladat
fun arrayExample() {
    val randomArray = Array(10) { Random.nextInt (0, 100) }

    println("Random tomb: ")
    randomArray.forEach { println(it) }

    println("Random tomb novekvoben: ")
    randomArray.sort()
    randomArray.forEach { println(it) }

    if(randomArray.any { it % 2 == 0 }) {
        println("A tombben van paros elem")
    }

    if(randomArray.all { it % 2 == 0}) {
        println("A tombben az osszes elem paros")
    }

    var sum = 0
    var nr = 0
    randomArray.forEach { 
        sum += it
        nr += 1
    }
    println("Atlag: ${sum/nr}")

}

fun main() {
    val choice = 8

    when (choice) {
        1 -> adder()
        2 -> days()
        3 -> primeRange(10)
        4 -> {
            val encodedMessage = messageCoding("Ez egy szoveg", ::encode)
            val decodedMessage = messageCoding(encodedMessage, ::decode)
            println("$encodedMessage visszafejtve: $decodedMessage")
        }
        5 -> evenNumbersInAList(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        6 -> mapExample()
        7 -> mutableExample()
        8 -> arrayExample()
        else -> println("Hiba")
    }
}
