package net.cobrasrock.spyzoom.mixin;

import net.cobrasrock.spyzoom.SpyZoom;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {
    //lowers mouse sensitivity
    @Inject(at = @At("HEAD"), method = "updateMouse", cancellable = true)
    private void updateMouse(CallbackInfo info) {
        ClientPlayerEntity player = SpyZoom.instance.player;
        GameOptions options = SpyZoom.instance.options;

        if(player == null) return;

        if (player.isUsingSpyglass() && options.getPerspective().isFirstPerson()) {

            double f = options.mouseSensitivity * 0.6000000238418579D + 0.20000000298023224D;
            double g = f * f * f;

            //janky calculation that probably isn't right but works
            double zoom = ((-20.0/9 * (SpyZoom.zoom * SpyZoom.zoom)) + (92.0/9 * SpyZoom.zoom));

            g *= zoom;

            double o = getCursorDeltaX() * g;
            double p = getCursorDeltaY() * g;

            setCursorDeltaX(0.0D);
            setCursorDeltaY(0.0D);

            int q = 1;
            if (options.invertYMouse) {
                q = -1;
            }

            SpyZoom.instance.getTutorialManager().onUpdateMouse(o, p);

            player.changeLookDirection(o, p * (double)q);

            info.cancel();
        }
    }

    @Accessor(value = "cursorDeltaX")
    public abstract double getCursorDeltaX();

    @Accessor(value = "cursorDeltaY")
    public abstract double getCursorDeltaY();

    @Accessor(value = "cursorDeltaX")
    public abstract void setCursorDeltaX(double x);

    @Accessor(value = "cursorDeltaY")
    public abstract void setCursorDeltaY(double y);
}
