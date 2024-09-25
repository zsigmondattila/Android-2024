import java.util.*

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

fun main() {
    //adder()
    //days()
    //primeRange(10)
    
	//val encodedMessage = messageCoding("Ez egy szoveg", ::encode)
    //val decodedMessage = messageCoding(encodedMessage, ::decode)
    //println("$encodedMessage visszafejtve: $decodedMessage")
    
    //evenNumbersInAList(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
}