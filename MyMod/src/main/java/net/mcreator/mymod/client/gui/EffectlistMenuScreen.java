package net.mcreator.mymod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.mymod.world.inventory.EffectlistMenuMenu;
import net.mcreator.mymod.procedures.EffectlistMenuValueProcedure;
import net.mcreator.mymod.network.EffectlistMenuButtonMessage;
import net.mcreator.mymod.MymodMod;

import java.util.HashMap;
import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.mymod.network.MymodModVariables;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.client.gui.components.Button;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectlistMenuScreen extends AbstractContainerScreen<EffectlistMenuMenu> {
	private final static HashMap<String, Object> guistate = EffectlistMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_button1;
	Button button_button2;
	Button button_button3;
	Button button_button4;
	Button button_button5;
	ImageButton imagebutton_uparrow;
	ImageButton imagebutton_downarrow;

	List<MobEffect> _mobEffectListAll;

	int effectListMenuIndex;

	public EffectlistMenuScreen(EffectlistMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("mymod:textures/screens/effectlist_menu.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}



	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		MymodModVariables.PlayerVariables playerVariables = entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables());

		//_mobEffectListToClear = new ArrayList<MobEffect>();
		List<MobEffect> _mobEffectListToClear;
		_mobEffectListToClear = playerVariables.getMobEffectListToClear(entity);

		/*
		if(_mobEffectListToClear == null) {
			//System.out.println("aaaaaaaaa EffectlistMenuScreen / renderLabels / _mobEffectListToClear == null");
			_mobEffectListToClear = new ArrayList<MobEffect>();
		}

		 */


		_mobEffectListAll = new ArrayList<MobEffect>();
		for (MobEffect e : ForgeRegistries.MOB_EFFECTS) {
			_mobEffectListAll.add(e);
		}

		effectListMenuIndex = (int) playerVariables.effectListMenuIndex;
		guiGraphics.drawString(this.font,

				EffectlistMenuValueProcedure.execute(entity), 16, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable(_mobEffectListAll.get(0 + effectListMenuIndex).getDisplayName().getString()), 38, 25, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable(_mobEffectListAll.get(1 + effectListMenuIndex).getDisplayName().getString()), 38, 50, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable(_mobEffectListAll.get(2 + effectListMenuIndex).getDisplayName().getString()), 38, 75, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable(_mobEffectListAll.get(3 + effectListMenuIndex).getDisplayName().getString()), 38, 100, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable(_mobEffectListAll.get(4 + effectListMenuIndex).getDisplayName().getString()), 38, 125, -12829636, false);

		if(_mobEffectListToClear.contains(_mobEffectListAll.get(0 + effectListMenuIndex))) {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_true"), 38, 35, -12829636, false);
		} else {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_false"), 38, 35, -12829636, false);
		}

		if(_mobEffectListToClear.contains(_mobEffectListAll.get(1 + effectListMenuIndex))) {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_true"), 38, 60, -12829636, false);
		} else {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_false"), 38, 60, -12829636, false);
		}

		if(_mobEffectListToClear.contains(_mobEffectListAll.get(2 + effectListMenuIndex))) {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_true"), 38, 85, -12829636, false);
		} else {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_false"), 38, 85, -12829636, false);
		}

		if(_mobEffectListToClear.contains(_mobEffectListAll.get(3 + effectListMenuIndex))) {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_true"), 38, 110, -12829636, false);
		} else {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_false"), 38, 110, -12829636, false);
		}

		if(_mobEffectListToClear.contains(_mobEffectListAll.get(4 + effectListMenuIndex))) {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_true"), 38, 135, -12829636, false);
		} else {
			guiGraphics.drawString(this.font, Component.translatable("gui.mymod.effectlist_menu.button_false"), 38, 135, -12829636, false);
		}
	}

	@Override
	public void init() {
		super.init();
		if (entity instanceof Player) {

			/*
			MymodModVariables.PlayerVariables playerVariables = entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables());
			_mobEffectListToClear = playerVariables.getMobEffectListToClear(entity);
			 */
			MymodModVariables.PlayerVariables playerVariables = entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables());
			List<MobEffect> _mobEffectListToClear = (entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MymodModVariables.PlayerVariables())).mobEffectListToClear;
			//_mobEffectListToClear = new ArrayList<MobEffect>();
			//_mobEffectListToClear = playerVariables.getmobEffectListToClear((Player) entity);
			/*
			if (_mobEffectListToClear == null) {
				System.out.println("aaaaaaaaaaaaa EffectlistMenuScreen : _mobEffectListToClear == null");
				_mobEffectListToClear = new ArrayList<MobEffect>();
			}


			for (MobEffect e : _mobEffectListToClear) {
				System.out.println("aaaaaaaaaaaaa EffectListMenuScreen init() _mobEffectListToClear : " + e);
			}

			 */


			_mobEffectListAll = new ArrayList<MobEffect>();
			for (MobEffect e : ForgeRegistries.MOB_EFFECTS) {
				_mobEffectListAll.add(e);
			}

//			playerVariables.savemobEffectListToClear(entity, _mobEffectListToClear);

			button_button1 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.label_empty"), e -> {
				MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(1, x, y, z));
				EffectlistMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
				/*
				for (MobEffect effect : ForgeRegistries.MOB_EFFECTS) {
					if (effect.isBeneficial()) {
						if (!_mobEffectListToClear.contains(effect)) {
							_mobEffectListToClear.add(effect);
						}
					}
					entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.mobEffectListToClear = _mobEffectListToClear;
						capability.syncPlayerVariables(entity);
					});
				}

				 */
			}).bounds(this.leftPos + 13, this.topPos + 20, 20, 20).build();
			guistate.put("button:button_false", button_button1);
			this.addRenderableWidget(button_button1);


			button_button2 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.label_empty"), e -> {
				MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(2, x, y, z));
				EffectlistMenuButtonMessage.handleButtonAction(entity, 2, x, y, z);
				/*
				if (_mobEffectListToClear.contains(_mobEffectListAll.get(1 + effectListMenuIndex))) {
					_mobEffectListToClear.remove(_mobEffectListAll.get(1 + effectListMenuIndex));
				} else {
					_mobEffectListToClear.add(_mobEffectListAll.get(1 + effectListMenuIndex));
				}
				entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mobEffectListToClear = _mobEffectListToClear;
					capability.syncPlayerVariables(entity);
				});
				 */
			}).bounds(this.leftPos + 13, this.topPos + 45, 20, 20).build();
			guistate.put("button:button_false1", button_button2);
			this.addRenderableWidget(button_button2);


			button_button3 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.label_empty"), e -> {
				MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(3, x, y, z));
				EffectlistMenuButtonMessage.handleButtonAction(entity, 3, x, y, z);
				/*
				if (_mobEffectListToClear.contains(_mobEffectListAll.get(2 + effectListMenuIndex))) {
					_mobEffectListToClear.remove(_mobEffectListAll.get(2 + effectListMenuIndex));
				} else {
					_mobEffectListToClear.add(_mobEffectListAll.get(2 + effectListMenuIndex));
				}
				entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mobEffectListToClear = _mobEffectListToClear;
					capability.syncPlayerVariables(entity);
				});

				 */
			}).bounds(this.leftPos + 13, this.topPos + 70, 20, 20).build();
			guistate.put("button:button_false2", button_button3);
			this.addRenderableWidget(button_button3);


			button_button4 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.label_empty"), e -> {
				MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(4, x, y, z));
				EffectlistMenuButtonMessage.handleButtonAction(entity, 4, x, y, z);
				/*
				if (_mobEffectListToClear.contains(_mobEffectListAll.get(3 + effectListMenuIndex))) {
					_mobEffectListToClear.remove(_mobEffectListAll.get(3 + effectListMenuIndex));
				} else {
					_mobEffectListToClear.add(_mobEffectListAll.get(3 + effectListMenuIndex));
				}
				entity.getCapability(MymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mobEffectListToClear = _mobEffectListToClear;
					capability.syncPlayerVariables(entity);
				});

				 */
			}).bounds(this.leftPos + 13, this.topPos + 95, 20, 20).build();
			guistate.put("button:button_false3", button_button4);
			this.addRenderableWidget(button_button4);


			button_button5 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.label_empty"), e -> {
				MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(5, x, y, z));
				EffectlistMenuButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}).bounds(this.leftPos + 13, this.topPos + 120, 20, 20).build();
			guistate.put("button:button_false4", button_button5);
			this.addRenderableWidget(button_button5);


			imagebutton_uparrow = new ImageButton(this.leftPos + 155, this.topPos + 7, 16, 16, 0, 0, 16, new ResourceLocation("mymod:textures/screens/atlas/imagebutton_uparrow.png"), 16, 32, e -> {

				/*
				for (int i = 0; i < _mobEffectListToClear.size(); i++) {
					System.out.println(_mobEffectListToClear.get(i).getDisplayName().getString());
				}

				 */
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(6, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 6, x, y, z);
				}

			});
			guistate.put("button:imagebutton_uparrow", imagebutton_uparrow);
			this.addRenderableWidget(imagebutton_uparrow);
			imagebutton_downarrow = new ImageButton(this.leftPos + 140, this.topPos + 143, 16, 16, 0, 0, 16, new ResourceLocation("mymod:textures/screens/atlas/imagebutton_downarrow.png"), 16, 32, e -> {
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(7, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 7, x, y, z);
				}
			});
			guistate.put("button:imagebutton_downarrow", imagebutton_downarrow);
			this.addRenderableWidget(imagebutton_downarrow);
		}
	}
}


