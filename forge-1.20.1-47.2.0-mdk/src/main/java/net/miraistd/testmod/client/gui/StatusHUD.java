package net.miraistd.testmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.core.HUDTransform;
import org.joml.Vector2f;

public class StatusHUD extends HUDTransform {
    public static final ResourceLocation BACKGROUND =
            new ResourceLocation(TestMod.MOD_ID, "textures/gui/statusbox.png");
    public static final IGuiOverlay STATUS_HUD = ((forgeGui, guiGraphics, v, i, i1) -> {

        //region CORE CACHE

        var pos = getPosition();
        var scale = getScale();
        var font = forgeGui.getFont();

        //endregion

        //region STATUS DATA CACHE

        var levelingData = StatusData.get_levelingData();
        var health = StatusData.get_health();
        var mana = StatusData.get_mana();

        //endregion

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        guiGraphics.blit(BACKGROUND,
                (int)(pos.x + 5), (int)(pos.y + 5),
                0,0,
                (int)(140 * scale),(int)(60 * scale),
                (int)(140 * scale),(int)(60 * scale));

        guiGraphics.drawString(font,
                "HP / " + (int)(health * 10),
                12, 18,
                0x666666, false);
    });

    public StatusHUD(){
        setScale(1.0f);
        setPosition(new Vector2f(0,0));
    }
}