package org.alter.plugins.content.areas.seers_village.chat

arrayOf(Npcs.FRIENDLY_FORESTER_11427).forEach { shop ->

    on_npc_option(npc = shop, option = "talk-to") { player.queue { dialog(this) } }

    on_npc_option(npc = shop, option = "shop") { open_shop(player) }
}

suspend fun dialog(it: QueueTask) {
    it.chatNpc("Hello, <br>Im Eikenb00m's best friend, <br>and i got an empty store!<br>Because Eikenb00m has nothing to..", animation = 567)
    when (it.options("Show me that empty store!.", "Wel have a good day!")) {
        1 -> open_shop(it.player)
        2 -> it.chatPlayer("No thanks.", animation = 588)
    }
}

fun open_shop(p: Player) {
    p.openShop("Forestry Shop")
}