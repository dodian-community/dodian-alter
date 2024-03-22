package org.alter.api.cfg

import org.alter.game.model.entity.Player
import org.alter.api.ext.getVarp

/**
 * @author Alycia <https://github.com/alycii>
 */
interface Requirement { fun hasRequirement(player: Player): Boolean }
class SkillRequirement(val skill: Int, val level: Int) : Requirement {
    override fun hasRequirement(player: Player): Boolean {
        return player.skillSet.getMaxLevel(skill) >= level
    }
}

class CombatRequirement(val combatLevel: Int) : Requirement {
    override fun hasRequirement(player: Player): Boolean {
        return player.combatLevel >= combatLevel
    }

}