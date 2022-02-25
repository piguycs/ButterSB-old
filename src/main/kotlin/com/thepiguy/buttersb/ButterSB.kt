package com.thepiguy.buttersb

import com.mojang.blaze3d.systems.RenderSystem
import com.thepiguy.buttersb.config.ButterConfig
import com.thepiguy.buttersb.utils.InterfaceInGameHudMixin
import com.thepiguy.buttersb.utils.ParseActionBar
import gg.essential.universal.UMinecraft
import gg.essential.vigilance.Vigilance
import gg.essential.vigilance.gui.SettingsGui
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier


@Suppress("UNUSED")
class ButterSB: ModInitializer {
    private val mcinstance = MinecraftClient.getInstance()

    private var configGUI: SettingsGui? = null

    override fun onInitialize() {
        // Init log to the console
        println("Learning Kotlin on FULL GEAR!!")

        // vigilance shit for config
        Vigilance.initialize()
        ButterConfig.preload()


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
        configGUI = ButterConfig.gui()
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
        // for rendering the bar
        private var barHealth: Float? = null
        private var barMana:Float? = null
        // and texture for the bar
        private val barTex = Identifier("buttersb", "bars.png")


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
            // coords for ui
            val height = (mcinstance.inGameHud as InterfaceInGameHudMixin).returnScaledHeight()
            val width = (mcinstance.inGameHud as InterfaceInGameHudMixin).returnScaledWidth()

            // modified coords for rendering the bars aboce hotbar
            val barXCoord = width / 2 - 91
            val barYCord = height - 37


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

                barHealth = if (health != null && maxHealth != null) {
                    ((health!!.toFloat()/ maxHealth!!.toFloat())*75.0f)
                } else {
                    0f
                }

                barMana = if (mana != null && maxMana != null) {
                    ((mana!!.toFloat()/ maxMana!!.toFloat())*75.0f)
                } else {
                    0f
                }


                RenderSystem.setShaderTexture(0, barTex)
                // health bar
                mcinstance.inGameHud.drawTexture(matrices, barXCoord, barYCord, 0, 9, 75, 7)
                mcinstance.inGameHud.drawTexture(matrices, barXCoord, barYCord, 0, 16, barHealth!!.toInt(), 5)

                // mana bar
                mcinstance.inGameHud.drawTexture(matrices, barXCoord+106, barYCord, 0, 9, 75, 7)
                mcinstance.inGameHud.drawTexture(matrices, barXCoord+106, barYCord, 0, 23, barMana!!.toInt(), 5)

                // health
                val healthTxtRender = "$health/$maxHealth"
                mcinstance.textRenderer.drawWithShadow(matrices, healthTxtRender, barXCoord+(healthTxtRender.length*6/2F), barYCord-8.toFloat(), 16733525)

                // mana
                val manaTxtRender = "$mana/$maxMana"
                mcinstance.textRenderer.drawWithShadow(matrices, manaTxtRender, barXCoord+106F+(manaTxtRender.length*6/2F), barYCord-8.toFloat(), 5636095)


            }
        }
    }
}
