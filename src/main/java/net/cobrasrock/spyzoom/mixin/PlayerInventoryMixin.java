package net.cobrasrock.spyzoom.mixin;

import net.cobrasrock.spyzoom.SpyZoom;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.MathHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
	@Inject(at = @At("HEAD"), method = "scrollInHotbar", cancellable = true)
	private void scrollInHotbar(double scrollAmount, CallbackInfo info) {
		if(SpyZoom.instance.player == null) return;
		if(SpyZoom.instance.player.isUsingSpyglass() && SpyZoom.instance.options.getPerspective().isFirstPerson()) {
			//zooms in/out
			if (scrollAmount > 0.0D && SpyZoom.zoom > 0.001f) {
				SpyZoom.zoom *= 9.0f / 10.0f;
			}
			if (scrollAmount < 0.0D && SpyZoom.zoom < 1.0f) {
				SpyZoom.zoom *= 10.0f / 9f;
			}
			//prevents zooming from going OOB
			SpyZoom.zoom = MathHelper.clamp(SpyZoom.zoom, 0.001f, 1.0f);

			info.cancel();
		}
	}
}
