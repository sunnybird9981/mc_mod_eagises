package net.mcreator.mymod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.mymod.network.MymodModVariables;
import net.mcreator.mymod.init.MymodModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EffectsStartProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("AegisEffectStartTimer") > 0) {
			entity.getPersistentData().putDouble("AegisEffectStartTimer", (entity.getPersistentData().getDouble("AegisEffectStartTimer") - 1));
		} else {
			if ((entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).foresightStat == true) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MymodModMobEffects.FORESIGHT_EFFECT.get(), 30, 0));
			}
			if ((entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).purifyingFlamesStat == true) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MymodModMobEffects.PURIFYING_FLAMES.get(), 30, 0));
			}
			if ((entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).aegisCooldownFlag == true) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MymodModMobEffects.AEGIS_COOLDOWN_EFFECT.get(), 1200, 0));
				{
					boolean _setval = false;
					entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.aegisCooldownFlag = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			entity.getPersistentData().putDouble("AegisEffectStartTimer", 20);
		}
	}
}
