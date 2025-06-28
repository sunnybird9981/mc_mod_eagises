package net.mcreator.aegisesmod.procedures;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TimeStopHandler {
    private static boolean timeStopped = false;

    public static boolean isTimeStopped() {
        return timeStopped;
    }

    public static void toggleTimeStop() {
        timeStopped = !timeStopped;
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
