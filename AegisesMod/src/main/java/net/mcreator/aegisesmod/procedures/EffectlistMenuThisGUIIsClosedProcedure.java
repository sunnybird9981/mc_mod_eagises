package net.mcreator.aegisesmod.procedures;

import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class EffectlistMenuThisGUIIsClosedProcedure {
	public static void execute(Entity entity) {
		List<MobEffect> _mobEffectListToClear = new ArrayList<MobEffect>();
		_mobEffectListToClear = (entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables())).mobEffectListToClear;
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			_player.displayClientMessage(Component.literal("gui closed"), false);
			/*
			for (MobEffect e : _mobEffectListToClear) {
				_player.displayClientMessage(Component.literal(e.getDisplayName().getString()), false);
			}

			 */
		}
	}
}
