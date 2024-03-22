package org.alter.plugins.content.area.yanille.shops

import org.alter.game.model.shop.PurchasePolicy
import org.alter.plugins.content.mechanics.shops.CoinCurrency

create_shop("Yanille General Store", CoinCurrency(), purchasePolicy = PurchasePolicy.BUY_TRADEABLES) {
    items[0] = ShopItem(Items.TINDERBOX, 5, 1, 1)
    items[1] = ShopItem(Items.HAMMER, 5, 1, 1)
    items[2] = ShopItem(Items.KNIFE, 5, 1, 1)
    items[3] = ShopItem(Items.BRONZE_AXE, 10, 12, 1)
    items[4] = ShopItem(Items.BRONZE_PICKAXE, 10, 12, 1)
    items[5] = ShopItem(Items.CHISEL, 5, 1, 1)
    items[6] = ShopItem(Items.ANTIDRAGON_SHIELD, 100, 1100, 100)
    items[7] = ShopItem(Items.SHEARS, 10, 1, 1)
    items[8] = ShopItem(Items.BUCKET, 1337, 2, 1)
}