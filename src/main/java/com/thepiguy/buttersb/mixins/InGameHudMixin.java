package com.thepiguy.buttersb.mixins;

import com.thepiguy.buttersb.ButterSB;
import com.thepiguy.buttersb.config.ExampleConfig;
import com.thepiguy.buttersb.utils.InterfaceInGameHudMixin;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin implements InterfaceInGameHudMixin {
    @Shadow @Nullable private Text overlayMessage;


    @Override
    public Text returnOverlayMessage() {
        return overlayMessage;
    }

    @Inject(method="render", at=@At("HEAD"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        ButterSB.Companion.onRender(matrices, overlayMessage);
    }

    // hides the default hud
    @ModifyArg(method="render", at=@At(value="INVOKE", target="Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), index=1)
    private Text injected(Text x) {
        return new LiteralText("");
    }

    @ModifyArg(method="renderHealthBar", at=@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;ceil(D)I"), index=0)
    private double injected(double value) {
        boolean hideVanillaHealth = ExampleConfig.INSTANCE.getHideVanillaHealth();
        if (hideVanillaHealth) {
            return 0;
        } else {
            return value;
        }
    }

}