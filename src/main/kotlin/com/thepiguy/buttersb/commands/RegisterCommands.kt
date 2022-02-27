package com.thepiguy.buttersb.commands

import com.thepiguy.buttersb.ButterSB
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.MinecraftClient

class RegisterCommands {
    fun registerCommands() {
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("buttersb").executes {
                MinecraftClient.getInstance().setScreen(ButterSB.config.gui())
                1
            }
        )
    }
}