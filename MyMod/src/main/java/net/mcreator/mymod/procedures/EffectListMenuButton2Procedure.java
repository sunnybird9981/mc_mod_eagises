package net.mcreator.mymod.procedures;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;

import net.mcreator.mymod.network.MymodModVariables;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class EffectListMenuButton2Procedure {
	public static void execute(Entity entity) {
		MymodModVariables.PlayerVariables playerVariables = entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables());
		int _effectListMenuIndex = (int) playerVariables.effectListMenuIndex;
		List<MobEffect> _mobEffectListToClear = playerVariables.mobEffectListToClear;
		List<MobEffect> _mobEffectListAll = new ArrayList<MobEffect>();

		for (MobEffect e : ForgeRegistries.MOB_EFFECTS) {
			_mobEffectListAll.add(e);
		}
		if (_mobEffectListToClear.contains(_mobEffectListAll.get(1 + _effectListMenuIndex))) {
			_mobEffectListToClear.remove(_mobEffectListAll.get(1 + _effectListMenuIndex));
		} else {
			_mobEffectListToClear.add(_mobEffectListAll.get(1 + _effectListMenuIndex));
		}
		System.out.println("EffectListMenuButton1Procedure : " + _mobEffectListToClear);
		entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
			capability.mobEffectListToClear = _mobEffectListToClear;
			capability.syncPlayerVariables(entity);
		});
	}
}
