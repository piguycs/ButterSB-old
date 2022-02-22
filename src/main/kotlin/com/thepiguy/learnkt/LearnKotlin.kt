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
    private val MODID = "learnkt"

    lateinit var status:String

    val hudinstance = MinecraftClient.getInstance().inGameHud

    override fun onInitialize() {
        println("Learning Kotlin on FULL GEAR!!")
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("hello").executes { context: CommandContext<FabricClientCommandSource> ->
                status = (hudinstance as InterfaceInGameHudMixin).returnOverlayMessage().string
                context.source.sendFeedback(LiteralText(status))
                MinecraftClient.getInstance().inGameHud.chatHud.addMessage(LiteralText("stuff is " + status))
                1
            }
        )
    }

    // events go here lol
    companion object {
        // does things per tick
        fun onTick() {

        }

        // run on world load
        fun onWorldLoad() {
            MinecraftClient.getInstance().inGameHud.chatHud.addMessage(LiteralText("Hello World!!"))

        }
    }
}