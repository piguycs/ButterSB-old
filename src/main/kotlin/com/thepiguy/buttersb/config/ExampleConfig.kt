package com.thepiguy.buttersb.config


import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.*
import java.io.File

object ExampleConfig : Vigilant(File("./config/butter.toml")) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Hide vanilla healt bar",
        description = "Hide the vanilla hearts which show health",
        category = "GUI"
    )
    var hideVanillaHealth = false

    init {
        initialize()

        setCategoryDescription(
            "GUI",
            "Change stuff in the GUI"
        )

    }
}