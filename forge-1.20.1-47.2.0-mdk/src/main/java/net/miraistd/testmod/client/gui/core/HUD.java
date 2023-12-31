package net.miraistd.testmod.client.gui.core;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.ExtendedPlayer;
import net.miraistd.testmod.client.gui.StatusHUD;
import net.miraistd.testmod.utils.Debug;
import org.joml.Vector2f;

public class HUD {
    public HUD(ExtendedPlayer owner){

        MinecraftForge.EVENT_BUS.register(this);
        Debug.Log(owner.getPlayerSafe(), "registered to event bus");

        //region RENDER
        //endregion

        //region REGISTRATION
        TestMod.ModEventBus.addListener(
                (RegisterGuiOverlaysEvent event) -> event.registerAboveAll("mod_hud", RENDER)
        );

        Debug.Log(owner.getPlayerSafe(), "registered listener");
        //endregion
    }

    public final IGuiOverlay RENDER = ((forgeGui, guiGraphics, v, i, i1) -> {

        final var font = forgeGui.getFont();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        //region STATUS HUD

        final var statusHUD = new StatusHUD();

        //region CORE CACHE

        final var pos = new Vector2f(0,0);//statusHUD.getPosition();
        final var scale = 1f;//statusHUD.getScale();

        //endregion

        //region STATUS DATA CACHE

        //final var statusData = owner.getStatusData();

       // final var levelingData = statusData.getLevelingData();
        final var health = 200; //StatusData.getHealth();
       // final var mana = statusData.getMana();

        //endregion

        // Background
        RenderSystem.setShaderTexture(0, StatusHUD.BACKGROUND);
        guiGraphics.blit(StatusHUD.BACKGROUND,
                (int) (pos.x),
                (int) (pos.y),
                0,
                0,
                (int) (140 * scale), (int) (60 * scale),
                (int) (140 * scale), (int) (60 * scale));

        // HealthBar
        guiGraphics.drawString(font,
                "HP / " + (int) (health * 10),
                (int) (pos.x) + 12,
                (int) (pos.y) + 18,
                0x666666,
                false);

        //endregion
    });

}
