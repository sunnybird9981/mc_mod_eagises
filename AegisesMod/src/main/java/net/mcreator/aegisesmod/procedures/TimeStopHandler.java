package net.mcreator.aegisesmod.procedures;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.world.entity.Mob;

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
}
