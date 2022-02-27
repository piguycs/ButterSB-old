package com.thepiguy.buttersb.commands

import com.thepiguy.buttersb.ButterSB
import com.thepiguy.buttersb.config.ButterConfig
import gg.essential.vigilance.gui.SettingsGui
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager

class RegisterCommands {
    private var configGUI: SettingsGui? = null
    fun registerCommands() {
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("buttersb").executes {
                openConfig()
                1
            }
        )
    }

    private fun openConfig() {
        configGUI = ButterConfig.gui()
        ButterSB.configGUI = configGUI
    }
}