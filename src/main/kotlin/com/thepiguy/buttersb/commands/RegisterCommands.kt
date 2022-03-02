package com.thepiguy.buttersb.commands

import com.thepiguy.buttersb.ButterSB
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager

class RegisterCommands {
    fun registerCommands() {
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("buttersb").executes {
                ButterSB.config = ButterSB.buttercfg.gui()
                1
            }
        )
    }
}