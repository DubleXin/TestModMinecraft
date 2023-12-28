package net.miraistd.testmod.client;

import net.miraistd.testmod.TestMod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class StatusHUD {
    public static final ResourceLocation BACKGROUND =
            new ResourceLocation(TestMod.MOD_ID, "testmod/textures/gui/statusbox.png");
    public static final IGuiOverlay STATUS_BOX = ((forgeGui, guiGraphics, v, i, i1) -> {

        int x = guiGraphics.guiWidth() /2;
        int y = guiGraphics.guiHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND);
    });
}