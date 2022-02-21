package com.thepiguy.learnkt.mixins;

import com.thepiguy.learnkt.LearnKotlin;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClassMixin {

    @Inject(method="tick", at=@At("HEAD"))
    public void tick(CallbackInfo ci) {
        LearnKotlin.Companion.onTick();
    }
}
