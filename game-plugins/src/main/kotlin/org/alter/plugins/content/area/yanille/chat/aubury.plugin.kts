package org.alter.plugins.content.area.yanille.chat

arrayOf(Npcs.AUBURY_11434).forEach { aubury ->

    if (npcHasOption(npc = aubury, option = "trade")) {
        on_npc_option(npc = aubury, option = "trade") { open_shop(player) }
    }

    if (npcHasOption(npc = aubury, option = "talk-to")) {
        on_npc_option(npc = aubury, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }
    }

}

suspend fun QueueTask.dialog() {
    chatNpc("Do you want to buy some magic equipment?")
    when (options()) {
        1 -> about_your_cape()
        2 -> open_shop(player)
        3 -> no_thank_you()
    }
}

suspend fun QueueTask.options(): Int = options("Can you tell me about your cape?", "Yes please!", "Oh, it's a rune shop. No thank you then.")

suspend fun QueueTask.about_your_cape() {
    chatNpc("Certainly! Skillcapes are a symbol of achievement. Only people who have mastered a skill and reached level 99 can get their hands on them and gain the benefits they carry.", animation = 568)
    chatNpc("The Cape of Dodian has been upgraded with each talisman, allowing you to access all Runecrafting altars. Is there anything else I can help you with?", animation = 554)
}

fun open_shop(p: Player) {
    p.openShop("Aubury's Magic Store.")
}

suspend fun QueueTask.no_thank_you() {
    chatPlayer("Oh, it's a rune shop. No thank you, then.", animation = 568)
    chatNpc("Well, if you find someone who does want runes, please send them my way.", animation = 554)
}