/*
			button_button1 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.button_button1"), e -> {
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(0, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
				}
			}).bounds(this.leftPos + 6, this.topPos + 28, 61, 20).build();
			guistate.put("button:button_button1", button_button1);
			this.addRenderableWidget(button_button1);
			button_button2 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.button_button2"), e -> {
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(1, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
				}
			}).bounds(this.leftPos + 15, this.topPos + 51, 61, 20).build();
			guistate.put("button:button_button2", button_button2);
			this.addRenderableWidget(button_button2);
			button_button3 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.button_button3"), e -> {
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(2, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 2, x, y, z);
				}
			}).bounds(this.leftPos + 15, this.topPos + 76, 61, 20).build();
			guistate.put("button:button_button3", button_button3);
			this.addRenderableWidget(button_button3);
			button_button4 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.button_button4"), e -> {
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(3, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 3, x, y, z);
				}
			}).bounds(this.leftPos + 15, this.topPos + 101, 61, 20).build();
			guistate.put("button:button_button4", button_button4);
			this.addRenderableWidget(button_button4);
			button_button5 = Button.builder(Component.translatable("gui.mymod.effectlist_menu.button_button5"), e -> {
				if (true) {
					MymodMod.PACKET_HANDLER.sendToServer(new EffectlistMenuButtonMessage(4, x, y, z));
					EffectlistMenuButtonMessage.handleButtonAction(entity, 4, x, y, z);
				}
			}).bounds(this.leftPos + 15, this.topPos + 123, 61, 20).build();
			guistate.put("button:button_button5", button_button5);
			this.addRenderableWidget(button_button5);

 */