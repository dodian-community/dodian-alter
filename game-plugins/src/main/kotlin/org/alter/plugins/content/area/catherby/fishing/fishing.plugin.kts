package org.alter.plugins.content.area.catherby.fishing

import org.alter.plugins.content.skills.fishing.data.FishingLocation
import org.alter.plugins.content.skills.fishing.data.FishingSpot
import org.alter.plugins.content.skills.fishing.data.FishingSpots

val net_spots = FishingLocation()
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2839, 3431, 0)))
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2844,3429,0)))
net_spots.numberOfFishingSpots = 1
net_spots.register()

val lure_spots = FishingLocation()
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2840, 3431, 0)))
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2846,3429,0)))
lure_spots.numberOfFishingSpots = 1
lure_spots.register()