package com.thepiguy.buttersb

import com.mojang.blaze3d.systems.RenderSystem
import com.thepiguy.buttersb.commands.RegisterCommands
import com.thepiguy.buttersb.config.ButterConfig
import com.thepiguy.buttersb.utils.InterfaceInGameHudMixin
import com.thepiguy.buttersb.utils.ParseActionBar
import com.thepiguy.buttersb.utils.RenderHud
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier


@Suppress("UNUSED")
class ButterSB : ModInitializer {

    override fun onInitialize() {
        // Init log to the console
        println("Learning Kotlin on FULL GEAR!!")

        // vigilance shit for config
        config.preload()

        // command for doing shit lol
        RegisterCommands().registerCommands()

    }

    // events go here lol
    companion object {
        private val mc = MinecraftClient.getInstance()
        val config: ButterConfig = ButterConfig()


        // does things per tick
        fun onTick() {
            // uh oh
        }

        // run on world load
        fun onWorldLoad() {
            //UChat.chat("Thank You for using Butter SB")

        }

        // render thing, renders stuff each tick
        private val renderhud = RenderHud()
        fun onRender(matrices: MatrixStack, overlayMessage: Text?) {
            renderhud.renderall(matrices, overlayMessage)
        }
    }
}
