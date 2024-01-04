package net.miraistd.testmod.client.gui;

import net.minecraft.resources.ResourceLocation;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.core.HUDTransformWithBars;
import org.joml.Vector2f;

public class VanillaResourcesHUD extends HUDTransformWithBars {
    public static final ResourceLocation BACKGROUND =
            new ResourceLocation(TestMod.MOD_ID, "textures/gui/vanilla_resources_box.png");

    public VanillaResourcesHUD(){
        BAR_START = new ResourceLocation(TestMod.MOD_ID, "textures/gui/bar_start_vertical.png");
        BAR_FILAMENT = new ResourceLocation(TestMod.MOD_ID, "textures/gui/bar_filament_vertical.png");
        BAR_END = new ResourceLocation(TestMod.MOD_ID, "textures/gui/bar_end_vertical.png");

        Scale = 1.0f;
        Position =new Vector2f(117,5);
    }
}
