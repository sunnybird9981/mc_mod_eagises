package net.mcreator.mymod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.mymod.network.MymodModVariables;

public class EffectListMenuUpArrowProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).effectListMenuIndex > 0) {
			double _setval = (entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).effectListMenuIndex - 1;
			entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.effectListMenuIndex = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
