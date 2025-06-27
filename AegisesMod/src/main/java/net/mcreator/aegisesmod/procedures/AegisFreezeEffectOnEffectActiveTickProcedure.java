package net.mcreator.aegisesmod.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

public class AegisFreezeEffectOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
 {
			return;
		}
		entity.setDeltaMovement(new Vec3(0, 0, 0));
		if (entity.invulnerableTime > 4) {
			entity.invulnerableTime = 4;
		}
	}
}
