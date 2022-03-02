package com.thepiguy.buttersb.mixins;

import com.thepiguy.buttersb.ButterSB;
import com.thepiguy.buttersb.config.ButterConfig;
import com.thepiguy.buttersb.utils.InterfaceInGameHudMixin;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InGameHud.class)
public class InGameHudMixin implements InterfaceInGameHudMixin {
    @Shadow @Nullable private Text overlayMessage;


    @Shadow private int scaledHeight;

    @Shadow private int scaledWidth;

    @Override
    public Text returnOverlayMessage() {
        return overlayMessage;
    }

    @Override
    public int returnScaledHeight() {
        return scaledHeight;
    }

    @Override
    public int returnScaledWidth() {
        return scaledWidth;
    }

    @Inject(method="render", at=@At("HEAD"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        ButterSB.Companion.onRender(matrices, overlayMessage);
    }

    // hides the default hud for actionbar
    @ModifyArg(method="render", at=@At(value="INVOKE", target="Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), index=1)
    private Text injected(Text x) {
        return new LiteralText("");
    }

    // hides the vanilla hearts
    @ModifyArg(method="renderHealthBar", at=@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;ceil(D)I"), index=0)
    private double injected(double value) {
        if (ButterConfig.Companion.getHideVanillaHealth()) {
            return 0;
        } else {
            return value;
        }
    }

    // hides the vanilla hunger
    @ModifyArgs(method="renderStatusBars", at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
    private void injected(Args args) {
        if (ButterConfig.Companion.getHideVanillaHunger()) {
            args.set(5, 0);
        }
    }
}