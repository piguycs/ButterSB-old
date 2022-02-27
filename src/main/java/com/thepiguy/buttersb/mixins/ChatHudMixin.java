package com.thepiguy.buttersb.mixins;

import com.thepiguy.buttersb.config.ButterConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    private int getOffset() {
        return ButterConfig.INSTANCE.getChatUpPixels();
    }

    @ModifyArg(method = "render", index = 1, at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V",
            ordinal = 0
    ))
    private double offsetY(double y) {
        return y - getOffset();
    }

    @ModifyConstant(method = "getText", constant = @Constant(doubleValue = 40.0))
    private double textBottomOffset(double original) {
        return original + getOffset();
    }
}
