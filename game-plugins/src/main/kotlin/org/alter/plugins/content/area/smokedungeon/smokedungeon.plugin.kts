package org.alter.plugins.content.area.smokedungeon

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
on_obj_option(obj = Objs.ROPE_6439, option = "Climb-Up") {
    player.moveTo(2596, 3409)
}

on_obj_option(obj = Objs.GATE_6451, option = "open") {
    player.queue { dialog()}
}
on_obj_option(obj = Objs.GATE_6452, option = "open") {
    player.queue { dialog()}
}

suspend fun QueueTask.dialog() {
    val talkingnpc = Npcs.KING_BLACK_DRAGON_2642
    chatNpc(npc = talkingnpc, message = "You must sacrifice 5 dragon bones to enter.", animation = 590)
    when (options("No thank you...", "Sacrifice 5 dragon bones")) {
        1 -> {
            chatPlayer("No thank you...")
        }
        2 -> {
            chatPlayer("Sacrifice 5 dragon bones")

            val inventory = player.inventory
            if (inventory.getItemCount(Items.DRAGON_BONES) >= 5) {

                player.lock()
                player.inventory.remove(Items.DRAGON_BONES, 5)
                wait(2)
                player.moveTo(3305, 9375, 0)
                player.message("A Strange force makes you walk through the gate.")
                wait(2)
                player.unlock()
            } else
                chatPlayer("Oh dear, I don't actually seem to have enough dragon bones.", animation = 554)
        }
    }
}