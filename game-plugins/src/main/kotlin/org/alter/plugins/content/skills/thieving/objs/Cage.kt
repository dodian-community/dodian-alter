package org.alter.plugins.content.skills.thieving.objs

import org.alter.game.model.entity.DynamicObject
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.api.Skills
import org.alter.api.cfg.Items

import org.alter.api.cfg.Objs
import org.alter.api.ext.*
import org.alter.plugins.content.skills.thieving.objs.CageRewards.cage_steals

import gg.rsmod.util.Misc

enum class Cage(val Cages: IntArray, private val levelRequired: Int, val xp: Double, private val respawnCycles: Int, val attemptMsg: String = "", val steals: Array<CageItem>) {
    CAGES(intArrayOf(Objs.CAGE_20873), 1, 150.0, 4, steals = cage_steals)
    ;

	fun steal(player: Player, CageId: Int) {
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
						CageId.getObjName(
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
					CageId.getObjName(
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

					val other = DynamicObject(obj, getEmptyCage(CageId))
					player.world.spawn(other)
					wait(getRespawnTimers(CageId))
					player.world.remove(other)
					player.world.spawn(DynamicObject(obj))
				}
				player.unlock()
			}
		}
	}

	private fun getStolenItem(steals: Array<CageItem>): Item {
		val list = mutableListOf<Item>()
		val roll = Math.random() * 100

		steals.forEach {
			if (it.chance >= roll)
				list.add(Item(it.itemId, it.amount))
		}

		return if (list.isEmpty()) Item(steals.first().itemId) else Item(list.random())
	}

	private fun getEmptyCage(CageId: Int): Int {
		return when (CageId) {
			Objs.CAGE_20873 -> Objs.CAGE_20873
			else -> Objs.CAGE_20873
		}
	}

	private fun getRespawnTimers(Cage: Int): Int {
		return when (Cage) {
			Objs.CAGE_20873 -> respawnCycles * 2
			else -> respawnCycles
		}
	}
}

object CageRewards {

	val cage_steals = arrayOf(
		CageItem(Items.COINS_995, amount = 20, chance = 65.0),
		CageItem(Items.COINS_995, amount = 30, chance = 25.0),
		CageItem(Items.COINS_995, amount = 40, chance = 10.0),
		CageItem(Items.COINS_995, amount = 50, chance = 1.0)
	)
}