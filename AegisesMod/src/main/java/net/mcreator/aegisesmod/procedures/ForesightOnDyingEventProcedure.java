package net.mcreator.aegisesmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.mcreator.aegisesmod.init.AegisesModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ForesightOnDyingEventProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			if ((entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AegisesModVariables.PlayerVariables())).eagisesStat == true
					&& !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(AegisesModMobEffects.AEGIS_COOLDOWN_EFFECT.get()))) {
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
				if (!(entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(AegisesModMobEffects.AEGIS_PNEUMA_EFFECT.get()))) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(AegisesModMobEffects.AEGIS_PNEUMA_EFFECT.get(), 6000, 0));
				}
			}
		}
	}
}
