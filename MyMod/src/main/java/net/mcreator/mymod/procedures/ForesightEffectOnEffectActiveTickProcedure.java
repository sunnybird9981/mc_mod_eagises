package net.mcreator.mymod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Comparator;

public class ForesightEffectOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double yRelativePosition = 0;
		double xRelativePosition = 0;
		double zRelativePosition = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(30 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				//if (!(entityiterator instanceof LivingEntity || entityiterator instanceof ItemEntity || entityiterator instanceof FallingBlockEntity)) {
				if (!(entityiterator instanceof ItemEntity)) {
					if (entityiterator.getPersistentData().getBoolean("StopFlag") == true) {
						entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
					} else {
						xRelativePosition = entity.getX() - entityiterator.getX();
						yRelativePosition = entity.getY() - entityiterator.getY();
						zRelativePosition = entity.getZ() - entityiterator.getZ();
						if (Math.abs(xRelativePosition) + Math.abs(yRelativePosition) > 2) {
							if (entityiterator.getDeltaMovement().z() * zRelativePosition + entityiterator.getDeltaMovement().y() * yRelativePosition + entityiterator.getDeltaMovement().x() * xRelativePosition > 0) {
								entityiterator.getPersistentData().putBoolean("StopFlag", true);
								entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
							}
						}
					}
				}
			}
		}
	}
}
