package com.thepiguy.learnkt.mixins;

import com.thepiguy.learnkt.utils.InterfaceInGameHudMixin;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InGameHud.class)
public class OverlayMessageGetter implements InterfaceInGameHudMixin {
    @Shadow @Nullable private Text overlayMessage;


    @Override
    public Text returnOverlayMessage() {
        return overlayMessage;
    }

}