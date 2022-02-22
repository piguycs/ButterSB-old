package com.thepiguy.learnkt

import com.mojang.brigadier.context.CommandContext
import com.thepiguy.learnkt.utils.InterfaceInGameHudMixin
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText


@Suppress("UNUSED")
class LearnKotlin: ModInitializer {
    private val MOD_ID = "learnkt"

    override fun onInitialize() {
        println("Learning Kotlin on FULL GEAR!!")
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("hello").executes { context: CommandContext<FabricClientCommandSource> ->
                context.source.sendFeedback(LiteralText((MinecraftClient.getInstance().inGameHud as InterfaceInGameHudMixin).returnOverlayMessage().string))
                1
            }
        )
    }


    companion object {
        // does things per tick
        fun onTick() {

        }

        lateinit var matr: MatrixStack
        // run on world load
        fun onWorldLoad() {
            matr.push()
            matr.translate(2.0, 2.0, 2.0)
            matr.scale(1F, 1F, 0F);
            MinecraftClient.getInstance().inGameHud.chatHud.addMessage(LiteralText("Hello World!!"))
            MinecraftClient.getInstance().inGameHud.render(matr, 1F)

        }
    }
}