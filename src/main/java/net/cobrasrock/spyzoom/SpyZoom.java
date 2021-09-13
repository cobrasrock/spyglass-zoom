package net.cobrasrock.spyzoom;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class SpyZoom implements ModInitializer {
	public static float zoom = 0.1f;

	public static MinecraftClient instance = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {}
}
