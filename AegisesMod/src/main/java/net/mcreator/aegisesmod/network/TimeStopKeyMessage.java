package net.mcreator.aegisesmod.network;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.network.NetworkEvent;
import net.mcreator.aegisesmod.procedures.TimeStopHandler;

import java.util.function.Supplier;

public class TimeStopKeyMessage {
    public TimeStopKeyMessage() {}

    public static void encode(TimeStopKeyMessage message, FriendlyByteBuf buffer) {}
    public static TimeStopKeyMessage decode(FriendlyByteBuf buffer) {
        return new TimeStopKeyMessage();
    }

    public static void handle(TimeStopKeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            ServerPlayer player = contextSupplier.get().getSender();
            if (player != null) {
                boolean result = TimeStopHandler.toggleTimeStop(player);
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
