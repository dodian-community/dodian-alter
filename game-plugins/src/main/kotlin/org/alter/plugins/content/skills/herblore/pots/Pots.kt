package org.alter.plugins.content.skills.herblore.pots

import io.github.oshai.kotlinlogging.KotlinLogging
import org.alter.game.fs.def.ItemDef
import org.alter.game.model.attr.INTERACTING_ITEM_SLOT
import org.alter.game.model.attr.OTHER_ITEM_SLOT_ATTR
import org.alter.game.model.entity.Player
import org.alter.api.Skills
import org.alter.api.cfg.Items

import org.alter.api.ext.*


class Pot(val unfinished: Int, val secondary: Int, val finished: IntArray, val minLevel: Int, val xpAwarded: Double) {
    fun make(player: Player) {
        if(player.replaceItemAndRemoveAnother(unfinished, finished[2], secondary.toItem())){
            player.animate(id = 363, delay = 0)
            player.playSound(2608, 1, 0)
            player.addXp(Skills.HERBLORE, xpAwarded)
        }
    }

    fun charge(player: Player) {
        val srcSlot = player.attr[INTERACTING_ITEM_SLOT]!!
        val dstSlot = player.attr[OTHER_ITEM_SLOT_ATTR]!!
        val chargeCount = finished.indexOf(player.inventory[dstSlot]!!.id)+1
        if(chargeCount > 3) {
            player.nothingMessage()
        } else if(chargeCount > 0) {
            consume(player)
            if(player.inventory.remove(finished[chargeCount-1], beginSlot = srcSlot).hasSucceeded())
                player.inventory.add(finished[chargeCount+1])

        } else { // all other values are invalid for charging pots
            // could only happen from improperly managed pot
            logger.error("invalid chargeCount for pot at ${player.inventory[dstSlot]} with charges = $chargeCount")
        }
    }

    fun consume(player: Player) {
        val chargeCount = finished.indexOf(player.getInteractingItem().id)+1
        if(chargeCount == 1 && player.inventory.remove(finished[0], beginSlot = player.getInteractingItemSlot()).hasSucceeded()) {
            player.message("You drink some of your ${player.world.definitions.get(ItemDef::class.java, finished[chargeCount-1]).name}.")
            player.animate(829)
            player.inventory.add(Items.VIAL)
            player.message("You have finished your potion.")
        } else if(chargeCount != 0 && player.inventory.remove(finished[chargeCount-1], beginSlot = player.getInteractingItemSlot()).hasSucceeded()) {
            player.message("You drink some of your ${player.world.definitions.get(ItemDef::class.java, finished[chargeCount-1]).name}")
            player.animate(829)
            player.inventory.add(finished[chargeCount-2], beginSlot = player.getInteractingItemSlot())
            player.message("You have ${chargeCount-1} doses of potion left.")
        } else { // not valid charge config
            // could only happen if [Pot.consume()] was called from an invalid pot registration
            logger.error("invalid pot registration for ${player.getInteractingItem().id}")
        }
    }


    companion object {
        private val logger = KotlinLogging.logger{}
    }
}

enum class Pots(val pot: Pot) {
    ATTACK(Pot(Items.GUAM_POTION_UNF, Items.EYE_OF_NEWT, intArrayOf(Items.ATTACK_POTION1, Items.ATTACK_POTION2, Items.ATTACK_POTION3, Items.ATTACK_POTION4), 3, 25.0)),
    STRENGTH(Pot(Items.TARROMIN_POTION_UNF, Items.LIMPWURT_ROOT, intArrayOf(Items.STRENGTH_POTION1, Items.STRENGTH_POTION2, Items.STRENGTH_POTION3, Items.STRENGTH_POTION4), 14, 50.0)),
    DEFENCE(Pot(Items.RANARR_POTION_UNF, Items.WHITE_BERRIES, intArrayOf(Items.DEFENCE_POTION1, Items.DEFENCE_POTION2, Items.DEFENCE_POTION3, Items.DEFENCE_POTION4), 25, 75.0)),
    PRAYER(Pot(Items.RANARR_POTION_UNF, Items.SNAPE_GRASS, intArrayOf(Items.PRAYER_POTION1, Items.PRAYER_POTION2, Items.PRAYER_POTION3, Items.PRAYER_POTION4), 38, 87.5)),
    SUPER_ATTACK(Pot(Items.IRIT_POTION_UNF, Items.EYE_OF_NEWT, intArrayOf(Items.SUPER_ATTACK1, Items.SUPER_ATTACK2, Items.SUPER_ATTACK3, Items.SUPER_ATTACK4), 46, 100.0)),
    SUPER_STRENGTH(Pot(Items.KWUARM_POTION_UNF, Items.LIMPWURT_ROOT, intArrayOf(Items.SUPER_STRENGTH1, Items.SUPER_STRENGTH2, Items.SUPER_STRENGTH3, Items.SUPER_STRENGTH4), 55, 125.0)),
    SUPER_RESTORE(Pot(Items.SNAPDRAGON_POTION_UNF, Items.RED_SPIDERS_EGGS, intArrayOf(Items.SUPER_RESTORE1, Items.SUPER_RESTORE2, Items.SUPER_RESTORE3, Items.SUPER_RESTORE4), 60, 142.5)),
    SUPER_DEFENCE(Pot(Items.CADANTINE_POTION_UNF, Items.WHITE_BERRIES, intArrayOf(Items.SUPER_DEFENCE1, Items.SUPER_DEFENCE2, Items.SUPER_DEFENCE3, Items.SUPER_DEFENCE4), 65, 150.0)),
    RANGING(Pot(Items.DWARF_WEED_POTION_UNF, Items.WINE_OF_ZAMORAK, intArrayOf(Items.RANGING_POTION1, Items.RANGING_POTION2, Items.RANGING_POTION3, Items.RANGING_POTION4), 75, 162.5)),
    ANTIFIRE(Pot(Items.TORSTOL_POTION_UNF, Items.WILLOW_ROOTS, intArrayOf(Items.ANTIFIRE_POTION1, Items.ANTIFIRE_POTION2, Items.ANTIFIRE_POTION3, Items.ANTIFIRE_POTION4), 79, 157.5)),
}