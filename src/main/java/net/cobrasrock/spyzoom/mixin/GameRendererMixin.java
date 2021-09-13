package net.cobrasrock.spyzoom.mixin;

import net.cobrasrock.spyzoom.SpyZoom;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    //zooms in screen
    @Inject(at = @At("HEAD"), method = "updateMovementFovMultiplier", cancellable = true)
    private void updateMovementFovMultiplier(CallbackInfo info) {
        if(SpyZoom.instance.player != null) {
            if (SpyZoom.instance.player.isUsingSpyglass() && SpyZoom.instance.options.getPerspective().isFirstPerson()) {

                setLastMovementFovMultiplier(getFovMultiplier());
                setFovMultiplier(getFovMultiplier() + (SpyZoom.zoom - getFovMultiplier()) * 0.5F);

                info.cancel();
            }
        }
    }

    @Accessor(value = "movementFovMultiplier")
    public abstract void setFovMultiplier(float fov);

    @Accessor(value = "movementFovMultiplier")
    public abstract float getFovMultiplier();

    @Accessor(value = "lastMovementFovMultiplier")
    public abstract void setLastMovementFovMultiplier(float fov);
}
