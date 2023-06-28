package lafolie.fmc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import lafolie.fmc.element.ElementalAttribute;
import lafolie.fmc.element.ElementalObject;
import lafolie.fmc.internal.element.ElementalStats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ElementalObject, ElementalStats
{
	@Shadow
	public abstract Item getItem();

	@Shadow
	public abstract NbtCompound getOrCreateSubNbt(String key);

	// @Shadow
	// public abstract NbtCompound getSubNbt(String key);

	@Override
	public ElementalStats getElementalStats()
	{
		return (ElementalStats)this;
	}

	@Override
	public NbtCompound getNbtCompound()
	{
		return getOrCreateSubNbt(NBT_KEY);
	}
}
