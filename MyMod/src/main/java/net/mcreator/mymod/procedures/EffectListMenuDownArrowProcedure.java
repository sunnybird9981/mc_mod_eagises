package net.mcreator.mymod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.mymod.network.MymodModVariables;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectListMenuDownArrowProcedure {
	public static void execute(Entity entity) {
		int effectsNum = ForgeRegistries.MOB_EFFECTS.getValues().size();
		int effectListIndex = (int) (entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).effectListMenuIndex;
		if (effectListIndex + 5 < effectsNum) {
			double _setval = effectListIndex + 1;
			entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.effectListMenuIndex = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
