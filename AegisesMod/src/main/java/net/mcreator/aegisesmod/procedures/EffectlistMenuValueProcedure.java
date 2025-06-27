package net.mcreator.aegisesmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.aegisesmod.network.AegisesModVariables;

public class EffectlistMenuValueProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + (entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables())).effectListMenuIndex;
	}
}
