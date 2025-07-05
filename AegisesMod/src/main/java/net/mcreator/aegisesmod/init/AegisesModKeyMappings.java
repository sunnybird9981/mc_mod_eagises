
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.aegisesmod.init;

import net.mcreator.aegisesmod.network.*;
import net.mcreator.aegisesmod.network.TimeStopKeyMessage;
import net.mcreator.aegisesmod.procedures.TimeStopHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.aegisesmod.AegisesMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mcreator.aegisesmod.procedures.TimeStopHandler;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class AegisesModKeyMappings {
	public static final KeyMapping SHOW_EFFECT_LIST_MENU_KEY = new KeyMapping("key.aegisesmod.show_effect_list_menu_key", GLFW.GLFW_KEY_UNKNOWN, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				AegisesMod.PACKET_HANDLER.sendToServer(new ShowEffectListMenuKeyMessage(0, 0));
				ShowEffectListMenuKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	public static final KeyMapping TOGGLE_TIME_STOP = new KeyMapping("key.aegisesmod.toggle_time_stop", GLFW.GLFW_KEY_Z, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				AegisesMod.PACKET_HANDLER.sendToServer(new TimeStopKeyMessage());
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(SHOW_EFFECT_LIST_MENU_KEY);
		event.register(TOGGLE_TIME_STOP);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				SHOW_EFFECT_LIST_MENU_KEY.consumeClick();
			}
		}
	}
}
