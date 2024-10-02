

// class AnagramsGrouperTest {
//     @Test
//     fun threeGroupsAllLowerCase() {
//         val anagrams = groupAnagrams(listOf("eat", "tea", "tan", "ate", "nat",
//         "bat").toTypedArray())
//         assertEquals(3, anagrams.size)
//         assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
//         assertTrue(anagrams.contains(listOf("tan", "nat")))
//         assertTrue(anagrams.contains(listOf("bat")))
//     }

//     @Test
//     fun threeGroupsSomeUpperCase() {
//         val anagrams = groupAnagrams(listOf("eat", "tEa", "Tan", "atE", "NAT",
//         "bat").toTypedArray())
//         assertEquals(3, anagrams.size)
//         assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
//         assertTrue(anagrams.contains(listOf("tan", "nat")))
//         assertTrue(anagrams.contains(listOf("bat")))
//     }

//     @Test
//     fun validOneGroup() {
//         val anagrams = groupAnagrams(listOf("eat").toTypedArray())
//         assertEquals(1, anagrams.size)
//         assertTrue(anagrams.contains(listOf("eat")))
//     }

//     @Test
//     fun noGroup() {
//         val anagrams = groupAnagrams(emptyList<String>().toTypedArray())
//         assertEquals(0, anagrams.size)
//     }
// }

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val anagramsMap = mutableMapOf<String, MutableList<String>>()

    for (word in strs) {
        val sortedWord = word.toLowerCase().toCharArray().sorted().joinToString("")

        if (anagramsMap.containsKey(sortedWord)) {
            anagramsMap[sortedWord]?.add(word)
        } else {
            anagramsMap[sortedWord] = mutableListOf(word)
        }
    }
    return anagramsMap.values.toList()
}

fun main() {
    val testCases = listOf(
        arrayOf("eat", "tea", "tan", "ate", "nat", "bat"),
        arrayOf("eat", "tEa", "Tan", "atE", "NAT", "bat"),
        arrayOf("eat"),
        arrayOf<String>()
    )

    for (testCase in testCases) {
        val anagrams = groupAnagrams(testCase)
        println("Input: ${testCase.joinToString(", ")}")
        println("Grouped Anagrams: $anagrams")
        println()
    }
}
