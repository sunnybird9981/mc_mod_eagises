//package net.mcreator.aegisesmod.procedures;
//
//import net.mcreator.aegisesmod.procedures.TimeStopHandler;
//import net.minecraft.world.entity.projectile.Projectile;
//import net.minecraft.world.level.Level;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(Projectile.class)
//public class ProjectileTickMixin {
//    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
//    private void onTick(CallbackInfo ci) {
//        Projectile self = (Projectile)(Object)this;
//        Level level = self.level();
//        if (!level.isClientSide && TimeStopHandler.isTimeStopped()) {
//            ci.cancel();
//        }
//    }
//}