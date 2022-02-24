package com.thepiguy.buttersb.mixins;

import com.thepiguy.buttersb.ButterSB;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClassMixin {

    @Inject(method="tick", at=@At("HEAD"))
    public void tick(CallbackInfo ci) {
        ButterSB.Companion.onTick();
    }

    @Inject(method="joinWorld", at=@At("HEAD"))
    public void joinWorld(CallbackInfo ci) {
        ButterSB.Companion.onWorldLoad();
    }
}