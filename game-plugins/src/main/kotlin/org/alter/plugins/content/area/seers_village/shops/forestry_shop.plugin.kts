package org.alter.plugins.content.area.seers_village.shops

import org.alter.game.model.shop.PurchasePolicy
import org.alter.plugins.content.mechanics.shops.CoinCurrency

create_shop("Forestry Shop", CoinCurrency(), purchasePolicy = PurchasePolicy.BUY_NONE) {
    items[0] = ShopItem(Items.LOGS, 0, 0, 0)
    items[1] = ShopItem(Items.OAK_LOGS, 0, 0, 0)
    items[2] = ShopItem(Items.WILLOW_LOGS, 0, 0, 0)
    items[3] = ShopItem(Items.MAPLE_LOGS, 0, 0, 0)
    items[4] = ShopItem(Items.YEW_LOGS, 0, 0, 0)
    items[5] = ShopItem(Items.MAGIC_LOGS, 0, 0, 0)
    items[6] = ShopItem(Items.REDWOOD_LOGS, 0, 0, 0)
}