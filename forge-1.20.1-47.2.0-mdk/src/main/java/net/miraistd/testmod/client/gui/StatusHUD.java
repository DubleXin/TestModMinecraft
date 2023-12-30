package net.miraistd.testmod.client.gui;

import net.minecraft.resources.ResourceLocation;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.core.HUDTransform;
import org.joml.Vector2f;

public class StatusHUD extends HUDTransform {
    public static final ResourceLocation BACKGROUND =
            new ResourceLocation(TestMod.MOD_ID, "textures/gui/statusbox.png");

    public StatusHUD(){
        Scale = 1.0f;
        Position =new Vector2f(5,5);
    }
}

