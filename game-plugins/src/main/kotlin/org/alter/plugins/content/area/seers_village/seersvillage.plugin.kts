package gg.rsmod.plugins.content.areas.seers_village


on_obj_option(obj = Objs.LOGS_5581, option = "Take-axe") {
    player.inventory.add(item = Items.BRONZE_AXE,1)
    player.message("You have taken Eikenb00m's bronze axe.")
}

//Ladder spinningwheel house
on_obj_option(obj = Objs.LADDER_25938, option = "Climb-Up") {
    player.moveTo(2715, 3471, 1)
}
on_obj_option(obj = Objs.LADDER_25939, option = "Climb-Down") {
    player.moveTo(2715, 3471, 0)
}
on_obj_option(obj = Objs.LADDER_26118, option = "Climb-Up") {
    player.moveTo(2714, 3472, 3)
}
on_obj_option(obj = Objs.TRAPDOOR_26119, option = "Climb-Down") {
    player.moveTo(2714, 3472, 1)
}