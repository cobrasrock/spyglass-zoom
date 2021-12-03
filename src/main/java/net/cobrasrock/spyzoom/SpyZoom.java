package net.cobrasrock.spyzoom;

import net.minecraft.client.MinecraftClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("spyzoom")
public class SpyZoom {

	public static float zoom = 0.1f;
	public static MinecraftClient instance = MinecraftClient.getInstance();

	public SpyZoom() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
