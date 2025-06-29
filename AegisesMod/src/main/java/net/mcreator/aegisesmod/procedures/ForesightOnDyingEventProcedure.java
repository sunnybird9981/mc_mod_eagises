package net.mcreator.aegisesmod.procedures;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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

		if (entity instanceof ServerPlayer player) {
			var advancement = player.server.getAdvancements().getAdvancement(new ResourceLocation("aegisesmod", "unlock_time_stop"));
			boolean hasAdvancement = advancement != null && player.getAdvancements().getOrStartProgress(advancement).isDone();

			boolean noCooldown = !player.hasEffect(AegisesModMobEffects.AEGIS_COOLDOWN_EFFECT.get());

			if (hasAdvancement && noCooldown) {
				player.setHealth(player.getMaxHealth());
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}

				if (!player.hasEffect(AegisesModMobEffects.AEGIS_PNEUMA_EFFECT.get())) {
					player.addEffect(new MobEffectInstance(AegisesModMobEffects.AEGIS_PNEUMA_EFFECT.get(), 6000, 0));
				}
			}
		}
	}
}
