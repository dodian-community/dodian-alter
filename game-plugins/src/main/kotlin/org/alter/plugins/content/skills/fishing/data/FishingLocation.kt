package org.alter.plugins.content.skills.fishing.data

import org.alter.game.model.Tile

/**
 * @author Fritz <frikkipafi@gmail.com>
 */

// TODO: make fishing spots move out of the MutableList

class FishingLocation {
    var spots: MutableList<FishingSpot> = mutableListOf()
    var numberOfFishingSpots = 1

    fun register() {
        FishingManager.fishing_locations.add(this)
    }
}