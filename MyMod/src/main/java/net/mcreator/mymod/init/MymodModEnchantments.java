
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.mymod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.mymod.enchantment.AegisesEnchantment;
import net.mcreator.mymod.MymodMod;

public class MymodModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MymodMod.MODID);
	public static final RegistryObject<Enchantment> AEGISES = REGISTRY.register("aegises", () -> new AegisesEnchantment());
}
