
package net.mcreator.mymod.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class PurifyingFlamesMobEffect extends MobEffect {
	public PurifyingFlamesMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -26215);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
