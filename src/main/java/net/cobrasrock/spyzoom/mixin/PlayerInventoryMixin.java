package net.cobrasrock.spyzoom.mixin;

import net.cobrasrock.spyzoom.SpyZoom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
	@Inject(at = @At("HEAD"), method = "scrollInHotbar", cancellable = true)
	private void scrollInHotbar(double scrollAmount, CallbackInfo info) {
		if(MinecraftClient.getInstance().player != null) {
			if (MinecraftClient.getInstance().player.isUsingSpyglass()) {
				//zooms in/out
				if (scrollAmount > 0.0D) {
					SpyZoom.zoom *= 9.0 /10;
				}
				if (scrollAmount < 0.0D) {
					SpyZoom.zoom *= 10.0 /9;
				}
				//prevents zooming too far
				if(SpyZoom.zoom < 0.001){
					SpyZoom.zoom = 0.001f;
				}
				if(SpyZoom.zoom > 1){
					SpyZoom.zoom = 1f;
				}

				info.cancel();
			}
		}
	}
}
