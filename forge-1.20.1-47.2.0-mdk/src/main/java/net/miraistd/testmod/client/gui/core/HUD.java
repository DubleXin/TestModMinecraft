package net.miraistd.testmod.client.gui.core;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.miraistd.testmod.client.gui.StatusHUD;
import net.miraistd.testmod.player.ClientExtendedPlayerData;

public class HUD {
    public static final IGuiOverlay RENDER = ((forgeGui, guiGraphics, v, i, i1) -> {

        //region VANILLA BLOCK
        //endregion

        final var font = forgeGui.getFont();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        //region STATUS HUD

            final var statusHUD = new StatusHUD();

            //region CORE CACHE

            final var pos = statusHUD.getPosition();
            final var scale = statusHUD.getScale();

            //endregion

            //region STATUS DATA CACHE

            final var extendedPlayer = ClientExtendedPlayerData.getData();
            if(extendedPlayer != null){
                final var statusData = extendedPlayer.getStatusData();

                final var health =  ClientExtendedPlayerData.getHealth();
                final var maxHealth = 20;
                final var mana = extendedPlayer.getMana();
                final var maxMana = 1;

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

                //region HealthBar

                //Start
                RenderSystem.setShaderTexture(0, StatusHUD.BAR_START);
                guiGraphics.blit(
                        StatusHUD.BAR_START,
                        (int) pos.x + 18, (int) pos.y + 25,
                        0, 0,
                        2, (int) (4.5f * scale),
                        2, (int) (4.5f * scale));

                //Filament
                RenderSystem.setShaderTexture(0, StatusHUD.BAR_FILAMENT);
                int healthBarWidth = (int) ((health / maxHealth) * (71 * scale));
                guiGraphics.blit(
                        StatusHUD.BAR_FILAMENT,
                        (int) pos.x + 20, (int) pos.y + 25,
                        0, 0,
                        healthBarWidth, (int) (4.5f * scale),
                        healthBarWidth, (int) (4.5f * scale));

                //End
                RenderSystem.setShaderTexture(0, StatusHUD.BAR_END);
                guiGraphics.blit(
                        StatusHUD.BAR_END,
                        (int) pos.x + 20 + healthBarWidth, (int) pos.y + 25,
                        0, 0,
                        2, (int) (4.5f * scale),
                        2, (int) (4.5f * scale));

                //TODO make font and re-implement
                /*
                guiGraphics.drawString(
                        font,
                        ((int) (health / maxHealth * 100) + "%"),
                        (int) pos.x + 23 + healthBarWidth,
                        (int) pos.y + 25,
                        0x666666,
                        false);
                 */
            //endregion

                //region ManaBar

                //Start
                RenderSystem.setShaderTexture(0, StatusHUD.BAR_START);
                guiGraphics.blit(
                        StatusHUD.BAR_START,
                        (int) pos.x + 18, (int) pos.y + 32,
                        0, 0,
                        2, (int) (4.5f * scale),
                        2, (int) (4.5f * scale));

                //Filament
                RenderSystem.setShaderTexture(0, StatusHUD.BAR_FILAMENT);
                int manaBarWidth = (int) ((mana / maxMana) * (71 * scale));
                guiGraphics.blit(
                        StatusHUD.BAR_FILAMENT,
                        (int) pos.x + 20, (int) pos.y + 32,
                        0, 0,
                        manaBarWidth, (int) (4.5f * scale),
                        manaBarWidth, (int) (4.5f * scale));

                //End
                RenderSystem.setShaderTexture(0, StatusHUD.BAR_END);
                guiGraphics.blit(
                        StatusHUD.BAR_END,
                        (int) pos.x + 20 + manaBarWidth, (int) pos.y + 32,
                        0, 0,
                        2, (int) (4.5f * scale),
                        2, (int) (4.5f * scale));

                //TODO make font and re-implement
                /*
                guiGraphics.drawString(
                        font,
                        ((int) (health / maxHealth * 100) + "%"),
                        (int) pos.x + 23 + healthBarWidth,
                        (int) pos.y + 25,
                        0x666666,
                        false);
                 */
                //endregion

            }
            //endregion

        //endregion
    });
}
