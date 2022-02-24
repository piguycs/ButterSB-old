package com.thepiguy.buttersb.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.minecraft.client.gui.screen.Screen

class ModMenuConfig:ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*>? {
        return ConfigScreenFactory<Screen> { parent: Screen? ->
            val configGUI = ExampleConfig.gui()
            configGUI
        }

    }
}