package org.alter.plugins.content.area.yanille.dungeon

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */


//Entering Yanille Dungeon by stairs
on_obj_option(obj = Objs.STAIRCASE_16664, option = "Climb-Down") {
    when (player.tile.regionId) {
        10804 -> { //legends guild
            player.moveTo(x = 2727, z = 9774)
        }

        else -> player.moveTo(2602, 9479)
    }
}

on_obj_option(obj = Objs.STAIRCASE_16665, option = "Climb-Up") {
    when (player.tile.regionId) {
        10904 -> { //legends guild
            player.moveTo(x = 2723, z = 3375)
        }

        else -> player.moveTo(2606, 3079)
    }

}

//Red Key Door
on_obj_option(obj = Objs.DOOR_11728, option = "open") {
    if(!player.inventory.contains(Items.KEY_1543)) {
        player.message("The door is locked.")
        return@on_obj_option
    }

    handleDoor(player)
}


fun handleDoor(player: Player) {
    val closedDoor = DynamicObject(id = 11728, type = 0, rot = 3, tile = Tile(x = 2601, z = 9482))
    val door = DynamicObject(id = 11728, type = 0, rot = if (player.tile.z == 9481) 2 else 2, tile = Tile(x = 2601, z = 9482))
    player.lock = LockState.DELAY_ACTIONS
    world.remove(closedDoor)
    world.spawn(door)

    player.queue {
        val x = 2601
        val z = if (player.tile.z == 9481) 9482 else 9481
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(door)
        player.lock = LockState.NONE
        world.spawn(closedDoor)
    }
}
on_obj_option(obj = Objs.STAIRCASE_15656, option = "Climb-Down") {
    player.moveTo(2621, 9565)
}

on_obj_option(obj = Objs.STAIRCASE_15657, option = "Climb-Up") {
    player.moveTo(2621, 9496)
}
//Pile of ruble up to orange key boss.
on_obj_option(obj = Objs.PILE_OF_RUBBLE_23563, option = "Climb-Up") {
    player.animate(839)
    player.moveTo(2614, 9505)
}
on_obj_option(obj = Objs.PILE_OF_RUBBLE_23564, option = "Climb-Down") {
    player.animate(839)
    player.moveTo(2616, 9571)
}
//Orange Key Monkeybars
on_obj_option(obj = Objs.MONKEYBARS_23567, option = "Swing across") {

    if (!player.inventory.contains(Items.KEY_1544)) {
        player.message("A Strange force hold you form swinging.")
        return@on_obj_option
    }
    val obj = player.getInteractingGameObj()
    val isNorth = player.tile.z > obj.tile.z
    val offsetZ = if (isNorth) -1 else 1
    val monkeyBarsStartTile = Tile(obj.tile.x + 1, obj.tile.z)
    val monkeyBarsEndTile = Tile(obj.tile.x + 1, obj.tile.z + 5 * offsetZ)

    player.lockingQueue() {
        if (player.tile != monkeyBarsStartTile) {
            val distance = player.tile.getDistance(monkeyBarsStartTile)
            player.walkTo(monkeyBarsStartTile)
            wait(distance + 2)
        } else {
            wait(2)
        }

        player.faceTile(Tile(obj.tile.x + 1, obj.tile.z + offsetZ))
        wait(1)

        // Loop for forced movement
        for (i in 1..5) {
            player.animate(if (i == 1) 742 else 744)
            player.queue {
                val move = ForcedMovement.of(
                    player.tile, Tile(obj.tile.x + 1, obj.tile.z + i * offsetZ),
                    clientDuration1 = if (i == 1) 25 else 25, clientDuration2 = 40,
                    directionAngle = if (isNorth) Direction.SOUTH.angle else Direction.NORTH.angle,
                    lockState = LockState.NONE
                )
                player.animate(744)
                player.forceMove(this, move)
            }
            player.animate(744)
            wait(1)
        }
        player.animate(744)
        waitTile(monkeyBarsEndTile)
        player.animate(743)
    }

}
//Pipe
//TO-DO: Animation Timing need to be better.
on_obj_option(obj = Objs.OBSTACLE_PIPE_23140, option = "Squeeze-through") {
    val obj = player.getInteractingGameObj()
    val isWest = player.tile.x > obj.tile.x
    val offsetX = if (isWest) -4 else 5
    val PipeStartTile = Tile(obj.tile.x + 0, obj.tile.z)
    val PipeEndTile = Tile(obj.tile.x + 1 + offsetX, obj.tile.z + 0)

    player.lockingQueue() {
        if (player.tile != PipeStartTile) {
            val distance = player.tile.getDistance(PipeStartTile)
            player.walkTo(PipeStartTile)
            //wait(distance + 0)
        } else {
            wait(1)
        }

        player.faceTile(Tile(obj.tile.x + offsetX, obj.tile.z + 0))
        wait(1)

        // Loop for forced movement
        for (i in 1..2) {
            player.animate(if (i == 1) 749 else 746)
            player.queue {
                val move = ForcedMovement.of(
                    player.tile, Tile(obj.tile.x + offsetX, obj.tile.z + 0),
                    clientDuration1 = if (i == 1) 50 else 60, clientDuration2 = 60,
                    directionAngle = if (isWest) Direction.WEST.angle else Direction.EAST.angle,
                    lockState = LockState.NONE
                )
                player.animate(746)
                player.forceMove(this, move)
            }
            wait(1)
        }
        waitTile(PipeEndTile)
        player.animate(748)
    }
}

//Yellow Key Balancing ledge
//TO-DO: Animation Timing need to be better.
on_obj_option(obj = Objs.BALANCING_LEDGE_23548, option = "Walk-across") {
    if(!player.inventory.contains(Items.KEY_1544)) {
        player.message("A Strange force hold you form balancing.")
        return@on_obj_option
    }
    val obj = player.getInteractingGameObj()
    val isNorth = player.tile.z > obj.tile.z
    val offsetZ = if (isNorth) -1 else 1
    val LedgeStartTile = Tile(obj.tile.x, obj.tile.z)
    val LedgeEndTile = Tile(obj.tile.x, obj.tile.z + 9 * offsetZ)

    player.lockingQueue() {
        if (player.tile != LedgeStartTile) {
            val distance = player.tile.getDistance(LedgeStartTile)
            player.walkTo(LedgeStartTile)
            wait(distance + 2)
        } else {
            wait(2)
        }

        player.faceTile(Tile(obj.tile.x + 0, obj.tile.z + offsetZ))
        wait(1)

        // Loop for forced movement
        for (i in 1..7) {
            player.animate(if (isNorth) 754 else 756)
            player.queue {
                val move = ForcedMovement.of(
                    player.tile, Tile(obj.tile.x + 0, obj.tile.z + i * offsetZ),
                    clientDuration1 = if (i == 1) 30 else 30, clientDuration2 = 30,
                    directionAngle = if (isNorth) Direction.SOUTH.angle else Direction.NORTH.angle,
                    lockState = LockState.NONE
                )
                player.animate(if (isNorth) 754 else 756)
                player.forceMove(this, move)
            }
            player.animate(if (isNorth) 754 else 756)
            wait(1)
        }
        player.animate(if (isNorth) 754 else 756)
        waitTile(LedgeEndTile)
    }
}
