package org.alter.plugins.content.skills.slayer

import org.alter.game.model.attr.SLAYER_AMOUNT
import org.alter.game.model.attr.SLAYER_ASSIGNMENT
import org.alter.game.model.attr.SLAYER_MASTER
import org.alter.game.model.attr.STARTED_SLAYER
import org.alter.plugins.content.skills.slayer.data.SlayerMaster
import org.alter.plugins.content.skills.slayer.data.slayerData

/**
 * @author Alycia <https://github.com/alycii>
 */

val masters = listOf(Npcs.TURAEL, Npcs.SPRIA, Npcs.KRYSTILIA, Npcs.VANNAKA, Npcs.MAZCHNA, Npcs.KONAR_QUO_MATEN, Npcs.CHAELDAR, Npcs.DURADEL)
masters.forEach { npcId ->

    val slayerMaster = SlayerMaster.values().firstOrNull { it.id == npcId } ?: return@forEach

    on_npc_option(npc = npcId, option = "talk-to") {

        player.queue {
            chatNpc("'Ello, and what are you after then?")
            val firstOption = when (player.attr.has(STARTED_SLAYER)) {
                true -> "I need another assignment"
                false -> "Who are you?"
            }

            when (options(
                firstOption,
                "Do you have anything for trade?",
                "Can you teleport me to West Ardougne?",
                "Er... Nothing..."
            )) {
                FIRST_OPTION -> {
                    if (player.attr.has(STARTED_SLAYER)) {
                        giveTask(this, slayerMaster)
                    } else {
                        chatPlayer("Who are you?")
                        chatNpc("I'm one of the elite Slayer Masters.")
                        when (options("What's a slayer?", "Never heard of you...")) {
                            FIRST_OPTION -> {
                                chatPlayer("What's a slayer?")
                                chatNpc("Oh dear, what do they teach you in school?")
                                chatPlayer("Well.. er...")
                                chatNpc(
                                    "I suppose I'll have to educate you then. A slayer is <br> someone who is trained to fight specific creatures. They <br>know these creatures' every weakness and strength. As <br>you can guess it makes killing them a lot easier."
                                )
                                tutorialDialogue(this, slayerMaster)
                            }

                            SECOND_OPTION -> {
                                chatPlayer("Never heard of you...")
                                chatNpc(
                                    "That's because my foe never lives to tell of me. We <br>slayers are a dangerous bunch."
                                )
                                tutorialDialogue(this, slayerMaster)

                            }
                        }
                    }
                }

                SECOND_OPTION -> {
                    chatPlayer("Do you have anything for trade?")
                    chatNpc("I have a wide selection of Slayer equipment; take a look!")
                    player.openShop("Slayer Equipment")
                }

                THIRD_OPTION -> {
                    chatPlayer("Can you Teleport me to West Ardougne?")
                    chatNpc("Sure i can!<br>Here you go!")

                    teleport(player)
                }
            }
        }
    }
    on_npc_option(npc = npcId, option = "Assignment") {
        player.queue {
        if (player.attr.has(STARTED_SLAYER)) {
            giveTask(this, slayerMaster)
        } else {
                chatNpc("You dont know how slayer works!<br>Talk to me first if you want to know!")
            }
        }
    }
    on_npc_option(npc = npcId, option = "Rewards") {
        player.queue {
            chatNpc("This is currently not available.")
        }
    }

}
fun teleport(p: Player) {
    p.queue {
        val npc = player.getInteractingNpc()
        player.lock = LockState.FULL
        npc.forceChat("Ego te movere")
        npc.graphic(108, 10)
        wait(3)
        player.graphic(109, 125)//this needs to be a projectile
        wait(2)
        player.graphic(110, 125)
        wait(2)
        player.moveTo(2531, 3306, 0)
        player.lock = LockState.NONE
    }
}

suspend fun tutorialDialogue(it: QueueTask, slayerMaster: SlayerMaster) {
    when (it.options("Wow, can you teach me?", "Sounds useless to me..")) {
        FIRST_OPTION -> {
            it.chatPlayer("Wow, can you teach me?")
            it.chatNpc("Hmm well I'm not so sure...")
            it.chatPlayer("Pleeeaasssse!")
            it.chatNpc(
                "Oh okay then, you twisted my arm. You'll have to train <br>against specific groups of creatures."
            )
            it.chatPlayer("Okay, what's first?")
            giveTask(it, slayerMaster)
        }
        SECOND_OPTION -> {
            it.chatPlayer("Sounds useless to me.")
            it.chatNpc("Suit yourself.")
        }
    }
}

suspend fun tipsDialogue(it: QueueTask) {
    // TODO: Find all the slayer tip dialogues
    when(it.options("Got any tips for me?", "Okay, great!")) {
        FIRST_OPTION -> {
            it.chatPlayer("Got any tips for me?")
            it.chatNpc("As you're a beginner, I recommend bringing food <br>and a good weapon of your choice.")
            it.chatPlayer("Great, thanks!")
        }
        SECOND_OPTION -> {
            it.chatPlayer("Okay, great!")
        }
    }
}

suspend fun giveTask(it: QueueTask, slayerMaster: SlayerMaster) {
    val player = it.player

    if(player.getSlayerAssignment() != null) {
        it.chatNpc("You're still hunting ${player.getSlayerAssignment()!!.identifier.lowercase()}, you have ${player.attr[SLAYER_AMOUNT]} to go. Come<br>back when you've finished your task.")
        return
    }

    val assignments = slayerData.getAssignmentsForMaster(slayerMaster)

    // Filter the assignments to only include those that meet the requirements
    val validAssignments = assignments.filter { assignment ->
        assignment.requirement.all { it.hasRequirement(player) }
    }

    if (validAssignments.isNotEmpty()) {
        // Get a random assignment from the valid assignments
        val randomAssignment = validAssignments.random()

        // Get the NPC and amount for the random assignment
        val assignment = randomAssignment.assignment
        val amount = when(randomAssignment.amount) {
            0..0 -> slayerMaster.defaultAmount
            else -> randomAssignment.amount
        }

        player.attr[SLAYER_ASSIGNMENT] = assignment.identifier
        player.attr[SLAYER_AMOUNT] = world.random(amount)
        player.attr[SLAYER_MASTER] = slayerMaster.id
    }


    if(!player.attr.has(STARTED_SLAYER)) {
        player.inventory.add(Item(Items.ENCHANTED_GEM))
        it.chatNpc("We'll start you off hunting ${player.getSlayerAssignment()!!.identifier.lowercase()}, you'll need to kill <br>${player.attr[SLAYER_AMOUNT]} of them.")
        it.chatNpc("You'll also need this enchanted gem, it allows Slayer<br>>Masters like myself to contact you and update you on<br>>your progress. Don't worry if you lose it, you can buy<br>>another from any Slayer Master.")
        player.attr[STARTED_SLAYER] = true
        tipsDialogue(it)
    } else {
        it.chatNpc("Excellent, you're doing great. Your new task is to kill<br>>${player.attr[SLAYER_AMOUNT]} ${player.getSlayerAssignment()!!.identifier}.", npc = player.attr[SLAYER_MASTER]!!)
        tipsDialogue(it)
    }
}
