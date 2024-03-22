package org.alter.plugins.content.area.legendsguild

// TODO: Fix the gates closing.

val GATES = intArrayOf(Objs.GATE_2391, Objs.GATE_2392)

GATES.forEach { obj ->
    on_obj_option(obj, "open", lineOfSightDistance = 2) {
            if (obj == 2392) {
                player.lock()
                if (player.tile.x == 2729 && player.tile.z == 3349) {
                    player.moveTo(2729, 3350, 0)
                }
                world.queue {
                    world.openDoor(world.getObject(Tile(2729, 3349), 0)!!, 1574)
                    world.openDoor(world.getObject(Tile(2728, 3349), 0)!!, 1573, invertRot = true)
                    wait(3)
                    //world.closeDoor(world.getObject(Tile(2729, 3349), 0)!!, Objs.GATE_2391, invertTransform = true, invertRot = true)
                    //world.closeDoor(world.getObject(Tile(2728, 3349), 0)!!, Objs.GATE_2392)
                }
                val curr = player.tile
                //val dst = if (curr.sameAs(2728, 3349)) Tile(2729, 3350) else if (curr.sameAs(2729, 3350)) Tile(2729, 3349) else return@on_obj_option
                val dst = Tile(2729, 3350)
                player.queue { dialog() }
                player.moveTo(dst)
                player.unlock()
            }

            if (obj == 2391) {
                player.lock()
                if (player.tile.x == 2728 && player.tile.z == 3349) {
                    player.moveTo(2728, 3350, 0)
                }
                world.queue {
                    world.openDoor(world.getObject(Tile(2729, 3349), 0)!!, 1574,)
                    world.openDoor(world.getObject(Tile(2728, 3349), 0)!!, 1573, invertRot = true)
                    wait(3)
                    //world.closeDoor(world.getObject(Tile(2729, 3349), 0)!!, Objs.GATE_2391)
                    //world.closeDoor(world.getObject(Tile(2728, 3349), 0)!!, Objs.GATE_2392)
                }
                val curr = player.tile
                //val dst = if (curr.sameAs(2729, 3349)) Tile(2728, 3350) else if (curr.sameAs(2728, 3350)) Tile(2728, 3349) else return@on_obj_option
                val dst = Tile(2729, 3350)
                player.queue { dialog() }
                player.moveTo(dst)
                player.unlock()
            }
    }
}


suspend fun QueueTask.dialog() {

    val guard = Npcs.LEGENDS_GUARD
    chatNpc(npc = guard, message = "!!!Attention!!<br><br>Legends' Guild Member Approaching!", animation = 590)
    }