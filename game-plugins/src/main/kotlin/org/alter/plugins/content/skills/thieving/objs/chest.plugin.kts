package org.alter.plugins.content.skills.thieving.objs

Chest.values().forEach{ chest ->
    chest.Chests.forEach { id ->
        if (objHasOption(id, "Open")) {
            on_obj_option(id, "Open") {
                chest.steal(player, id)
            }
        } else if (objHasOption(id, "Open")) {
            on_obj_option(id, "Open") {
                chest.steal(player, id)
            }
        }
    }
}