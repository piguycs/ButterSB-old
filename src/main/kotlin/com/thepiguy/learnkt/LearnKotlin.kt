package com.thepiguy.learnkt

import com.mojang.brigadier.context.CommandContext
import com.thepiguy.learnkt.utils.InterfaceInGameHudMixin
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText


@Suppress("UNUSED")
class LearnKotlin: ModInitializer {
    private val MOD_ID = "learnkt"

    override fun onInitialize() {
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("hello").executes { context: CommandContext<FabricClientCommandSource> ->
                context.source.sendFeedback(LiteralText((MinecraftClient.getInstance().inGameHud as InterfaceInGameHudMixin).returnOverlayMessage().string))
                1
            }
        )
    }

    companion object {
        fun onTick() {

            println("HELLO")
        }
    }
}