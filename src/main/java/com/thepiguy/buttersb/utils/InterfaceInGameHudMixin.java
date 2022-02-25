package com.thepiguy.buttersb.utils;

import net.minecraft.text.Text;

public interface InterfaceInGameHudMixin {
    Text returnOverlayMessage();

    int returnScaledHeight();

    int returnScaledWidth();
}
