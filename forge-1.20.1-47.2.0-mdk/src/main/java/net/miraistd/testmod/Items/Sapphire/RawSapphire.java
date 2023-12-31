package net.miraistd.testmod.Items.Sapphire;

import lombok.Getter;
import net.minecraft.world.item.Item;
import net.miraistd.testmod.Items.ICustomStaticEntity;
import net.miraistd.testmod.Items.ItemRegisterUtil;

public class RawSapphire implements ICustomStaticEntity {
    @Getter
    private String Name;
    public RawSapphire(){
        Name = "raw_sapphire";
    }
    @Override
    public void registerSelf() {
        ItemRegisterUtil.RegisterItem(
           (Void) -> new Item(new Item.Properties()), this);
    }
}
