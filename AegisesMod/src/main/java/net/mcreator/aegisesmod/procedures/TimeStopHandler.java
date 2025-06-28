package net.mcreator.aegisesmod.procedures;


import net.minecraft.advancements.Advancement;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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
            player.displayClientMessage(Component.literal("あなたには時を止める力がまだありません！"), true);
            return false;
        }

        timeStopped = !timeStopped;
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
