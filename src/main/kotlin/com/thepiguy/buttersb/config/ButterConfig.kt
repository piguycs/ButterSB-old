package com.thepiguy.buttersb.config


import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.*
import java.io.File

object ButterConfig : Vigilant(File("./config/butter.toml")) {
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
    var configGUI = false

    init {
        initialize()

        setCategoryDescription(
            "GUI",
            "Change stuff in the GUI"
        )
    }
}