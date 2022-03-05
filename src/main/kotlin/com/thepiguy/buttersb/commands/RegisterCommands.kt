package com.thepiguy.buttersb.commands

import com.thepiguy.buttersb.ButterSB
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager

class RegisterCommands {
    fun registerCommands() {
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("buttersb").executes {
                ButterSB.butterConfigScreen = ButterSB.buttercfg.gui()
                1
            }
        )
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("buttergui").executes {
                ButterSB.dragableConfigFlag = true
                1
            }
        )
    }
}