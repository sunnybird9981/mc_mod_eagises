package net.mcreator.aegisesmod.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "aegisesesmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TimeStopEvents {

    private static final double STOP_RANGE = 32;
    private static final double STOP_RANGE_SQR = STOP_RANGE * STOP_RANGE;

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
        } else if (mob.isNoAi()) {
            mob.setNoAi(false);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Level level = event.level;
        if (level.isClientSide) return;

        for (Player player : level.players()) {
            AABB area = player.getBoundingBox().inflate(10);

            // モブのAIを再開
            for (Mob mob : level.getEntitiesOfClass(Mob.class, area)) {
                if (mob.isNoAi()) {
                    mob.setNoAi(false);
                }
            }

            // エンティティ処理（飛び道具など）
            for (Entity entity : level.getEntities(null, area)) {
                if (entity instanceof Player) continue; // プレイヤーは無視

                boolean isNear = player.distanceToSqr(entity) <= STOP_RANGE_SQR;
            }
        }
    }
}
