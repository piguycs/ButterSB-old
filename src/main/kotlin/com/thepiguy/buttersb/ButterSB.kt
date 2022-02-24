package com.thepiguy.buttersb

import com.thepiguy.buttersb.config.ExampleConfig
import com.thepiguy.buttersb.utils.ParseActionBar
import com.thepiguy.buttersb.utils.InterfaceInGameHudMixin
import gg.essential.universal.UMinecraft
import gg.essential.vigilance.Vigilance
import gg.essential.vigilance.gui.SettingsGui
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text


@Suppress("UNUSED")
class ButterSB: ModInitializer {
    private val mcinstance = MinecraftClient.getInstance()

    private var configGUI: SettingsGui? = null

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
        private var health: String? = null
        private var maxHealth: String? = null
        private var mana: String? = null
        private var maxMana: String? = null


        // does things per tick
        fun onTick() {
            if (configGUI != null) {
                UMinecraft.getMinecraft().setScreen(configGUI)
            }
            configGUI = null


        }

        // run on world load
        fun onWorldLoad() {
            //mcinstance.inGameHud.chatHud.addMessage(LiteralText("Thank You for using Butter SB\nIt is Skyblock but better with butter performance"))
        }

        // render thing, renders stuff each tick
        fun onRender(matrices: MatrixStack, overlayMessage: Text?) {
            if (overlayMessage != null) {
                // renders the actionbar text (with stats and all)
                val overlayStuff = ParseActionBar().statsParser(overlayMessage.string)

                val playerHealth = mcinstance.player?.health
                val playerMaxHealth = mcinstance.player?.maxHealth

                if (overlayStuff != null) {
                    health = overlayStuff[0]
                    maxHealth = overlayStuff[1]
                    mana = overlayStuff[2]
                    maxMana = overlayStuff[3]
                } else {
                    if (playerHealth != null && playerMaxHealth != null) {
                        health = ((playerHealth / playerMaxHealth) * (maxHealth?.toInt() ?: 0)).toInt().toString()
                    }
                }

                mcinstance.textRenderer.draw(matrices, "Health: $health/$maxHealth", 2F, 2F, 16733525)
                mcinstance.textRenderer.draw(matrices, "Mana: $mana/$maxMana", 2F, 12F, 5636095)
            }
        }
    }
}
