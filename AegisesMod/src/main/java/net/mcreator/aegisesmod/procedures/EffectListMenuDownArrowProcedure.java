package net.mcreator.aegisesmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectListMenuDownArrowProcedure {
	public static void execute(Entity entity) {
		int effectsNum = ForgeRegistries.MOB_EFFECTS.getValues().size();
		int effectListIndex = (int) (entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables())).effectListMenuIndex;
		if (effectListIndex + 5 < effectsNum) {
			double _setval = effectListIndex + 1;
			entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.effectListMenuIndex = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
