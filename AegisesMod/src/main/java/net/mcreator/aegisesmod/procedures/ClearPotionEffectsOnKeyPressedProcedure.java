package net.mcreator.aegisesmod.procedures;

import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;

import java.util.List;

public class ClearPotionEffectsOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (!(entity instanceof ServerPlayer player)) return;

		Advancement advancement = player.server.getAdvancements().getAdvancement(
				new ResourceLocation("aegisesmod", "clear_effects_unlock")
		);
		if (advancement == null) return;

		AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
		if (!progress.isDone()) return;

		List<MobEffect> toClear = player.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AegisesModVariables.PlayerVariables()).mobEffectListToClear;

		for (MobEffect effect : toClear) {
			player.removeEffect(effect);
		}
	}
}