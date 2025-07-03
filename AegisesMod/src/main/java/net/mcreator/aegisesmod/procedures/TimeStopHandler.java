package net.mcreator.aegisesmod.procedures;


import net.minecraft.advancements.Advancement;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TimeStopHandler {
    private static boolean timeStopped = false;
    private static final ResourceLocation ADVANCEMENT_ID = new ResourceLocation("aegisesmod", "unlock_time_stop");

    public static boolean isTimeStopped() {
        return timeStopped;
    }

    public static boolean canUseTimeStop(ServerPlayer player) {
        Advancement advancement = player.server.getAdvancements().getAdvancement(ADVANCEMENT_ID);
        return advancement != null && player.getAdvancements().getOrStartProgress(advancement).isDone();
    }

    public static boolean toggleTimeStop(ServerPlayer player) {
        if (!canUseTimeStop(player)) return false;

        timeStopped = !timeStopped;
        ServerLevel level = player.serverLevel();

        if (timeStopped) {
            level.playSound(null, player.blockPosition(), SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS, 0.8f, 0.9f);
            level.playSound(null, player.blockPosition(), SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS, 1.5f, 0.7f);
            level.playSound(null, player.blockPosition(), SoundEvents.AMBIENT_CAVE.get(), SoundSource.PLAYERS, 0.4f, 1.2f);

            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false));

            double r = 4.0;
            for (int i = 0; i < 360; i += 15) {
                for (int j = -90; j <= 90; j += 15) {
                    double x = player.getX() + r * Math.cos(Math.toRadians(j)) * Math.cos(Math.toRadians(i));
                    double y = player.getY() + 1 + r * Math.sin(Math.toRadians(j));
                    double z = player.getZ() + r * Math.cos(Math.toRadians(j)) * Math.sin(Math.toRadians(i));
                    level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0, 0, 0, 0);
                }
            }

        } else {
            level.playSound(null, player.blockPosition(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.2f, 1.0f);
            level.playSound(null, player.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.5f, 1.3f);

            double height = 2.5;
            for (double t = 0; t < Math.PI * 4; t += Math.PI / 16) {
                double x = player.getX() + Math.cos(t) * 1.5;
                double y = player.getY() + 0.5 + (height * t / (Math.PI * 4));
                double z = player.getZ() + Math.sin(t) * 1.5;
                level.sendParticles(ParticleTypes.WAX_ON, x, y, z, 1, 0, 0, 0, 0);
            }

            level.sendParticles(ParticleTypes.FLASH, player.getX(), player.getY() + 1.0, player.getZ(), 1, 0, 0, 0, 0);
        }

        return true;
    }



    public static void setTimeStopped(boolean value) {
        timeStopped = value;
    }

    private static final Map<UUID, Vec3> storedVelocities = new ConcurrentHashMap<>();

    public static void storeVelocity(Entity entity) {
        storedVelocities.put(entity.getUUID(), entity.getDeltaMovement());
    }

    public static Vec3 getStoredVelocity(Entity entity) {
        return storedVelocities.getOrDefault(entity.getUUID(), Vec3.ZERO);
    }

    public static void clearStoredVelocities() {
        storedVelocities.clear();
    }
}
