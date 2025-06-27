
package net.mcreator.mymod.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.mymod.procedures.ForesightEffectOnEffectActiveTickProcedure;

public class ForesightEffectMobEffect extends MobEffect {
	public ForesightEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -154);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		ForesightEffectOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
