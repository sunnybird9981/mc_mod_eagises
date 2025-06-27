
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.mymod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.mymod.world.inventory.EffectlistMenuMenu;
import net.mcreator.mymod.MymodMod;

public class MymodModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MymodMod.MODID);
	public static final RegistryObject<MenuType<EffectlistMenuMenu>> EFFECTLIST_MENU = REGISTRY.register("effectlist_menu", () -> IForgeMenuType.create(EffectlistMenuMenu::new));
}
