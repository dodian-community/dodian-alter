package org.alter.plugins.content.skills.fishing.data

import org.alter.api.cfg.Items

/**
 * @author Fritz <frikkipafi@gmail.com>
 */

enum class Fish(val fishItem: Int, val level: Int, val xp: Double) {
    SHRIMP(Items.RAW_SHRIMPS, 1, 10.0),
    SARDINE(Items.RAW_SARDINE, 5, 20.0),
    KARAMBWANJI(Items.RAW_KARAMBWANJI, 5, 5.0),
    HERRING(Items.RAW_HERRING, 10, 30.0),
    ANCHOVIES(Items.RAW_ANCHOVIES, 15, 40.0),
    MACKEREL(Items.RAW_MACKEREL, 16, 20.0),
    TROUT(Items.RAW_TROUT, 20, 50.0),
    COD(Items.RAW_COD, 23, 45.0),
    PIKE(Items.RAW_PIKE, 25, 60.0),
    SLIMY_EEL(Items.RAW_SLIMY_EEL, 28, 65.0),
    SALMON(Items.RAW_SALMON, 30, 70.0),
    TUNA(Items.RAW_TUNA, 35, 80.0),
    CAVE_EEL(Items.RAW_CAVE_EEL, 38, 80.0),
    RAINBOWFISH(Items.RAW_RAINBOW_FISH, 38, 80.0),
    LOBSTER(Items.RAW_LOBSTER, 40, 90.0),
    BASS(Items.RAW_BASS, 46, 100.0),
    SWORDFISH(Items.RAW_SWORDFISH, 50, 100.0),
    LAVA_EEL(Items.RAW_LAVA_EEL, 53, 60.0),
    LEAPING_SALMON(Items.LEAPING_SALMON, 58, 70.0),/**6 Str & Agi xp*/
    MONKFISH(Items.RAW_MONKFISH, 62, 120.0),
    KARAMBWAN(Items.RAW_KARAMBWAN, 65, 50.0),
    LEAPING_STURGEON(Items.LEAPING_STURGEON, 70, 80.0),/**7 Str & Agi xp*/
    SHARK(Items.RAW_SHARK, 76, 110.0),
    INFERNAL_EEL(Items.INFERNAL_EEL, 80, 95.0),
    MINNOW(Items.MINNOW_21356, 82, 26.1),
    ANGLERFISH(Items.RAW_ANGLERFISH, 82, 120.0),
    DARK_CRAB(Items.RAW_DARK_CRAB, 85, 130.0),
    SACRED_EEL(Items.SACRED_EEL, 87, 105.0)
}