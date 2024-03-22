package org.alter.plugins.content.area.taverley.dungeon

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

on_obj_option(obj = Objs.OBSTACLE_PIPE_16509, option = "Squeeze-through") {
    if (player.getSkills().getCurrentLevel(Skills.AGILITY) > 69) {
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
} else {
    player.message("You need a agility of 70 to squeeze through.")
}
}

on_obj_option(obj = Objs.STRANGE_FLOOR, option = "Jump-over") {
    if (player.getSkills().getCurrentLevel(Skills.AGILITY) > 69) {
        val obj = player.getInteractingGameObj()
        val isWest = player.tile.x > obj.tile.x
        val offsetX = if (isWest) -1 else 2
        val FloorStartTile = Tile(obj.tile.x + 0, obj.tile.z)
        val FloorEndTile = Tile(obj.tile.x + 1 + offsetX, obj.tile.z + 0)

        player.lockingQueue() {
            if (player.tile != FloorStartTile) {
                val distance = player.tile.getDistance(FloorStartTile)
                player.walkTo(FloorStartTile)
                //wait(distance + 0)
            } else {
                wait(1)
            }

            player.faceTile(Tile(obj.tile.x + offsetX, obj.tile.z + 0))
            wait(1)

            // Loop for forced movement
            for (i in 1..2) {
                player.queue {
                    val move = ForcedMovement.of(
                        player.tile, Tile(obj.tile.x + offsetX, obj.tile.z + 0),
                        clientDuration1 = if (i == 1) 50 else 60, clientDuration2 = 60,
                        directionAngle = if (isWest) Direction.WEST.angle else Direction.EAST.angle,
                        lockState = LockState.NONE
                    )
                    player.animate(6132)
                    player.forceMove(this, move)
                }
                wait(1)
            }
            waitTile(FloorEndTile)
        }
    } else {
        player.message("You need a agility of 70 to jump over.")
    }
}

on_obj_option(obj = Objs.ROCKS_154, option = "Jump-up") {
    if (player.getSkills().getCurrentLevel(Skills.SLAYER) > 59) {
        player.queue {
            player.moveTo(tile = Tile(x = 2888, z = 9823, height = 1))
        }
    } else {
        player.message("You need a slayer of 60 to jump up to the higher level.")
    }
}
on_obj_option(obj = Objs.ROCKS_14106, option = "Jump-down") {
    if (player.getSkills().getCurrentLevel(Skills.SLAYER) > 59) {
        player.queue {
            player.moveTo(tile = Tile(x = 2886, z = 9823, height = 0))
        }
    } else {
        player.message("You need a slayer of 60 to jump down.")
    }
}
on_obj_option(obj = Objs.STEPS_30189, option = "Climb") {
    if (player.getSkills().getCurrentLevel(Skills.SLAYER) > 69) {
        player.queue {
            player.moveTo(tile = Tile(x = 2880, z = 9825, height = 1))
        }
    } else {
        player.message("You need a slayer of 70 to climb up to the higher level.")
    }
}
on_obj_option(obj = Objs.STEPS_30190, option = "Climb") {
    if (player.getSkills().getCurrentLevel(Skills.SLAYER) > 69) {
        player.queue {
            player.moveTo(tile = Tile(x = 2883, z = 9825, height = 0))
        }
    } else {
        player.message("You need a slayer of 70 to jump down.")
    }
}