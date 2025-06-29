package net.mcreator.aegisesmod.procedures;


import net.minecraft.advancements.Advancement;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        if (!canUseTimeStop(player)) {
            return false;
        }

        timeStopped = !timeStopped;

        if (timeStopped) {
            player.level().playSound(null, player.blockPosition(), SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS, 1.0f, 1.0f);

            if (player.level() instanceof ServerLevel level) {
                double r = 3.0;
                for (int i = 0; i < 360; i += 15) {
                    for (int j = -90; j <= 90; j += 15) {
                        double x = player.getX() + r * Math.cos(Math.toRadians(j)) * Math.cos(Math.toRadians(i));
                        double y = player.getY() + 1 + r * Math.sin(Math.toRadians(j));
                        double z = player.getZ() + r * Math.cos(Math.toRadians(j)) * Math.sin(Math.toRadians(i));
                        level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0, 0, 0, 0);
                    }
                }
            }
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
