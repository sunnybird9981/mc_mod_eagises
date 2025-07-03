package net.mcreator.aegisesmod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Mod.EventBusSubscriber(modid = "aegisesesmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TimeStopEvents {

    private static final double STOP_RANGE = 32;
    private static final double STOP_RANGE_SQR = STOP_RANGE * STOP_RANGE;

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

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (event.getEntity() instanceof Player) return;

        Level level = mob.level();
        if (level.isClientSide) return;

        boolean isNearAnyPlayer = level.players().stream()
                .anyMatch(p -> p.distanceToSqr(mob) <= STOP_RANGE_SQR);

        if (TimeStopHandler.isTimeStopped() && isNearAnyPlayer) {
            mob.setDeltaMovement(Vec3.ZERO);
            mob.setNoAi(true);
            mob.invulnerableTime = 0;
        } else if (mob.isNoAi()) {
            mob.setNoAi(false);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.level.isClientSide) return;

        Level level = event.level;
        for (Player player : level.players()) {
            AABB area = player.getBoundingBox().inflate(STOP_RANGE);

            for (Mob mob : level.getEntitiesOfClass(Mob.class, area)) {
                if (mob.isNoAi()) mob.setNoAi(false);
            }

            for (Entity entity : level.getEntities(null, area)) {
                if (entity instanceof Player) continue;
                if (TimeStopHandler.isTimeStopped()) {
                    if (!(entity instanceof LivingEntity)) {
                        if (!storedVelocities.containsKey(entity.getUUID())) {
                            storeVelocity(entity);
                        }
                    }
                } else {
                    if (!(entity instanceof LivingEntity)) {
                        Vec3 stored = getStoredVelocity(entity);
                        if (!stored.equals(Vec3.ZERO)) {
                            entity.setDeltaMovement(stored);
                            storedVelocities.remove(entity.getUUID());
                        }
                    }
                }
            }
        }
    }
}