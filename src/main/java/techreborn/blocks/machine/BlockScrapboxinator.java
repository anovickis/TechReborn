package techreborn.blocks.machine;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.blocks.IRotationTexture;

import techreborn.Core;
import techreborn.client.EGui;
import techreborn.client.TechRebornCreativeTab;
import techreborn.tiles.TileScrapboxinator;

public class BlockScrapboxinator extends BlockMachineBase implements IRotationTexture {

	private final String prefix = "techreborn:blocks/machine/greg_machines/";

	public BlockScrapboxinator(final Material material) {
		super();
		this.setUnlocalizedName("techreborn.scrapboxinator");
		this.setCreativeTab(TechRebornCreativeTab.instance);
	}

	@Override
	public TileEntity createNewTileEntity(final World p_149915_1_, final int p_149915_2_) {
		return new TileScrapboxinator();
	}

	@Override
	public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float hitX,
			final float hitY, final float hitZ) {
		if (!player.isSneaking()) {
			player.openGui(Core.INSTANCE, EGui.SCRAPBOXINATOR.ordinal(), world, x, y, z);
		}
		return true;
	}

	@Override
	public String getFrontOff() {
		return this.prefix + "scrapboxinator_front_off";
	}

	@Override
	public String getFrontOn() {
		return this.prefix + "scrapboxinator_front_on";
	}

	@Override
	public String getSide() {
		return this.prefix + "machine_side";
	}

	@Override
	public String getTop() {
		return this.prefix + "machine_top";
	}

	@Override
	public String getBottom() {
		return this.prefix + "machine_bottom";
	}
}
