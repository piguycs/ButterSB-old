package com.thepiguy.buttersb.utils

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.render.*
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text

class HudDragableConfig(title: Text?) : Screen(title) {
    override fun render(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {




        //textRenderer.drawWithShadow(matrices, "Sprinting", mouseX.toFloat(), mouseY.toFloat(), 5636095)
    }

    companion object {
        val mc = MinecraftClient.getInstance()
    }
}