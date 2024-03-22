package org.alter.plugins.content.area.fishing_guild.fishing

import org.alter.plugins.content.skills.fishing.data.FishingLocation
import org.alter.plugins.content.skills.fishing.data.FishingSpot
import org.alter.plugins.content.skills.fishing.data.FishingSpots

val net_spots = FishingLocation()
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2605, 3410, 0)))
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2607,3410,0)))
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2608,3410,0)))
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2602,3411,0)))
net_spots.spots.add(FishingSpot(FishingSpots.NET, Tile(2602,3412,0)))
net_spots.numberOfFishingSpots = 2
net_spots.register()

val lure_spots = FishingLocation()
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2605, 3413, 0)))
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2606,3413,0)))
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2607,3413,0)))
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2608,3413,0)))
lure_spots.spots.add(FishingSpot(FishingSpots.LURE, Tile(2609,3413,0)))
lure_spots.numberOfFishingSpots = 2
lure_spots.register()