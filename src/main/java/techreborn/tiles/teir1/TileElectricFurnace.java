package techreborn.tiles.teir1;

import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.blocks.BlockMachineBase;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.TileMachineBase;

public class TileElectricFurnace extends TileMachineBase implements ISidedInventory {

	private static final int[] SLOTS_TOP = new int[] { 4 + 1 };
	private static final int[] SLOTS_BOTTOM = new int[] { 4 + 2 };
	private static final int[] SLOTS_SIDES = new int[] { 4 + 3 };
	public int maxProgress = 100;
	public int cost = ConfigTechReborn.ELECTRIC_FURNACE_COST;
	public int input = 4 + 1;
	public int output = 4 + 2;

	public TileElectricFurnace() {
		super(4, "TileElectricFurnace", 64, ConfigTechReborn.ELECTRIC_FURNACE_MAX_POWER, EnumPowerTier.LOW, new ItemStack(ModBlocks.electricFurnace));
	}

	public int gaugeProgressScaled(int scale) {
		return (progress * scale) / maxProgress;
	}

	@Override
	public void update() {
		super.update();
		if (world.isRemote) {
			return;
		}
		boolean burning = isBurning();
		boolean updateInventory = false;
		if (isBurning() && canSmelt() && !getStackInSlot(input).isEmpty()) {
			updateState();

			progress++;
			if (progress % 10 == 0) {
				useEnergy(cost);
			}
			if (progress > maxProgress) {
				cookItems();
				progress = 0;
				updateInventory = true;
			}
			if (getStackInSlot(input).isEmpty())
				progress = 0;
		} else {
			progress = 0;
			updateState();
		}
		if (burning != isBurning()) {
			updateInventory = true;
		}
		if (getStackInSlot(input).isEmpty()) {
			progress = 0;
			updateInventory = true;
		}
		if (updateInventory) {
			markDirty();
		}
	}

	public void cookItems() {
		if (this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(input));

			if (getStackInSlot(output) == ItemStack.EMPTY) {
				setInventorySlotContents(output, itemstack.copy());
			} else if (getStackInSlot(output).isItemEqual(itemstack)) {
				getStackInSlot(output).grow(itemstack.getCount());
			}
			if (getStackInSlot(input).getCount() > 1) {
				this.decrStackSize(input, 1);
			} else {
				setInventorySlotContents(input, ItemStack.EMPTY);
			}
		}
	}

	public boolean canSmelt() {
		if (getStackInSlot(input) == ItemStack.EMPTY) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(input));
			if (itemstack == ItemStack.EMPTY)
				return false;
			if (getStackInSlot(output) == ItemStack.EMPTY)
				return true;
			if (!getStackInSlot(output).isItemEqual(itemstack))
				return false;
			int result = getStackInSlot(output).getCount() + itemstack.getCount();
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public boolean isBurning() {
		return getEnergy() >= cost;
	}

	public ItemStack getResultFor(ItemStack stack) {
		ItemStack result = FurnaceRecipes.instance().getSmeltingResult(stack);
		if (result != ItemStack.EMPTY) {
			return result.copy();
		}
		return null;
	}

	public void updateState() {
		IBlockState BlockStateContainer = world.getBlockState(pos);
		if (BlockStateContainer.getBlock() instanceof BlockMachineBase) {
			BlockMachineBase blockMachineBase = (BlockMachineBase) BlockStateContainer.getBlock();
			if (BlockStateContainer.getValue(BlockMachineBase.ACTIVE) != progress > 0)
				blockMachineBase.setActive(progress > 0, world, pos);
		}
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return false;
	}

	public boolean isComplete() {
		return false;
	}

	// ISidedInventory
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
	}

	@Override
	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side) {
		if (slotIndex == 2)
			return false;
		return isItemValidForSlot(slotIndex, itemStack);
	}

	@Override
	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side) {
		return slotIndex == 1;
	}
}
