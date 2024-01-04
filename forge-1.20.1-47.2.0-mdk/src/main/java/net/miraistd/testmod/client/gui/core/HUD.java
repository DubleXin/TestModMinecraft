package net.miraistd.testmod.client.gui.core;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.miraistd.testmod.client.gui.StatusHUD;
import net.miraistd.testmod.client.gui.VanillaResourcesHUD;
import net.miraistd.testmod.player.ClientExtendedPlayerData;
import org.joml.Vector2f;

public class HUD {
    public static final Font FONT = new Font (
            s -> new FontSet(
                    Minecraft.getInstance().textureManager,
                    new ResourceLocation("font/arian_font")
            ), true
    );
    public static final IGuiOverlay RENDER = ((forgeGui, guiGraphics, v, i, i1) -> {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        //region STATUS HUD

            final var statusHUD = new StatusHUD();

            //region CORE CACHE

            var pos = statusHUD.getPosition();
            var scale = statusHUD.getScale();

            //endregion

            //region STATUS DATA CACHE

            final var extendedPlayer = ClientExtendedPlayerData.getData();
            if(extendedPlayer != null){
                final var statusData = extendedPlayer.getStatusData();

                final var healthPercentage =  ClientExtendedPlayerData.getHealth() / 20f;
                final var manaPercentage = extendedPlayer.getMana() / 1f;

            //endregion

                //region Background
                RenderSystem.setShaderTexture(0, StatusHUD.BACKGROUND);
                guiGraphics.blit(StatusHUD.BACKGROUND,
                        (int) (pos.x),
                        (int) (pos.y),
                        0,
                        0,
                        (int) (110 * scale), (int) (67.5f * scale),
                        (int) (110 * scale), (int) (67.5f * scale));
                //endregion

                //region Name/Job

                guiGraphics.drawString(
                        forgeGui.getFont(),
                        ClientExtendedPlayerData.getName(),
                        (int) pos.x + 6,
                        (int) pos.y + 9,
                        0x666666,
                        false);

                guiGraphics.drawString(
                        forgeGui.getFont(),
                        statusData.getJob().toString(),
                        (int) pos.x + 6,
                        (int) pos.y + 15,
                        0x666666,
                        false);
                //endregion

                //region Health

                drawBar(false, guiGraphics,
                        healthPercentage, 71,
                        2, 4.5f, new Vector2f(18, 25),
                        statusHUD);

                guiGraphics.drawString(
                        forgeGui.getFont(),
                        ((int)(healthPercentage * 100) + "%"),
                        (int) pos.x + 23 + 71,
                        (int) pos.y + 25,
                        0x333333,
                        false);

            //endregion

                //region Mana

                drawBar(false, guiGraphics,
                        manaPercentage, 71,
                        2, 4.5f, new Vector2f(18, 32),
                        statusHUD);

                //TODO make font and re-implement
                guiGraphics.drawString(
                        forgeGui.getFont(),
                        ((int)(manaPercentage * 100) + "%"),
                        (int) pos.x + 23 + 71,
                        (int) pos.y + 32,
                        0x333333,
                        false);
                //endregion

                //region Level

                drawBar(false, guiGraphics,
                        1, 54,
                        2, 3, new Vector2f(36, 43),
                        statusHUD);

                //TODO make font and re-implement
                guiGraphics.drawString(
                        forgeGui.getFont(),
                        "100%",
                        (int) pos.x + 38 + 54,
                        (int) pos.y + 43,
                        0x666666,
                        false);

                //TODO make font and re-implement
                guiGraphics.drawString(
                        forgeGui.getFont(),
                        "" + (statusData.getLevel()),
                        (int) pos.x + 26,
                        (int) pos.y + 43,
                        0x666666,
                        false);
                //endregion

                //region JobLevel

                drawBar(false, guiGraphics,
                        1, 54,
                        2, 3, new Vector2f(36, 49),
                        statusHUD);

                //TODO make font and re-implement
                guiGraphics.drawString(
                        forgeGui.getFont(),
                        "100%",
                        (int) pos.x + 38 + 54,
                        (int) pos.y + 49,
                        0x666666,
                        false);

                //TODO make font and re-implement
                guiGraphics.drawString(
                        forgeGui.getFont(),
                        "" + (statusData.getJobLevel()),
                        (int) pos.x + 26,
                        (int) pos.y + 49,
                        0x666666,
                        false);
                //endregion

            }
            //endregion

        //endregion

        //region VANILLA RESOURCES HUD

            final var vanillaResourcesHUD = new VanillaResourcesHUD();

            //region CORE CACHE

            pos = vanillaResourcesHUD.getPosition();
            scale = vanillaResourcesHUD.getScale();

            //endregion

            //region VANILLA DATA CACHE

            final var foodPercentage =  ClientExtendedPlayerData.getHunger() / 20f;
            final var expPercentage = ClientExtendedPlayerData.getMinecraftExp();
            final var minecraftLevel = ClientExtendedPlayerData.getMinecraftLevel();

            //endregion

                //region Background
                RenderSystem.setShaderTexture(0, VanillaResourcesHUD.BACKGROUND);
                guiGraphics.blit(VanillaResourcesHUD.BACKGROUND,
                        (int) (pos.x),
                        (int) (pos.y),
                        0,
                        0,
                        (int) (23 * scale), (int) (56 * scale),
                        (int) (23 * scale), (int) (56 * scale));
                //endregion

                //region Food

                //Start
                RenderSystem.setShaderColor(1.0f, 0.5f, 0.2f, 1.0f);
                drawBar(true, guiGraphics,
                        foodPercentage, 22,
                        3, 2.5f, new Vector2f(6, 17),
                        vanillaResourcesHUD);
                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

                //endregion

                //region Experience

                //Start
                RenderSystem.setShaderColor(0.7f, 1.0f, 0.2f, 1.0f);
                drawBar(true, guiGraphics,
                        expPercentage, 22,
                        3, 2.5f, new Vector2f(14, 17),
                        vanillaResourcesHUD);
                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

                //TODO make font and re-implement
                guiGraphics.drawString(
                        forgeGui.getFont(),
                        "" + (minecraftLevel + 1),
                        (int) pos.x + 14,
                        (int) pos.y + 47,
                        0x666666,
                        false);
                //endregion

            //endregion

        //endregion
    });

