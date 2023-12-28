package net.miraistd.testmod.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.gui.font.FontSet;
import net.miraistd.testmod.TestMod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class StatusHUD {
    public static final ResourceLocation BACKGROUND =
            new ResourceLocation(TestMod.MOD_ID, "textures/gui/statusbox.png");
    public static final IGuiOverlay STATUS_BOX = ((forgeGui, guiGraphics, v, i, i1) -> {

        float hudScale = HUDSettings.GetScale();
        Font font = forgeGui.getFont();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        guiGraphics.blit(BACKGROUND,
                5, 5,
                0,0,
                (int)(140 * hudScale),(int)(60 * hudScale),
                (int)(140 * hudScale),(int)(60 * hudScale));

        guiGraphics.drawString(font,
                "HP / " + (int)(StatusData.get_health() * 10.0f),
                12, 18,
                0x666666, false);
    });
}