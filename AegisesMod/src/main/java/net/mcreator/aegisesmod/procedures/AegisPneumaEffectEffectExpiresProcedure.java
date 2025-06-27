package net.mcreator.aegisesmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.aegisesmod.network.AegisesModVariables;

public class AegisPneumaEffectEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = true;
			entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.aegisCooldownFlag = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
