package net.mcreator.mymod.procedures;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.mymod.world.inventory.EffectlistMenuMenu;

import io.netty.buffer.Unpooled;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ShowEffectListMenuProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer _ent) {
			List<MobEffect> _mobEffectList = new ArrayList<MobEffect>();
			for (MobEffect effect : ForgeRegistries.MOB_EFFECTS) {
				_mobEffectList.add(effect);
				if (entity instanceof Player _player && !entity.level().isClientSide()) {
					//_player.displayClientMessage(Component.literal(effect.getDisplayName().getString()), false);
				}
			}

			Player _player = (Player) entity;
			for (int i = 0; i < _mobEffectList.size(); i++) {
				//_player.displayClientMessage(Component.literal(_mobEffectList.get(i).getDisplayName().getString() + " index : " + i), false);
			}



			BlockPos _bpos = BlockPos.containing(x, y, z);
			NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("EffectlistMenu");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new EffectlistMenuMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}
