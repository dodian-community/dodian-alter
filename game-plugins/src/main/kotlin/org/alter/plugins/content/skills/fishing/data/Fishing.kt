package org.alter.plugins.content.skills.fishing.data

import org.alter.game.model.entity.Player
import org.alter.game.model.queue.QueueTask
import org.alter.api.Skills
import org.alter.api.ext.interpolate
import org.alter.api.ext.message
import kotlin.random.Random

/**
 * @author Fritz <frikkipafi@gmail.com>
 */

/**TODO:
 * make bignet options & give random items
 * make message say lowest fish to be caught not highest
 */

class Fishing(val player: Player, val spot: FishingSpots) {
    suspend fun startFishing(it: QueueTask) {
        if(!canFish()) {
            player.animate(-1)
            return
        }

        while(true) {
            player.world.plugins.executeOnStartFishing(player, spot.spotEntityId)
            player.animate(spot.animation)
            it.wait(2)

            if(!canFish()) {
                player.animate(-1)
                break
            }

            val lvl = player.getSkills().getCurrentLevel(Skills.FISHING)

            val randomFish = getRandomFish()

            if(lvl.interpolate(minChance = 60, maxChance = 190, minLvl = randomFish.level, maxLvl = 99, cap = 255)) {
                player.world.plugins.executeOnCatchFish(player, spot.spotEntityId)
                player.message("You catch some fish.")

                if(spot.baitId != -1) {
                    player.inventory.remove(spot.baitId, 1)
                }
                player.inventory.add(randomFish.fishItem, 1)
                player.addXp(Skills.FISHING, randomFish.xp)
            }
            it.wait(2)
        }
    }

    private fun getRandomFish(): Fish {
        return spot.getFish().filter { player.getSkills().getBaseLevel(Skills.FISHING) >= it.level }.random()
    }

    //added from other files.
    /*private fun reward(p: Player, option: FishingOption): Fish {
        if (option.equals(FishingOption.BIG_NET)) {
            when (Random.nextInt(0, 100)) {
                0 -> Fish.OYSTER
                50 -> Fish.CASKET
                90 -> Fish.SEAWEED
            }
        }
        if (option.equals(FishingOption.LURE) && p.inventory.contains(10087)) {
            return Fish.RAINBOW_FISH
        }
        return option.fishType.filter { p.getSkills().getMaxLevel(Skills.FISHING) >= it.levelReq }.random()
    }*/

    private fun canFish(): Boolean {
        if(!hasLevel()) {
            player.message("You need at least level ${minLevel()} fishing to catch fish here.")
            return false
        }

        if(!hasTool()) {
            player.message("You do not have the required tool to fish here.")
            return false
        }

        if(spot.baitId != -1) {
            if(!player.inventory.contains(spot.baitId)) {
                player.message("You do not have any bait to fish here.")
                return false
            }
        }

        if(player.inventory.isFull) {
            player.message("You do not have enough space to hold any more fish.")
            return false
        }

        return true
    }

    private fun hasLevel(): Boolean {
        var hasLevel = false
        spot.getFish().forEach { fish ->
            if(player.getSkills().getCurrentLevel(Skills.FISHING) >= fish.level) {
                hasLevel = true
            }
        }
        return hasLevel
    }

    private fun minLevel(): Int {
        var minLevel = 0
        spot.getFish().forEach { fish ->
            if(fish.level > minLevel) {
                minLevel = fish.level
            }
        }
        return minLevel
    }

    private fun hasTool(): Boolean {
        return player.inventory.contains(spot.toolId)
    }
}