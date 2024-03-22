package org.alter.plugins.content.skills.thieving.objs

import org.alter.game.model.entity.DynamicObject
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.api.Skills
import org.alter.api.cfg.Items

import org.alter.api.cfg.Objs
import org.alter.api.ext.*
import org.alter.plugins.content.skills.thieving.objs.StallRewards.bakers_stall_steals
import org.alter.plugins.content.skills.thieving.objs.StallRewards.fur_stall_steals
import org.alter.plugins.content.skills.thieving.objs.StallRewards.gem_stall_steals
import org.alter.plugins.content.skills.thieving.objs.StallRewards.silk_stall_steals
import org.alter.plugins.content.skills.thieving.objs.StallRewards.silver_stall_steals
import org.alter.plugins.content.skills.thieving.objs.StallRewards.spice_stall_steals
import gg.rsmod.util.Misc

enum class Stall(val stalls: IntArray, private val levelRequired: Int, val xp: Double, private val respawnCycles: Int, val attemptMsg: String = "", val steals: Array<StallItem>) {
    BAKERS_STALL(intArrayOf(Objs.BAKERS_STALL_11730), 10, 1000.0, 4, steals = bakers_stall_steals),
    SILK_STALL(intArrayOf(Objs.SILK_STALL_11729), 20, 1400.0, 6, steals = silk_stall_steals),
    FUR_STALL(intArrayOf(Objs.FUR_STALL_11732), 40, 1800.0, 10, steals = fur_stall_steals),
    SILVER_STALL(intArrayOf(Objs.SILVER_STALL_11734), 65, 2500.0, 20, steals = silver_stall_steals),
	SPICE_STALL(intArrayOf(Objs.SPICE_STALL_11733), 80, 4800.0, 40, steals = spice_stall_steals),
    GEM_STALL(intArrayOf(Objs.GEM_STALL_11731), 90, 58000.0, 100, steals = gem_stall_steals)
    ;

	fun steal(player: Player, stallId: Int) {
		if (!player.inventory.hasSpace) {
			player.queue {
				messageBox("Your inventory is too full to hold any more.")
			}
			return
		}

		if (player.getSkills()[Skills.THIEVING].currentLevel < levelRequired) {
			player.queue {
				messageBox(
					"You need to be level $levelRequired to steal from the ${
						stallId.getObjName(
							player.world.definitions,
							lowercase = true
						)
					}."
				)
			}
			return
		}

		if (attemptMsg != "") {
			player.filterableMessage(
				"You attempt to steal $attemptMsg from the the ${
					stallId.getObjName(
						player.world.definitions,
						lowercase = true
					)
				}."
			)
		}

		val item = getStolenItem(steals)

		if (player.inventory.add(item).hasSucceeded()) {
			player.queue {
				player.lock()
				wait(1)
				player.animate(832)
				player.playSound(2581)

				val outMsg = "You steal ${
					Misc.getIndefiniteArticle(
						item.getName(
							player.world.definitions,
						).lowercase()
					)
				}."
				player.addXp(Skills.THIEVING, xp)

				player.world.queue {
					val obj = player.getInteractingGameObj()
					player.world.remove(obj)
					player.message(outMsg)

					val other = DynamicObject(obj, getEmptyStall(stallId))
					player.world.spawn(other)
					wait(getRespawnTimers(stallId))
					player.world.remove(other)
					player.world.spawn(DynamicObject(obj))
				}
				player.unlock()
			}
		}
	}

	private fun getStolenItem(steals: Array<StallItem>): Item {
		val list = mutableListOf<Item>()
		val roll = Math.random() * 100

		steals.forEach {
			if (it.chance >= roll)
				list.add(Item(it.itemId, it.amount))
		}

		return if (list.isEmpty()) Item(steals.first().itemId) else Item(list.random())
	}

	private fun getEmptyStall(stallId: Int): Int {
		return when (stallId) {
			Objs.BAKERS_STALL_11730 -> Objs.EMPTY_STALL
			Objs.GEM_STALL_6162, Objs.BAKERY_STALL_6163, Objs.SILVER_STALL_6164 -> Objs.MARKET_STALL_6984
			Objs.SHOP_COUNTER_30279, Objs.SHOP_COUNTER_30280 -> Objs.SHOP_COUNTER_30278
			Objs.CRAFTING_STALL, Objs.FOOD_STALL, Objs.GENERAL_STALL, Objs.MAGIC_STALL, Objs.SCIMITAR_STALL -> Objs.BAMBOO_DESK
			else -> Objs.MARKET_STALL
		}
	}

	private fun getRespawnTimers(stall: Int): Int {
		return when (stall) {
			Objs.BAKERY_STALL_6163 -> respawnCycles * 2
			Objs.SILVER_STALL_6164 -> respawnCycles / 2
			else -> respawnCycles
		}
	}
}

