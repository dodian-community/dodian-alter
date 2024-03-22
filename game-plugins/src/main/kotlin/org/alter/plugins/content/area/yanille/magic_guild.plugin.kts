package gg.rsmod.plugins.content.areas.yanille
/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

// Doors
//To-Do:    Close doors after opening, the closing wont work atm.
//          Also need to move player when opening.
//          When players now try to close, this plugin trows an error.
//          Nothing is failing after the error.
val DOORS = intArrayOf(Objs.MAGIC_GUILD_DOOR, Objs.MAGIC_GUILD_DOOR_1733)

DOORS.forEach { obj ->
    on_obj_option(obj, "Open", lineOfSightDistance = 2) {
        if (obj == 1732) {
            player.lock()
            world.queue {
                world.openDoor(world.getObject(Tile(2597, 3087), 0)!!, Objs.MAGIC_GUILD_DOOR, invertRot = true)
                world.openDoor(world.getObject(Tile(2597, 3088), 0)!!, Objs.MAGIC_GUILD_DOOR_1733)
                world.openDoor(world.getObject(Tile(2584, 3088), 0)!!, Objs.MAGIC_GUILD_DOOR, invertRot = true)
                world.openDoor(world.getObject(Tile(2584, 3087), 0)!!, Objs.MAGIC_GUILD_DOOR_1733)

            }
            player.unlock()
        }
        if (obj == 1733) {
            player.lock()
            world.queue {
                world.openDoor(world.getObject(Tile(2597, 3087), 0)!!, Objs.MAGIC_GUILD_DOOR, invertRot = true)
                world.openDoor(world.getObject(Tile(2597, 3088), 0)!!, Objs.MAGIC_GUILD_DOOR_1733)
                world.openDoor(world.getObject(Tile(2584, 3088), 0)!!, Objs.MAGIC_GUILD_DOOR, invertRot = true)
                world.openDoor(world.getObject(Tile(2584, 3087), 0)!!, Objs.MAGIC_GUILD_DOOR_1733)

            }
            player.unlock()
        }
    }
}


//Basement
on_obj_option(obj = Objs.LADDER_17384, option = "Climb-Down") {
    when(player.tile.regionId) {
        11317 -> { // Water obelisk
            player.moveTo(x = 2842, z = 9823)
        }

        else -> player.moveTo(2594, 9486)
    }
}
on_obj_option(obj = Objs.LADDER_17385, option = "Climb-Up")
{
    when(player.tile.regionId) {
        11418 -> { // IceQueen Dungeon
            player.moveTo(x = 2846, z = 3516)
        }
        11673 -> { // Taverley Dungeon
            player.moveTo(x = 2884, z = 3398)
        }
        11417 -> { // Water obelisk
            player.moveTo(x = 2842, z = 3423)
        }

        else ->  player.moveTo(x = 2594, z = 3086) //Magic Guild
    }
}
on_obj_option(obj = Objs.GATE_2154, option = "Open") {
    player.message("The gate is locked")
}
on_obj_option(obj = Objs.GATE_2155, option = "Open") {
    player.message("The gate is locked")
}
//Staircases
on_obj_option(obj = Objs.STAIRCASE_15645, option = "climb-Up") {
    when(player.tile.height) {
        1 -> {
            player.moveTo(x = 2591, z = 3087, 2)
        }
        else ->  player.moveTo(x = 2591, z = 3092, 1)
    }
}
on_obj_option(obj = Objs.STAIRCASE_15648, option = "climb-down") {
    when(player.tile.height) {
        1 -> {
            player.moveTo(x = 2591, z = 3088, 0)
        }
        else ->  player.moveTo(x = 2591, z = 3083, 1)
    }
}
//Portals
//East Portal
on_obj_option(obj = Objs.MAGIC_PORTAL, option = "Enter") {
    //player.moveTo(3109, 3163, 0)
    player.message("Nothing interesting happens.")
}
//South Portal
on_obj_option(obj = Objs.MAGIC_PORTAL_2157, option = "Enter") {
    //player.moveTo(2908, 3336, 0)
    player.message("Nothing interesting happens.")
}
//South Portal
on_obj_option(obj = Objs.MAGIC_PORTAL_2158, option = "Enter") {
    //player.moveTo(2702, 3405, 0)
    player.message("Nothing interesting happens.")
}



