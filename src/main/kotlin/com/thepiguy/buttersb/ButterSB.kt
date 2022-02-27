package com.thepiguy.buttersb

import com.mojang.blaze3d.systems.RenderSystem
import com.thepiguy.buttersb.commands.RegisterCommands
import com.thepiguy.buttersb.config.ButterConfig
import com.thepiguy.buttersb.utils.InterfaceInGameHudMixin
import com.thepiguy.buttersb.utils.ParseActionBar
import com.thepiguy.buttersb.utils.RenderHud
import gg.essential.universal.UChat
import gg.essential.universal.UMinecraft
import gg.essential.vigilance.Vigilance
import gg.essential.vigilance.gui.SettingsGui
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier


@Suppress("UNUSED")
class ButterSB: ModInitializer {
    private val mcinstance = MinecraftClient.getInstance()

    override fun onInitialize() {
        // Init log to the console
        println("Learning Kotlin on FULL GEAR!!")

        // vigilance shit for config
        Vigilance.initialize()
        ButterConfig.preload()

        // command for doing shit lol
        RegisterCommands().registerCommands()

    }

    // events go here lol
    companion object {
        private val mcinstance = MinecraftClient.getInstance()
        var configGUI : Screen? = null

        // does things per tick
        fun onTick() {
            if (configGUI != null) {
                UMinecraft.getMinecraft().setScreen(configGUI)
            }
            configGUI = null
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
