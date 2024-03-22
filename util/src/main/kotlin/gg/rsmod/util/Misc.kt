package gg.rsmod.util

/**
 * @author Tom <rspsmods@gmail.com>
 */
object Misc {

    val DIRECTION_DELTA_X = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    val DIRECTION_DELTA_Z = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)

    fun getPlayerWalkingDirection(dx: Int, dy: Int): Int {
        if (dx == -1 && dy == -1) {
            return 0
        }
        if (dx == 0 && dy == -1) {
            return 1
        }
        if (dx == 1 && dy == -1) {
            return 2
        }
        if (dx == -1 && dy == 0) {
            return 3
        }
        if (dx == 1 && dy == 0) {
            return 4
        }
        if (dx == -1 && dy == 1) {
            return 5
        }
        if (dx == 0 && dy == 1) {
            return 6
        }
        return if (dx == 1 && dy == 1) {
            7
        } else -1
    }

    fun getPlayerRunningDirection(dx: Int, dy: Int): Int {
        if (dx == -2 && dy == -2)
            return 0
        if (dx == -1 && dy == -2)
            return 1
        if (dx == 0 && dy == -2)
            return 2
        if (dx == 1 && dy == -2)
            return 3
        if (dx == 2 && dy == -2)
            return 4
        if (dx == -2 && dy == -1)
            return 5
        if (dx == 2 && dy == -1)
            return 6
        if (dx == -2 && dy == 0)
            return 7
        if (dx == 2 && dy == 0)
            return 8
        if (dx == -2 && dy == 1)
            return 9
        if (dx == 2 && dy == 1)
            return 10
        if (dx == -2 && dy == 2)
            return 11
        if (dx == -1 && dy == 2)
            return 12
        if (dx == 0 && dy == 2)
            return 13
        if (dx == 1 && dy == 2)
            return 14
        return if (dx == 2 && dy == 2) 15 else -1
    }

    fun IntRange.toArray(): Array<Int> {
        return toList().toTypedArray()
    }

    fun getIndefiniteArticle(word: String): String {
        val first = word.lowercase().first()
        val vowel = first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u'
        val numeric = word.first().equals(Regex(".*[0-9].*"))
        val special = listOf("bolts", "arrows", "coins", "vambraces", "chaps", "grapes", "silk", "bread", "grey wolf fur", "spice").filter { it in word }
        val some = special.isNotEmpty()
        return (if (numeric) "" else if (vowel) "an" else if (some) "some" else "a") + " " + word
    }
    /**
     * Formats the string as display name.
     * @param name The string to format.
     * @return The formatted name.
     */
    fun formatForDisplay(name: String): String {
        val newName = StringBuilder()
        var wasSpace = true
        for (c in name.replace("_".toRegex(), " ").lowercase()) {
            if (wasSpace) {
                newName.append(c.uppercase())
                wasSpace = false
            } else {
                newName.append(c)
            }
            if (c == ' ') {
                wasSpace = true
            }
        }
        return newName.toString()
    }
}