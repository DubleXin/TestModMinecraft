package net.miraistd.testmod.client.gui.core;

import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector2f;

@Getter
public class HUDTransformWithBars extends HUDTransform {
    protected ResourceLocation BAR_START;
    protected ResourceLocation BAR_FILAMENT;
    protected ResourceLocation BAR_END;

    public HUDTransformWithBars(){
        Scale = 1.0f;
        Position =new Vector2f(0,0);
    }
}
