
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.aegisesmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;


import net.mcreator.aegisesmod.potion.AegisPneumaEffectMobEffect;
import net.mcreator.aegisesmod.potion.AegisCooldownEffectMobEffect;
import net.mcreator.aegisesmod.AegisesMod;

public class AegisesModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AegisesMod.MODID);
	public static final RegistryObject<MobEffect> AEGIS_COOLDOWN_EFFECT = REGISTRY.register("aegis_cooldown_effect", () -> new AegisCooldownEffectMobEffect());
	public static final RegistryObject<MobEffect> AEGIS_PNEUMA_EFFECT = REGISTRY.register("aegis_pneuma_effect", () -> new AegisPneumaEffectMobEffect());
}
