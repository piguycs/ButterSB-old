package com.thepiguy.buttersb.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.thepiguy.buttersb.ButterSB
import net.minecraft.client.gui.screen.Screen

class ModMenuConfig: ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*>? {
        return ConfigScreenFactory<Screen> {
            val configGUI = ButterSB.config.gui()
            configGUI
        }
    }
}