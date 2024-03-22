package org.alter.plugins.content.area.taverley.chats

on_npc_option(npc = Npcs.DRUID, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }
on_npc_option(npc = Npcs.DRUID_3259, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }
on_npc_option(npc = Npcs.UNGADULU, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }

suspend fun QueueTask.dialog() {
    chatPlayer("Hello.", animation = 568)
    chatNpc("Good day to you, may nature smile upon you.", animation = 554)


}