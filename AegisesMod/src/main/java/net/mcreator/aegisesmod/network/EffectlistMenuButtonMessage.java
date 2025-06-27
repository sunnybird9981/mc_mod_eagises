
package net.mcreator.aegisesmod.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.aegisesmod.world.inventory.EffectlistMenuMenu;
import net.mcreator.aegisesmod.procedures.EffectListMenuUpArrowProcedure;
import net.mcreator.aegisesmod.procedures.EffectListMenuDownArrowProcedure;
import net.mcreator.aegisesmod.procedures.EffectListMenuButton5Procedure;
import net.mcreator.aegisesmod.procedures.EffectListMenuButton4Procedure;
import net.mcreator.aegisesmod.procedures.EffectListMenuButton3Procedure;
import net.mcreator.aegisesmod.procedures.EffectListMenuButton2Procedure;
import net.mcreator.aegisesmod.procedures.EffectListMenuButton1Procedure;
import net.mcreator.aegisesmod.AegisesMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EffectlistMenuButtonMessage {
	private final int buttonID, x, y, z;

	public EffectlistMenuButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public EffectlistMenuButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(EffectlistMenuButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(EffectlistMenuButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = EffectlistMenuMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 1) {

			EffectListMenuButton1Procedure.execute(entity);
		}
		if (buttonID == 2) {

			EffectListMenuButton2Procedure.execute(entity);
		}
		if (buttonID == 3) {

			EffectListMenuButton3Procedure.execute(entity);
		}
		if (buttonID == 4) {

			EffectListMenuButton4Procedure.execute(entity);
		}
		if (buttonID == 5) {

			EffectListMenuButton5Procedure.execute(entity);
		}
		if (buttonID == 6) {

			EffectListMenuUpArrowProcedure.execute(entity);
		}
		if (buttonID == 7) {

			EffectListMenuDownArrowProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AegisesMod.addNetworkMessage(EffectlistMenuButtonMessage.class, EffectlistMenuButtonMessage::buffer, EffectlistMenuButtonMessage::new, EffectlistMenuButtonMessage::handler);
	}
}
