package org.alter.plugins.content.area.catherby.shops

import org.alter.game.model.shop.PurchasePolicy
import org.alter.plugins.content.mechanics.shops.CoinCurrency

create_shop("Gerrant's Fishy Business", CoinCurrency(), purchasePolicy = PurchasePolicy.BUY_STOCK) {
    items[0] = ShopItem(Items.SMALL_FISHING_NET, 5, 5, 1)
    items[1] = ShopItem(Items.FLY_FISHING_ROD, 5, 20, 2)
    items[2] = ShopItem(Items.LOBSTER_POT, 5, 25, 5)
    items[3] = ShopItem(Items.HARPOON, 5, 35, 10)
    items[4] = ShopItem(Items.RAW_SHRIMPS, 1, 5, 0)
}