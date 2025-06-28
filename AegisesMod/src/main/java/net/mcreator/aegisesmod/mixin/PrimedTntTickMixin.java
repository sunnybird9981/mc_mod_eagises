package net.mcreator.aegisesmod.mixin;

import net.mcreator.aegisesmod.procedures.TimeStopHandler;
import net.minecraft.world.entity.item.PrimedTnt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PrimedTnt.class)
public class PrimedTntTickMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        if (TimeStopHandler.isTimeStopped()) {
            ci.cancel();
        }
    }
}