package net.mcreator.aegisesmod.procedures;

import net.mcreator.aegisesmod.init.AegisesModMobEffects;
import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AegisEffectEventHandler {
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (event.getEffect() != null &&
                event.getEffect() == AegisesModMobEffects.AEGIS_PNEUMA_EFFECT.get()) {

            player.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(vars -> {
                vars.addCooldownNextTick = true;
                vars.syncPlayerVariables(player);
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        player.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(vars -> {
            if (vars.addCooldownNextTick) {
                vars.addCooldownNextTick = false;

                if (!player.hasEffect(AegisesModMobEffects.AEGIS_COOLDOWN_EFFECT.get())) {
                    player.addEffect(new MobEffectInstance(
                            AegisesModMobEffects.AEGIS_COOLDOWN_EFFECT.get(),
                            6000, 0, false, true, true
                    ));
                }
            }
        });
    }
}
