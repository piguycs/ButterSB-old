package com.thepiguy.buttersb.config


import gg.essential.universal.UChat
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

class ButterConfig : Vigilant(File("./config/butter.toml")) {
    companion object {
        @Property(
            type = PropertyType.SWITCH,
            name = "Hide vanilla health bar",
            description = "Hide the vanilla hearts which show health",
            category = "GUI"
        )
        var hideVanillaHealth = true

        @Property(
            type = PropertyType.SWITCH,
            name = "Hide vanilla hunger bar",
            description = "Hide the vanilla hunger bar as it is not needed in skyblock",
            category = "GUI"
        )
        var hideVanillaHunger = true

        @Property(
            type = PropertyType.BUTTON,
            name = "CONFIG UI",
            description = "Config the ui",
            category = "GUI"
        )
        fun openHudConfig() {
            UChat.chat("WIP!!")
        }

        @Property(
            type = PropertyType.NUMBER,
            name = "Chat Up height",
            description = "Move the chat up by the set number of pixels, this is so that it wont obstruct the hud",
            category = "GUI",
            min = 0,
            max = 1000
        )
        var chatUpPixels = 10
    }

    init {
        initialize()

        setCategoryDescription(
            "GUI",
            "Change stuff in the GUI"
        )
    }
}