package org.alter.plugins.content.skills.thieving.objs

import org.alter.game.model.entity.DynamicObject
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.api.Skills
import org.alter.api.cfg.Items

import org.alter.api.cfg.Objs
import org.alter.api.ext.*
import org.alter.plugins.content.skills.thieving.objs.ChestRewards.yanille_chest

import gg.rsmod.util.Misc

enum class Chest(val Chests: IntArray, private val levelRequired: Int, val xp: Double, private val respawnCycles: Int, val attemptMsg: String = "", val steals: Array<ChestItem>) {
    YANILLE(intArrayOf(Objs.CLOSED_CHEST_375), 70, 0.0, 60, steals = yanille_chest)
	//TO-DO Legends chest
    ;

	fun steal(player: Player, ChestId: Int) {
		if (!player.inventory.hasSpace) {
			player.queue {
				messageBox("Your inventory is too full to hold any more.")
			}
			return
		}

		if (player.getSkills()[Skills.THIEVING].currentLevel < levelRequired) {
			player.queue {
				messageBox(
					"You need to be level $levelRequired to unlock from the ${
						ChestId.getObjName(
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
				"You attempt to unlock $attemptMsg from the the ${
					ChestId.getObjName(
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

					val other = DynamicObject(obj, getEmptyChest(ChestId))
					player.world.spawn(other)
					wait(getRespawnTimers(ChestId))
					player.world.remove(other)
					player.world.spawn(DynamicObject(obj))
				}
				player.unlock()
			}
		}
	}

	private fun getStolenItem(steals: Array<ChestItem>): Item {
		val list = mutableListOf<Item>()
		val roll = Math.random() * 100

		steals.forEach {
			if (it.chance >= roll)
				list.add(Item(it.itemId, it.amount))
		}

		return if (list.isEmpty()) Item(steals.first().itemId) else Item(list.random())
	}

	private fun getEmptyChest(ChestId: Int): Int {
		return when (ChestId) {
			Objs.CLOSED_CHEST_375 -> Objs.OPEN_CHEST_378
			else -> Objs.OPEN_CHEST_378
		}
	}

	private fun getRespawnTimers(Chest: Int): Int {
		return when (Chest) {
			Objs.CLOSED_CHEST_375 -> respawnCycles * 2
			else -> respawnCycles
		}
	}
}

object ChestRewards {

	val yanille_chest = arrayOf(
		ChestItem(Items.COINS_995, amount = 2000, chance = 65.0),
		ChestItem(Items.COINS_995, amount = 3000, chance = 25.0),
		ChestItem(Items.COINS_995, amount = 4000, chance = 10.0),
		ChestItem(Items.HIGHWAYMAN_MASK, amount = 1, chance = 0.002),
		ChestItem(Items.WIZARD_BOOTS, amount = 1, chance = 0.001),
		ChestItem(Items.RANGER_BOOTS, amount = 1, chance = 0.001),
	)
	val legends_chest = arrayOf(
		ChestItem(Items.COINS_995, amount = 2000, chance = 65.0),
		ChestItem(Items.COINS_995, amount = 3000, chance = 25.0),
		ChestItem(Items.COINS_995, amount = 4000, chance = 10.0),
		ChestItem(Items.HIGHWAYMAN_MASK, amount = 1, chance = 0.002),
		ChestItem(Items.SANTA_HAT, amount = 1, chance = 0.001),
		ChestItem(Items.ROBIN_HOOD_HAT, amount = 1, chance = 0.001),
	)
}