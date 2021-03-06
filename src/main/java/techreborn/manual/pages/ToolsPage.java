package techreborn.manual.pages;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import techreborn.init.ModItems;
import techreborn.manual.PageCollection;
import techreborn.manual.Reference;
import techreborn.manual.util.ButtonUtil;
import techreborn.manual.util.GuiButtonItemTexture;

import java.awt.*;

public class ToolsPage extends TitledPage {
	public ToolsPage(String name, PageCollection collection) {
		super(name, false, collection, Reference.TOOLS_KEY, Color.white.getRGB());
	}

	@Override
	public void initGui() {
		buttonList.clear();
		ButtonUtil.addBackButton(0, width / 2 - 60, height / 2 + 64, buttonList);
		ButtonUtil.addNextButton(1, width / 2 + 40, height / 2 + 64, buttonList);
		buttonList.add(new GuiButtonItemTexture(2, getXMin() + 20, getYMin() + 20, 0, 46, 100, 20,
			new ItemStack(ModItems.STEEL_DRILL), ModItems.STEEL_DRILL.getUnlocalizedName(),
			ttl(ModItems.STEEL_DRILL.getUnlocalizedName() + ".name")));
		buttonList.add(new GuiButtonItemTexture(3, getXMin() + 20, getYMin() + 40, 0, 46, 100, 20,
			new ItemStack(ModItems.DIAMOND_DRILL), ModItems.DIAMOND_DRILL.getUnlocalizedName(),
			ttl(ModItems.DIAMOND_DRILL.getUnlocalizedName() + ".name")));
		buttonList.add(new GuiButtonItemTexture(4, getXMin() + 20, getYMin() + 60, 0, 46, 100, 20,
			new ItemStack(ModItems.ADVANCED_DRILL), ModItems.ADVANCED_DRILL.getUnlocalizedName(),
			ttl(ModItems.ADVANCED_DRILL.getUnlocalizedName() + ".name")));
		buttonList.add(new GuiButtonItemTexture(5, getXMin() + 20, getYMin() + 80, 0, 46, 100, 20,
			new ItemStack(ModItems.STEEL_CHAINSAW), ModItems.STEEL_CHAINSAW.getUnlocalizedName(),
			ttl(ModItems.STEEL_CHAINSAW.getUnlocalizedName() + ".name")));
		buttonList.add(new GuiButtonItemTexture(6, getXMin() + 20, getYMin() + 100, 0, 46, 100, 20,
			new ItemStack(ModItems.DIAMOND_CHAINSAW), ModItems.DIAMOND_CHAINSAW.getUnlocalizedName(),
			ttl(ModItems.DIAMOND_CHAINSAW.getUnlocalizedName() + ".name")));
		buttonList.add(new GuiButtonItemTexture(7, getXMin() + 20, getYMin() + 120, 0, 46, 100, 20,
			new ItemStack(ModItems.ADVANCED_CHAINSAW), ModItems.ADVANCED_CHAINSAW.getUnlocalizedName(),
			ttl(ModItems.ADVANCED_CHAINSAW.getUnlocalizedName() + ".name")));
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button.id == 0)
			collection.changeActivePage(Reference.pageNames.CONTENTS_PAGE);
		if (button.id == 1) {
			buttonList.clear();
			ButtonUtil.addBackButton(0, width / 2 - 60, height / 2 + 64, buttonList);
			buttonList.add(new GuiButtonItemTexture(8, getXMin() + 20, getYMin() + 20, 0, 46, 100, 20,
				new ItemStack(ModItems.OMNI_TOOL), ModItems.OMNI_TOOL.getUnlocalizedName(),
				ttl(ModItems.OMNI_TOOL.getUnlocalizedName() + ".name")));
			buttonList.add(new GuiButtonItemTexture(9, getXMin() + 20, getYMin() + 40, 0, 46, 100, 20,
				new ItemStack(ModItems.TREE_TAP), ModItems.TREE_TAP.getUnlocalizedName(),
				ttl(ModItems.TREE_TAP.getUnlocalizedName() + ".name")));
		}
		if (button.id == 2)
			collection.changeActivePage(ModItems.STEEL_DRILL.getUnlocalizedName() + ".name");
		if (button.id == 3)
			collection.changeActivePage(ModItems.DIAMOND_DRILL.getUnlocalizedName() + ".name");
		if (button.id == 4)
			collection.changeActivePage(ModItems.ADVANCED_DRILL.getUnlocalizedName() + ".name");
		if (button.id == 5)
			collection.changeActivePage(ModItems.STEEL_CHAINSAW.getUnlocalizedName() + ".name");
		if (button.id == 6)
			collection.changeActivePage(ModItems.DIAMOND_CHAINSAW.getUnlocalizedName() + ".name");
		if (button.id == 7)
			collection.changeActivePage(ModItems.ADVANCED_CHAINSAW.getUnlocalizedName() + ".name");
		if (button.id == 8)
			collection.changeActivePage(ModItems.OMNI_TOOL.getUnlocalizedName() + ".name");
		if (button.id == 9)
			collection.changeActivePage(ModItems.TREE_TAP.getUnlocalizedName() + ".name");
	}
}
