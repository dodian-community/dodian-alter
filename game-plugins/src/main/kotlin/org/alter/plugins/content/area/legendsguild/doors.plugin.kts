package org.alter.plugins.content.area.legendsguild

// TODO: Fix the doors closing.

val GATES = intArrayOf(Objs.LEGENDS_GUILD_DOOR, Objs.LEGENDS_GUILD_DOOR_2897)

GATES.forEach { obj ->
    on_obj_option(obj, "open", lineOfSightDistance = 2) {
            if (obj == 2897) {
                player.lock()
                if (player.tile.x == 2729 && player.tile.z == 3373) {
                    player.moveTo(2729, 3374, 0)
                }
                world.queue {
                    world.openDoor(world.getObject(Tile(2728, 3373), 0)!!, 2896, invertRot = true)
                    world.openDoor(world.getObject(Tile(2729, 3373), 0)!!, 2897)

                }
                //val curr = player.tile
                //val dst = if (curr.sameAs(2728, 3349)) Tile(2729, 3350) else if (curr.sameAs(2729, 3350)) Tile(2729, 3349) else return@on_obj_option
                val dst = Tile(2729, 3374)
                player.moveTo(dst)
                player.unlock()
            }

            if (obj == 2896) {
                player.lock()
                if (player.tile.x == 2728 && player.tile.z == 3373) {
                    player.moveTo(2728, 3374, 0)
                }
                world.queue {
                    world.openDoor(world.getObject(Tile(2728, 3373), 0)!!, 2896, invertRot = true)
                    world.openDoor(world.getObject(Tile(2729, 3373), 0)!!, 2897)
                }
                //val curr = player.tile
                //val dst = if (curr.sameAs(2729, 3349)) Tile(2728, 3350) else if (curr.sameAs(2728, 3350)) Tile(2728, 3349) else return@on_obj_option
                val dst = Tile(2729, 3374)
                player.moveTo(dst)
                player.unlock()
            }
    }
}