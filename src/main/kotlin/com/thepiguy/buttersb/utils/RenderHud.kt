package com.thepiguy.buttersb.utils

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class RenderHud {
    private val mc = MinecraftClient.getInstance()

    private var health: String? = null
    private var maxHealth: String? = null
    private var mana: String? = null
    private var maxMana: String? = null

    // for rendering the bar
    private var barHealth: Float? = null
    private var barMana: Float? = null

    // and texture for the bar
    private val barTex = Identifier("buttersb", "bars.png")
    fun renderall(matrices: MatrixStack, overlayMessage: Text?) {

        // coords for ui
        val height = (mc.inGameHud as InterfaceInGameHudMixin).returnScaledHeight()
        val width = (mc.inGameHud as InterfaceInGameHudMixin).returnScaledWidth()

        // modified coords for rendering the bars aboce hotbar
        val barXCoord = width / 2 - 91
        val barYCord = height - 37


        if (overlayMessage != null) {
            // renders the actionbar text (with stats and all)
            val overlayStuff = ParseActionBar().statsParser(overlayMessage.string)

            val playerHealth = mc.player?.health
            val playerMaxHealth = mc.player?.maxHealth

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
                ((health!!.toFloat() / maxHealth!!.toFloat()) * 75.0f)
            } else {
                0f
            }

            barMana = if (mana != null && maxMana != null) {
                ((mana!!.toFloat() / maxMana!!.toFloat()) * 75.0f)
            } else {
                0f
            }

            RenderSystem.setShaderTexture(0, barTex)
            // health bar
            mc.inGameHud.drawTexture(matrices, barXCoord, barYCord, 0, 9, 75, 7)
            mc.inGameHud.drawTexture(matrices, barXCoord, barYCord, 0, 16, barHealth!!.toInt(), 5)

            // mana bar
            mc.inGameHud.drawTexture(matrices, barXCoord + 106, barYCord, 0, 9, 75, 7)
            mc.inGameHud.drawTexture(matrices, barXCoord + 106, barYCord, 0, 23, barMana!!.toInt(), 5)

            // health
            val healthTxtRender = "$health/$maxHealth"
            val healthLength = if (healthTxtRender.length * 6 / 2F % 2 != 0F) {
                healthTxtRender.length * 6 / 2F - 6
            } else {
                healthTxtRender.length * 6 / 2F
            }
            mc.textRenderer.drawWithShadow(
                matrices,
                healthTxtRender,
                barXCoord + healthLength,
                barYCord - 8.toFloat(),
                16733525
            )

            // mana
            val manaTxtRender = "$mana/$maxMana"
            val manaLength = if (manaTxtRender.length * 6 / 2F % 2 != 0F) {
                manaTxtRender.length * 6 / 2F - 6
            } else {
                manaTxtRender.length * 6 / 2F
            }
            mc.textRenderer.drawWithShadow(
                matrices,
                manaTxtRender,
                barXCoord + 106F + manaLength,
                barYCord - 8.toFloat(),
                5636095
            )

        }

        if (mc.options.keySprint.isPressed && mc.options.sprintToggled) {
            mc.textRenderer.drawWithShadow(matrices, "Sprinting", 2F, 2F, 5636095)
        }
    }
}
