package net.mcreator.aegisesmod.mixin;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import net.mcreator.aegisesmod.procedures.TimeStopHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public class ProjectileTickMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        if (TimeStopHandler.isTimeStopped()) {
            ((Projectile)(Object) this).setDeltaMovement(Vec3.ZERO);
        }
    }
}
