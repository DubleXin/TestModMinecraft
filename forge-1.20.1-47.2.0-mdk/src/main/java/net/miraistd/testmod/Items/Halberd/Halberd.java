package net.miraistd.testmod.Items.Halberd;

import lombok.Getter;
import net.minecraft.world.item.Item;
import net.miraistd.testmod.Items.ICustomStaticEntity;
import net.miraistd.testmod.Items.ItemRegisterUtil;

@Getter
public class Halberd implements ICustomStaticEntity {
    public final String Name;
    public Halberd(){
        Name = "halberd";
    }
    @Override
    public void registerSelf() {
        ItemRegisterUtil.RegisterItem((Void) -> new Item(new Item.Properties()) ,this);
    }
}
