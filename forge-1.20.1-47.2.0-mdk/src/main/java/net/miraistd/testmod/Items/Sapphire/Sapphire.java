package net.miraistd.testmod.Items.Sapphire;

import lombok.Getter;
import net.minecraft.world.item.Item;
import net.miraistd.testmod.Items.ICustomItem;
import net.miraistd.testmod.Items.ItemRegisterUtil;

public class Sapphire implements ICustomItem {
    @Getter
    private String Name;
    public Sapphire(){
        Name = "sapphire";
    }
    @Override
    public void registerSelf() {
        ItemRegisterUtil.RegisterItem(
           (Void) -> new Item(new Item.Properties()), this);
    }
}
