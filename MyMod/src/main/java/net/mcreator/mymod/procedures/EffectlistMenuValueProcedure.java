package net.mcreator.mymod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.mymod.network.MymodModVariables;

public class EffectlistMenuValueProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + (entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).effectListMenuIndex;
	}
}
