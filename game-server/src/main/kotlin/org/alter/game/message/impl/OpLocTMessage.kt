package org.alter.game.message.impl

import org.alter.game.message.Message

data class OpLocTMessage(val movementType: Int, val item: Int, val obj: Int, val z: Int, val x: Int, val slot: Int, val hash: Int) : Message