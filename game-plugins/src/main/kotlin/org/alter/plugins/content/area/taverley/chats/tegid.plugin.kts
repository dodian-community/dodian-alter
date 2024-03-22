package org.alter.plugins.content.area.taverley.chats

on_npc_option(npc = Npcs.TEGID, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }

suspend fun QueueTask.dialog() {
    chatNpc("Ah, another adventurer come to interrupt my laundry duties. <br>What can I do for you?", animation = 554)
    chatPlayer("Hey there! Just wondering if you have any tasks for me?", animation = 568)
    chatNpc("Tasks?", animation = 554)
    chatNpc("Well, if you're offering, I could use some help fetching water from the lake. <br>These robes won't clean themselves, you know!", animation = 554)


}