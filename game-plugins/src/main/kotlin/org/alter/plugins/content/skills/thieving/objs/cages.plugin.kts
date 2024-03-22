package org.alter.plugins.content.skills.thieving.objs

Cage.values().forEach{ cage ->
    cage.Cages.forEach { id ->
        if (objHasOption(id, "Unlock")) {
            on_obj_option(id, "Unlock") {
                cage.steal(player, id)
            }
        } else if (objHasOption(id, "Unlock")) {
            on_obj_option(id, "Unlock") {
                cage.steal(player, id)
            }
        }
    }
}