package com.robvanblerk.tieredpower.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.robvanblerk.tieredpower.block.entity.CropFarmerBlockEntity;
import com.robvanblerk.tieredpower.registry.ModMenus;

public class CropFarmerMenu extends MachineMenu {
	// 0-1 energy, 2 harvested, 3 FE/t
	public static final int DATA_COUNT = 4;
	public static final int SEED_X = 30, GRID_X = 98, TOP_Y = 17;

	private final Container container;

	public CropFarmerMenu(int containerId, Inventory inventory) {
		this(containerId, inventory, new SimpleContainer(14), new SimpleContainerData(DATA_COUNT));
	}

	public CropFarmerMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
		super(ModMenus.CROP_FARMER.get(), containerId, data, 14);
		this.container = container;
		for (int i = 0; i < CropFarmerBlockEntity.SEED_SLOTS; i++) {
			addSlot(new Slot(container, i, SEED_X, TOP_Y + i * 18) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return CropFarmerBlockEntity.isPlantable(stack);
				}
			});
		}
		for (int i = 0; i < 9; i++) {
			addOutputSlot(container, CropFarmerBlockEntity.OUTPUT_START + i, GRID_X + (i % 3) * 18, TOP_Y + (i / 3) * 18, false);
		}
		addUpgradeSlots(container, 12, 3);
		addPlayerInventory(inventory);
	}

	@Override
	public long getCapacity() {
		return CropFarmerBlockEntity.CAPACITY;
	}

	public int getHarvested() { return data.get(2) & 0xFFFF; }

	@Override
	public boolean stillValid(Player player) {
		return container.stillValid(player);
	}
}
