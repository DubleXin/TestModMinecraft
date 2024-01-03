package net.miraistd.testmod.Items.DebugItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.miraistd.testmod.Items.ICustomStaticEntity;
import net.miraistd.testmod.Items.ItemRegisterUtil;

public class DebugItem implements ICustomStaticEntity {
    private static String _name;

    public DebugItem() {
        _name = "debug_item";

    }
    @Override
    public String getName() {
        return _name;

    }
    @Override
    public void registerSelf() {
        ItemRegisterUtil.RegisterItem((Void) -> new Item
                (
                        new Item.Properties()
                                .rarity(Rarity.EPIC)
                                .stacksTo(1)
                                .fireResistant()
                ), this);
    }
}
