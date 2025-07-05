package net.mcreator.aegisesmod.network;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.aegisesmod.AegisesMod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AegisesModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		AegisesMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.mobEffectListToClear = original.mobEffectListToClear;
			if (!event.isWasDeath()) {
				clone.effectListMenuIndex = original.effectListMenuIndex;
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("aegisesmod", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double effectListMenuIndex = 0;
		public boolean addCooldownNextTick = false;


		public List<MobEffect> mobEffectListToClear = initMobEffectListToClear();


		public List<MobEffect> initMobEffectListToClear() {
			List<MobEffect> _mobEffectListToClear;
			_mobEffectListToClear = new ArrayList<MobEffect>();

			for(MobEffect e : ForgeRegistries.MOB_EFFECTS) {
				if (!e.isBeneficial()) {
					_mobEffectListToClear.add(e);
				}
			}
			return  _mobEffectListToClear;
		}


		public void savemobEffectListToClear(CompoundTag tag) {
			ListTag effectList = new ListTag();

			if (mobEffectListToClear == null) {
				mobEffectListToClear = new ArrayList<MobEffect>();
				Random rand = new Random();
				for (MobEffect e : ForgeRegistries.MOB_EFFECTS) {
					if (rand.nextInt(10) > 8) {
						if (!mobEffectListToClear.contains(e)) {
							mobEffectListToClear.add(e);
						}
					}
				}
			}


			for (MobEffect effect : this.mobEffectListToClear) {
				CompoundTag effectTag = new CompoundTag();
				effectTag.putString("Effect", effect.getDescriptionId());
				effectList.add(effectTag);
			}

			tag.put("mobEffectListToClear", effectList);
		}

		public List<MobEffect> readmobEffectListToClear(CompoundTag tag) {
			if (tag == null || !tag.contains("mobEffectListToClear")) {
				return initMobEffectListToClear();
			}

			mobEffectListToClear = new ArrayList<MobEffect>();
			List<MobEffect> effects = new ArrayList<MobEffect>();
			ListTag effectList = tag.getList("mobEffectListToClear", 10);

			for(int i = 0; i < effectList.size(); i++) {
				CompoundTag effectTag = effectList.getCompound(i);
				String effectId = effectTag.getString("Effect");
				if (effectId.startsWith("effect.")) {
					effectId = effectId.substring(7);
				}
				effectId = effectId.replace(".", ":");
				MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(effectId));
				if(effect != null) {
					effects.add(effect);
				}
			}

			return effects;
		}

		public void setMobEffectListToClear(Entity entity, List<MobEffect> _mobEffectListToClear) {
			if(entity == null) {
				return;
			} else {
				for (MobEffect e : _mobEffectListToClear) {
				}
				entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					if (!entity.level().isClientSide()) {
						capability.mobEffectListToClear = _mobEffectListToClear;
						capability.syncPlayerVariables(entity);
					}
				});
			}
		}


		public List<MobEffect> getMobEffectListToClear(Entity entity) {
			List<MobEffect> _mobEffectListToClear = (entity.getCapability(AegisesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).mobEffectListToClear;
			return _mobEffectListToClear;
		}


		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer) {
				AegisesMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
			}
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("addCooldownNextTick", addCooldownNextTick);
			nbt.putDouble("effectListMenuIndex", effectListMenuIndex);
			savemobEffectListToClear(nbt);

			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			addCooldownNextTick = nbt.getBoolean("addCooldownNextTick");
			effectListMenuIndex = nbt.getDouble("effectListMenuIndex");
			mobEffectListToClear = readmobEffectListToClear(nbt);
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.effectListMenuIndex = message.data.effectListMenuIndex;
					variables.addCooldownNextTick = message.data.addCooldownNextTick;
					variables.mobEffectListToClear = message.data.mobEffectListToClear;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