    private static void drawBar(boolean isVertical, GuiGraphics guiGraphics,
                                float value, float maxValue, float maxLength,
                                float width, float height, Vector2f offset,
                                HUDTransformWithBars transform) {

        drawBar(isVertical, guiGraphics,
                value / maxValue,
                maxLength, width, height,
                offset, transform);
    }
    private static void drawBar(boolean isVertical, GuiGraphics guiGraphics,
                                float percentage, float maxLength,
                                float width, float height, Vector2f offset,
                                HUDTransformWithBars transform) {

        float scale = transform.getScale();
        Vector2f pos = transform.getPosition();
        Vector2f offsetPos = new Vector2f(pos.x + offset.x, pos.y + offset.y);
        int barLength = (int) (percentage * (maxLength * scale));

        //Start
        if(percentage > 0.02f) {
            guiGraphics.blit(
                    transform.BAR_START,
                    (int) offsetPos.x, (int) offsetPos.y,
                    0, 0,
                    (int) (width * scale), (int) (height * scale),
                    (int) (width * scale), (int) (height * scale));
            //Filament
            guiGraphics.blit(
                    transform.BAR_FILAMENT,
                    (int) offsetPos.x + (isVertical ? 0 : 2),
                    (int) offsetPos.y + (isVertical ? 2 : 0),
                    0, 0,
                    isVertical ? (int) (width * scale) : barLength, isVertical ? barLength : (int) (height * scale),
                    isVertical ? (int) (width * scale) : barLength, isVertical ? barLength : (int) (height * scale));
        }
        //End
        if(percentage > 0.05f)
            guiGraphics.blit(
                    transform.BAR_END,
                    (int) offsetPos.x + (isVertical? 0 : barLength + 2),
                    (int) offsetPos.y + (isVertical? barLength + 2 : 0),
                    0, 0,
                    (int)(width * scale), (int) (height * scale),
                    (int)(width * scale), (int) (height * scale));

    }
}
