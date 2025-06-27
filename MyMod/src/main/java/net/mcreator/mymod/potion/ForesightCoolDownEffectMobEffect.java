
package net.mcreator.mymod.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class ForesightCoolDownEffectMobEffect extends MobEffect {
	public ForesightCoolDownEffectMobEffect() {
		super(MobEffectCategory.HARMFUL, -39322);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
