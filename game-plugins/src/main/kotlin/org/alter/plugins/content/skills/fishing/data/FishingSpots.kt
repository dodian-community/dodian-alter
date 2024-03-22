package org.alter.plugins.content.skills.fishing.data

import org.alter.api.cfg.Items
import org.alter.api.cfg.Npcs
import org.alter.api.cfg.Objs

/**
 * @author Fritz <frikkipafi@gmail.com>
 */

val SMALL_NET = arrayOf(Npcs.FISHING_SPOT_3317, Npcs.FISHING_SPOT_1530)
val BIG_NET = arrayOf(Npcs.FISHING_SPOT_1511, Npcs.FISHING_SPOT_1520)
val FISHING_ROD1 = arrayOf(Npcs.FISHING_SPOT_1530, Npcs.ROD_FISHING_SPOT_1527, Npcs.ROD_FISHING_SPOT_1526)
val FLYFISHING_ROD = arrayOf(Npcs.ROD_FISHING_SPOT_1527, Npcs.ROD_FISHING_SPOT_1526)

//fishing spot 1523

enum class FishingSpots(val spotEntityId: Int, val option: String, val toolId: Int, val baitId: Int, val animation: Int, vararg fish: Fish) {
    TUTORIAL_NET(Npcs.FISHING_SPOT_3317, "net", Items.SMALL_FISHING_NET, -1, 621, Fish.SHRIMP),
    NET(Npcs.FISHING_SPOT_1530, "net", Items.SMALL_FISHING_NET, -1, 621, Fish.SHRIMP, Fish.ANCHOVIES),
    BAIT(Npcs.FISHING_SPOT_1530, "bait", Items.FISHING_ROD, Items.FISHING_BAIT, 622, Fish.SARDINE, Fish.HERRING),
    BAIT2(Npcs.ROD_FISHING_SPOT_1527, "bait", Items.FISHING_ROD, Items.FISHING_BAIT, 622, Fish.PIKE),
    BAIT3(Npcs.ROD_FISHING_SPOT_1526, "bait", Items.FISHING_ROD, Items.FISHING_BAIT, 622, Fish.PIKE),
    LURE(Npcs.ROD_FISHING_SPOT_1527, "lure", Items.FLY_FISHING_ROD, Items.FEATHER, 622, Fish.TROUT, Fish.SALMON),
    LURE2(Npcs.ROD_FISHING_SPOT_1526, "lure", Items.FLY_FISHING_ROD, Items.FEATHER, 622, Fish.TROUT, Fish.SALMON, Fish.RAINBOWFISH),
    BIG_NET(Npcs.FISHING_SPOT_1511, "net", Items.BIG_FISHING_NET, -1, 621, Fish.MACKEREL, Fish.COD, Fish.BASS),
    BIG_NET2(Npcs.FISHING_SPOT_1520, "big net", Items.BIG_FISHING_NET, -1, 621, Fish.MACKEREL, Fish.COD, Fish.BASS);
    private val fish = fish

    fun getFish(): Array<out Fish> { return this.fish
    }
}