package org.alter.plugins.content.area.taverley

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */


on_obj_option(obj = Objs.DOOR_2861, option = "open") {

    handleDoor1(player)
}


fun handleDoor1(player: Player) {
    val closedDoor = DynamicObject(id = 2861, type = 0, rot = 2, tile = Tile(x = 2900, z = 3473))
    val door = DynamicObject(id = 2861, type = 1, rot = if (player.tile.x == 2900) 1 else 1, tile = Tile(x = 2901, z = 3473))
    player.lock = LockState.DELAY_ACTIONS
    world.remove(closedDoor)
    world.spawn(door)

    player.queue {
        val x = if (player.tile.x == 2900) 2901 else 2900
        val z = 3473
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(door)
        player.lock = LockState.NONE
        world.spawn(closedDoor)
    }
}