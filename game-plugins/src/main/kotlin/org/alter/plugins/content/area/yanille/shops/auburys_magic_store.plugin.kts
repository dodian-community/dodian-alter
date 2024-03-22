package org.alter.plugins.content.area.yanille.shops

import org.alter.plugins.content.mechanics.shops.CoinCurrency

create_shop("Aubury's Magic Store.", CoinCurrency(), purchasePolicy = PurchasePolicy.BUY_NONE) {
    items[0] = ShopItem(Items.SARADOMIN_STAFF, 10, 405000)
    items[1] = ShopItem(Items.GUTHIX_STAFF, 10, 405000)
    items[2] = ShopItem(Items.ZAMORAK_STAFF, 10, 405000)
    items[3] = ShopItem(Items.MYSTIC_HAT, 10, 450000)
    items[4] = ShopItem(Items.MYSTIC_ROBE_TOP, 10, 1350000)
    items[5] = ShopItem(Items.MYSTIC_ROBE_BOTTOM, 10, 1125000)
    items[6] = ShopItem(Items.MYSTIC_GLOVES, 10, 450000)
    items[7] = ShopItem(Items.MYSTIC_BOOTS, 10, 450000)
}