package com.thepiguy.learnkt

import com.thepiguy.learnkt.config.ExampleConfig
import com.thepiguy.learnkt.utils.ParseActionBar
import com.thepiguy.learnkt.mixins.InGameHudMixin
import com.thepiguy.learnkt.utils.InterfaceInGameHudMixin
import gg.essential.universal.UMinecraft
import gg.essential.vigilance.Vigilance
import gg.essential.vigilance.gui.SettingsGui
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.text.Text


@Suppress("UNUSED")
class LearnKotlin: ModInitializer {
    private val MODID = "learnkt"
    private val mcinstance = MinecraftClient.getInstance()

    var configGUI: SettingsGui? = null

    override fun onInitialize() {
        // Init log to the console
        println("Learning Kotlin on FULL GEAR!!")

        // vigilance shit for config
        Vigilance.initialize()
        ExampleConfig.preload()


        // command for doing shit lol
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("buttersb").executes {
                openConfig()
                1
            }
        )
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("testregex").executes {
                ParseActionBar().statsParser((mcinstance.inGameHud as InterfaceInGameHudMixin).returnOverlayMessage().string)
                1
            }
        )

    }

    // IMPORTANT lol
    // §c120/120❤     §a10§a❈ Defense     §b100/100✎ Mana

    private fun openConfig() {
        configGUI = ExampleConfig.gui()
        Companion.configGUI = configGUI
    }

    // events go here lol
    companion object {
        private val mcinstance = MinecraftClient.getInstance()
        private var configGUI : Screen? = null

        // for rendering and all that shit
        var health: String? = null
        var maxHealth: String? = null
        var mana: String? = null
        var maxMana: String? = null


        // does things per tick
        fun onTick() {
            if (configGUI != null) {
                UMinecraft.getMinecraft().setScreen(configGUI)
            }
            configGUI = null


        }

        // run on world load
        fun onWorldLoad() {
            mcinstance.inGameHud.chatHud.addMessage(LiteralText("Hello World!!"))
        }

        // render thing, renders stuff each tick
        fun onRender(matrices: MatrixStack, tickDelta: Float, overlayMessage: Text?) {
            if (overlayMessage != null) {
                // renders the actionbar text (with stats and all)
                val overlayStuff = ParseActionBar().statsParser(overlayMessage.string)
                if (overlayStuff != null) {
                    health = overlayStuff[0]
                    maxHealth = overlayStuff[1]
                    mana = overlayStuff[2]
                    maxMana = overlayStuff[3]
                }

                mcinstance.textRenderer.draw(matrices, "Health: $health/$maxHealth", 2F, 2F, 16733525)
                mcinstance.textRenderer.draw(matrices, "Mana: $mana/$maxMana", 2F, 12F, 5636095)
            }
        }
    }
}
