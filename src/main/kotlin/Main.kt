fun main() {
//    println(
//        caesarEncrypt("В ОДНОМ ЧАСЕ ЛЮБВИ - ЦЕЛАЯ ЖИЗНЬ.", 5)
//    )

//    repeat(32) {
//        println(
//            caesarDecrypt("ШСЪИЫЯ ДЮХЯ ФЪГФХОЛЕЮП - ДЦЗЯ ЛП!. key = $it", it)
//        )
//    }

//    println(
//        tarabar("ШЛЕ ГУЦЕЛА ПАГИПАЮКЛЯ ЛО ЛРЕПЫ ШОЛНМИЯКИЯ.")
//    )

//    println(
//        vigenereEncrypt("ГОРАЗДО ЛЕГЧЕ НАЙТИ ОШИБКУ, ЧЕМ ИСТИНУ.", "РАЗУЖН")
//    )

//    println(
//        vigenereDecrypt("ЛЩТХ ГЪХПК ВУББХЧ ЦЩГЧУ - НЪЯМБШВУ.", "ЖИЗНЬЭТОВОПРОСВРЕМЕНИ")
//    )

//    println(
//        vigenereKey("ГОРАЗДО ЛЕГЧЕ НАЙТИ ОШИБКУ, ЧЕМ ИСТИНУ.", "УОЧУНСЮ ТШЙДХ ФУПЯШ ХЛООЪУ, ЭТЬ ПДШХЭУ.")
//    )

//    println(
//        transposition("ЕСЛИ ЖИЗНЬ ТЕРЯЕТ СМЫСЛ - РИСКУЙТЕ.", 7)
//    )
}

private fun caesarEncrypt(message: String, key: Int): String {
    return message.map { ch ->
        chars.indexOf(ch).let { index ->
            if (index == -1) return@map ch
            val newIndex = (index + key) % chars.size
            return@map chars[newIndex]
        }
    }.joinToString("")
}

private fun caesarDecrypt(message: String, key: Int): String {
    return message.map { ch ->
        chars.indexOf(ch).let { index ->
            if (index == -1) return@map ch
            val newIndex = if ((index - key) < 0) {
                chars.size + (index - key)
            } else {
                index - key
            }
            return@map chars[newIndex]
        }
    }.joinToString("")
}

private fun tarabar(message: String): String {
    return message.map { ch -> tarabarChars.getOrDefault(ch, ch) }
        .joinToString("")
}

private fun vigenereEncrypt(message: String, key: String): String {
    return message.mapIndexed { messageIndex, ch ->
        chars.indexOf(ch).let { charIndex ->
            if (charIndex == -1) return@mapIndexed ch
            val newIndex = (chars.indexOf(ch) + chars.indexOf(key[messageIndex % key.length])).mod(chars.size)
            chars[newIndex]
        }
    }.joinToString("")
}

private fun vigenereDecrypt(message: String, key: String): String {
    return message.mapIndexed { messageIndex, ch ->
        chars.indexOf(ch).let { charIndex ->
            if (charIndex == -1) return@mapIndexed ch
            val newIndex = (chars.indexOf(ch) - chars.indexOf(key[messageIndex % key.length])).mod(chars.size)
            chars[newIndex]
        }
    }.joinToString("")
}

private fun vigenereKey(origin: String, encrypted: String): String {
    var index = 0
    var keyIndex = 0
    var key = ""

    while (index < encrypted.length) {
        if (chars.indexOf(encrypted[index]) == -1) {
            key += encrypted[index]
            ++index
            continue
        }
        val guessedKeyIndex = (chars.indexOf(encrypted[index]) - keyIndex).mod(chars.size)

        if (chars[guessedKeyIndex] == origin[index]) {
            key += chars[keyIndex]
            ++index
            keyIndex = 0
        } else {
            ++keyIndex
        }
    }

    return key
}

private fun transposition(message: String, rowLength: Int): String {
    return message.replace(' ', '_').chunked(rowLength).joinToString("\n")
}
