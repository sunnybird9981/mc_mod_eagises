package net.mcreator.aegisesmod.mixin;

import net.mcreator.aegisesmod.procedures.TimeStopHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class GeneralEntityTickMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        Level level = self.level();

        if (!level.isClientSide && !(self instanceof Player)) {
            if (TimeStopHandler.isTimeStopped()) {
                self.setDeltaMovement(Vec3.ZERO);
                ci.cancel();
            }
        }
    }
}