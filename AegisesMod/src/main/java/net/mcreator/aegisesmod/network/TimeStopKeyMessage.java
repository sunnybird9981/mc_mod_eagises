package net.mcreator.aegisesmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
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
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal(
                        result ? "ザ・ワールド！時よ止まれ！" : "そして時は動き出す…"
                ));
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
