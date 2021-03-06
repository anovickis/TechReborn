package techreborn.items;

import com.google.common.base.CaseFormat;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import techreborn.client.TechRebornCreativeTabMisc;
import techreborn.init.ModItems;

import java.security.InvalidParameterException;

public class ItemGems extends ItemTRNoDestroy {

	public static final String[] types = new String[] { "ruby", "sapphire", "peridot", "red_garnet", "yellow_garnet" };

	public ItemGems() {
		setCreativeTab(TechRebornCreativeTabMisc.instance);
		setUnlocalizedName("techreborn.gem");
		setHasSubtypes(true);
	}

	public static ItemStack getGemByName(String name, int count) {
		name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
		for (int i = 0; i < types.length; i++) {
			if (types[i].equalsIgnoreCase(name)) {
				return new ItemStack(ModItems.GEMS, count, i);
			}
		}
		throw new InvalidParameterException("The gem " + name + " could not be found.");
	}

	public static ItemStack getGemByName(String name) {
		return getGemByName(name, 1);
	}

	@Override
	// gets Unlocalized Name depending on meta data
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= types.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + types[meta];
	}

	// Adds Dusts SubItems To Creative Tab
	public void getSubItems(Item item, CreativeTabs creativeTabs, NonNullList list) {
		for (int meta = 0; meta < types.length; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}

}
