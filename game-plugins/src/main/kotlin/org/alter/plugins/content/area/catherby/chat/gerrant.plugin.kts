package org.alter.plugins.content.areas.catherby.chat

arrayOf(Npcs.GERRANT).forEach { shop ->

    on_npc_option(npc = shop, option = "talk-to") { player.queue { dialog(this) } }

    on_npc_option(npc = shop, option = "trade") { open_shop(player) }
}

suspend fun dialog(it: QueueTask) {
    it.chatNpc("Welcome! <br><br>You can buy fishing equipment at my store.", animation = 567)
    when (it.options("Let's see what you've got then.", "No thanks.")) {
        1 -> open_shop(it.player)
        2 -> it.chatPlayer("Sorry, I'm not interested.", animation = 588)
    }
}

fun open_shop(p: Player) {
    p.openShop("Gerrant's Fishy Business")
}