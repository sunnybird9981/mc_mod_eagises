package net.mcreator.mymod.procedures;

import net.mcreator.mymod.network.MymodModVariables;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.mymod.init.MymodModMobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ClearPotionEffectsOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MymodModMobEffects.PURIFYING_FLAMES.get())) {
			List<MobEffect> _mobEffectListToClear = entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables()).mobEffectListToClear;

			for (MobEffect e : _mobEffectListToClear) {
				((Player) entity).removeEffect(e);
			}
		}
	}
}
