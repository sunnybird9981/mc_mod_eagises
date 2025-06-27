
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.aegisesmod.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.aegisesmod.network.TogglePurifyingFlamesStatMessage;
import net.mcreator.aegisesmod.network.ToggleForesightStatMessage;
import net.mcreator.aegisesmod.network.ToggleEasisesStatMessage;
import net.mcreator.aegisesmod.network.ShowEffectListMenuKeyMessage;
import net.mcreator.aegisesmod.network.ClearPotionEffectsMessage;
import net.mcreator.aegisesmod.AegisesMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class AegisesModKeyMappings {
	public static final KeyMapping TOGGLE_FORESIGHT_STAT = new KeyMapping("key.aegisesmod.toggle_foresight_stat", GLFW.GLFW_KEY_9, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				AegisesMod.PACKET_HANDLER.sendToServer(new ToggleForesightStatMessage(0, 0));
				ToggleForesightStatMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping CLEAR_POTION_EFFECTS = new KeyMapping("key.aegisesmod.clear_potion_effects", GLFW.GLFW_KEY_0, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				AegisesMod.PACKET_HANDLER.sendToServer(new ClearPotionEffectsMessage(0, 0));
				ClearPotionEffectsMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TOGGLE_PURIFYING_FLAMES_STAT = new KeyMapping("key.aegisesmod.toggle_purifying_flames_stat", GLFW.GLFW_KEY_8, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				AegisesMod.PACKET_HANDLER.sendToServer(new TogglePurifyingFlamesStatMessage(0, 0));
				TogglePurifyingFlamesStatMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TOGGLE_EASISES_STAT = new KeyMapping("key.aegisesmod.toggle_easises_stat", GLFW.GLFW_KEY_7, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				AegisesMod.PACKET_HANDLER.sendToServer(new ToggleEasisesStatMessage(0, 0));
				ToggleEasisesStatMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
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

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(TOGGLE_FORESIGHT_STAT);
		event.register(CLEAR_POTION_EFFECTS);
		event.register(TOGGLE_PURIFYING_FLAMES_STAT);
		event.register(TOGGLE_EASISES_STAT);
		event.register(SHOW_EFFECT_LIST_MENU_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				TOGGLE_FORESIGHT_STAT.consumeClick();
				CLEAR_POTION_EFFECTS.consumeClick();
				TOGGLE_PURIFYING_FLAMES_STAT.consumeClick();
				TOGGLE_EASISES_STAT.consumeClick();
				SHOW_EFFECT_LIST_MENU_KEY.consumeClick();
			}
		}
	}
}
