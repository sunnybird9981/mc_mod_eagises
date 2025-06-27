
package net.mcreator.aegisesmod.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class AegisCooldownEffectMobEffect extends MobEffect {
	public AegisCooldownEffectMobEffect() {
		super(MobEffectCategory.HARMFUL, -1);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