object StallRewards {

	val bakers_stall_steals = arrayOf(
		StallItem(Items.BREAD, chance = 65.0),
		StallItem(Items.CAKE, chance = 25.0),
		StallItem(Items.CHOCOLATE_SLICE, chance = 10.0),
	)

	val silk_stall_steals = arrayOf(
		StallItem(Items.COINS_995, amount = 5, chance = 50.0),
		StallItem(Items.COINS_995, amount = 50, chance = 5.0),
		StallItem(Items.COINS_995, amount = 500, chance = 1.0)
	)

	val spice_stall_steals = arrayOf(
		StallItem(Items.COINS_995, amount = 100, chance = 50.0),
		StallItem(Items.GRIMY_GUAM_LEAF, amount = 1, chance = 32.5),
		StallItem(Items.GRIMY_TARROMIN, amount = 1, chance = 10.5),
		StallItem(Items.GRIMY_RANARR_WEED, amount = 1, chance = 9.0),
		StallItem(Items.GRIMY_IRIT_LEAF, amount = 1, chance = 7.5),
		StallItem(Items.GRIMY_KWUARM, amount = 1, chance = 5.0),
		StallItem(Items.GRIMY_SNAPDRAGON, amount = 1, chance = 3.5),
		StallItem(Items.GRIMY_CADANTINE, amount = 1, chance = 1.0),
		StallItem(Items.GRIMY_DWARF_WEED, amount = 1, chance = 0.75),
	)

	val fur_stall_steals = arrayOf(
		StallItem(Items.COINS_995, amount = 100, chance = 50.0),
		StallItem(Items.COINS_995, amount = 621, chance = 32.5),
		StallItem(Items.COINS_995, amount = 1250, chance = 10.5),
		StallItem(Items.COWHIDE, amount = 1, chance = 9.0),
		StallItem(Items.GREEN_DRAGONHIDE, amount = 1, chance = 7.5),
		StallItem(Items.BLUE_DRAGONHIDE, amount = 1, chance = 5.0),
		StallItem(Items.RED_DRAGONHIDE, amount = 1, chance = 3.5),
		StallItem(Items.BLACK_DRAGONHIDE, amount = 1, chance = 1.0),
	)

	val silver_stall_steals = arrayOf(
		StallItem(Items.COINS_995, amount = 100, chance = 50.0),
		StallItem(Items.COINS_995, amount = 621, chance = 32.5),
		StallItem(Items.COINS_995, amount = 1250, chance = 10.5),
		StallItem(Items.BRONZE_BAR, amount = 1, chance = 9.0),
		StallItem(Items.IRON_BAR, amount = 1, chance = 7.5),
		StallItem(Items.STEEL_BAR, amount = 1, chance = 5.0),
		StallItem(Items.GOLD_BAR, amount = 1, chance = 3.5),
		StallItem(Items.SILVER_BAR, amount = 1, chance = 3.5),
		StallItem(Items.MITHRIL_BAR, amount = 1, chance = 1.0),
		StallItem(Items.ADAMANTITE_BAR, amount = 1, chance = 0.5),
		StallItem(Items.RUNITE_BAR, amount = 1, chance = 0.002)

	)

	val gem_stall_steals = arrayOf(
		StallItem(Items.COINS_995, chance = 62.0, amount = 5),
		StallItem(Items.COINS_995, chance = 62.0, amount = 15),
		StallItem(Items.COINS_995, chance = 30.0, amount = 99),
		StallItem(Items.UNCUT_SAPPHIRE, chance = 22.0),
		StallItem(Items.UNCUT_EMERALD, chance = 13.3),
		StallItem(Items.UNCUT_RUBY, chance = 3.91),
		StallItem(Items.UNCUT_DIAMOND, chance = 0.781),
		StallItem(Items.UNCUT_DRAGONSTONE, chance = 0.0333),
		StallItem(Items.UNCUT_ONYX, chance = 0.002)
	)
}