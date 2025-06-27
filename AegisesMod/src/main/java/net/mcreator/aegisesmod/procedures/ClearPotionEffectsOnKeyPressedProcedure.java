package net.mcreator.aegisesmod.procedures;

import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.aegisesmod.init.AegisesModMobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ClearPotionEffectsOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(AegisesModMobEffects.PURIFYING_FLAMES.get())) {
			List<MobEffect> _mobEffectListToClear = entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables()).mobEffectListToClear;

			for (MobEffect e : _mobEffectListToClear) {
				((Player) entity).removeEffect(e);
			}
		}
	}
}
