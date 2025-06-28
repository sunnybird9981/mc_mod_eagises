package net.mcreator.aegisesmod.procedures;

import net.mcreator.aegisesmod.network.AegisesModVariables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class AutoClearPotionEffectHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide || !(player instanceof ServerPlayer serverPlayer)) return;

        var adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("aegisesmod", "unlock_clear_effects"));
        if (adv == null || !serverPlayer.getAdvancements().getOrStartProgress(adv).isDone()) return;

        var cap = serverPlayer.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(null);
        if (cap == null) return;


        for (MobEffectInstance effectInstance : player.getActiveEffects()) {
            if (cap.mobEffectListToClear.contains(effectInstance.getEffect())) {
                player.removeEffect(effectInstance.getEffect());
            }
        }
    }
}