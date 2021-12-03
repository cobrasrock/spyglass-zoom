package net.cobrasrock.spyzoom.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.cobrasrock.spyzoom.SpyZoom;
import net.minecraft.item.SpyglassItem;

@Mixin(SpyglassItem.class)
public class SpyglassItemMixin {
  @Inject(method = "onStoppedUsing", at = @At("HEAD"))
  private void resetZoom(CallbackInfo ci) {
    SpyZoom.zoom = 0.1f;
  }
}
