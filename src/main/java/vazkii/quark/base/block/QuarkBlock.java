package vazkii.quark.base.block;

import java.util.function.Supplier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.arl.block.BasicBlock;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.moduleloader.Module;

public class QuarkBlock extends BasicBlock {
	
	private final Module module;
	private Supplier<Boolean> enabledSupplier = () -> true; 

	public QuarkBlock(String regname, Module module, ItemGroup creativeTab, Properties properties) {
		super(regname, properties);
		this.module = module;
		
		if(creativeTab != null)
			RegistryHelper.setCreativeTab(this, creativeTab);
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(isEnabled() || group == ItemGroup.SEARCH)
			super.fillItemGroup(group, items);
	}
	
	public QuarkBlock setCondition(Supplier<Boolean> enabledSupplier) {
		this.enabledSupplier = enabledSupplier;
		return this;
	}
	
	public boolean isEnabled() {
		return module != null && module.enabled && enabledSupplier.get();
	}

}