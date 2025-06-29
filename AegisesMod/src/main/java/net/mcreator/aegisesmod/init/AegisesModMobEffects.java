
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.aegisesmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

//import net.mcreator.aegisesmod.potion.PurifyingFlamesMobEffect;
//import net.mcreator.aegisesmod.potion.ForesightEffectMobEffect;
import net.mcreator.aegisesmod.potion.ForesightCoolDownEffectMobEffect;
import net.mcreator.aegisesmod.potion.AegisPneumaEffectMobEffect;
//import net.mcreator.aegisesmod.potion.AegisFreezeEffectMobEffect;
import net.mcreator.aegisesmod.potion.AegisCooldownEffectMobEffect;
import net.mcreator.aegisesmod.AegisesMod;

public class AegisesModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AegisesMod.MODID);
//	public static final RegistryObject<MobEffect> FORESIGHT_EFFECT = REGISTRY.register("foresight_effect", () -> new ForesightEffectMobEffect());
//	public static final RegistryObject<MobEffect> PURIFYING_FLAMES = REGISTRY.register("purifying_flames", () -> new PurifyingFlamesMobEffect());
	public static final RegistryObject<MobEffect> FORESIGHT_COOL_DOWN_EFFECT = REGISTRY.register("foresight_cool_down_effect", () -> new ForesightCoolDownEffectMobEffect());
	public static final RegistryObject<MobEffect> AEGIS_COOLDOWN_EFFECT = REGISTRY.register("aegis_cooldown_effect", () -> new AegisCooldownEffectMobEffect());
	public static final RegistryObject<MobEffect> AEGIS_PNEUMA_EFFECT = REGISTRY.register("aegis_pneuma_effect", () -> new AegisPneumaEffectMobEffect());
//	public static final RegistryObject<MobEffect> AEGIS_FREEZE_EFFECT = REGISTRY.register("aegis_freeze_effect", () -> new AegisFreezeEffectMobEffect());
}
