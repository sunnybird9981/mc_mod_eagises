package net.mcreator.aegisesmod.procedures;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ClearPotionEffectsHandler {
    private static final ResourceLocation ADVANCEMENT_ID = new ResourceLocation("aegisesmod", "unlock_time_stop");

    public static boolean canClearPotionEffects(ServerPlayer player) {
        Advancement advancement = player.server.getAdvancements().getAdvancement(ADVANCEMENT_ID);
        return advancement != null && player.getAdvancements().getOrStartProgress(advancement).isDone();
    }
}
