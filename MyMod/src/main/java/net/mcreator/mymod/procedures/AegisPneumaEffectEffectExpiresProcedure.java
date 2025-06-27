package net.mcreator.mymod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.mymod.network.MymodModVariables;

public class AegisPneumaEffectEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = true;
			entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.aegisCooldownFlag = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
