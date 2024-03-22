package org.alter.plugins.content.area.legendsguild.chat

on_npc_option(npc = Npcs.LEGENDS_GUARD, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }
on_npc_option(npc = Npcs.LEGENDS_GUARD_3952, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }

suspend fun QueueTask.dialog() {
    chatNpc("Hello")
    when (options()) {
        1 -> hello()
        2 -> no_thank_you()
    }
}

suspend fun QueueTask.options(): Int = options("What is this place?", "No thank you.")

suspend fun QueueTask.no_thank_you() {
    chatPlayer("No thank you.", animation = 568)
    chatNpc("Well, have a good day.", animation = 554)
}

suspend fun QueueTask.hello() {
    chatPlayer("What is this place?", animation = 568)
    chatNpc("This is the Legends Guild!<br>Where legends are born!.", animation = 554)
}