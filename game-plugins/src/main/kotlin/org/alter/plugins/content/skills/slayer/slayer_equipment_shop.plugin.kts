package org.alter.plugins.content.skills.slayer

import org.alter.plugins.content.mechanics.shops.CoinCurrency

/**
 * @author Alycia <https://github.com/alycii>
 */

create_shop(
    "Slayer Equipment",
    currency = CoinCurrency(),
    purchasePolicy = PurchasePolicy.BUY_NONE,
) {
    var index = 0
    items[index++] = ShopItem(Items.ENCHANTED_GEM, amount = 10000, sellPrice = 0)
    items[index] = ShopItem(Items.MIRROR_SHIELD, amount = 10, sellPrice = 8500)
    items[index] = ShopItem(Items.FACEMASK, amount = 10, sellPrice = 1200)
    items[index] = ShopItem(Items.EARMUFFS, amount = 10, sellPrice = 1200)
    items[index] = ShopItem(Items.NOSE_PEG, amount = 10, sellPrice = 1500)
    items[index] = ShopItem(Items.SPINY_HELMET, amount = 10, sellPrice = 3500)
    items[index] = ShopItem(Items.SLAYER_GLOVES, amount = 10, sellPrice = 2200)
    items[index] = ShopItem(Items.WITCHWOOD_ICON, amount = 10, sellPrice = 5000)

}

val masters = arrayOf(Npcs.TURAEL, Npcs.SPRIA, Npcs.KRYSTILIA, Npcs.VANNAKA, Npcs.MAZCHNA, Npcs.KONAR_QUO_MATEN, Npcs.CHAELDAR, Npcs.DURADEL)
masters.forEach {
    on_npc_option(npc = it, option = "trade") {
        player.openShop("Slayer Equipment")
    }
}