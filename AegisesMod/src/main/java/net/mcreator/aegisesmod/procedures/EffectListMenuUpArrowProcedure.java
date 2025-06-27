package net.mcreator.aegisesmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.aegisesmod.network.AegisesModVariables;

public class EffectListMenuUpArrowProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables())).effectListMenuIndex > 0) {
			double _setval = (entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables())).effectListMenuIndex - 1;
			entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.effectListMenuIndex = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
