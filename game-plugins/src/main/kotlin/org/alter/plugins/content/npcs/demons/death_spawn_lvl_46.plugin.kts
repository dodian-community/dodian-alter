package org.alter.plugins.content.npcs.demons

import org.alter.game.model.combat.SlayerAssignment


val ids = intArrayOf(Npcs.DEATH_SPAWN)


ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
        }
        stats {
            hitpoints = 60
            attack = 67
            strength = 7
            defence = 30
        }
        bonuses {
            defenceStab = 20
            defenceSlash = 20
            defenceCrush = 20
            defenceRanged = 20
        }
        anims {
            attack = 9459
            death = 9460
            block = 9461
        }
        slayer {
            assignment = SlayerAssignment.DEATHSPAWN
            xp = 50.0
            levelRequirement = 30
        }
    }
}