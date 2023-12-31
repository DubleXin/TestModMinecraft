package net.miraistd.testmod.client.gui.core;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.ExtendedPlayer;
import net.miraistd.testmod.client.gui.StatusData;
import net.miraistd.testmod.client.gui.StatusHUD;
import net.miraistd.testmod.player.PlayerManager;

public class HUD {

    private final ExtendedPlayer _owner;
    private final StatusHUD _statusHUD;

    public HUD(ExtendedPlayer owner){
        _owner = owner;
        _statusHUD = new StatusHUD();

        TestMod.ModEventBus.addListener(this::RegisterGuiOverlays);
    }
    public void RegisterGuiOverlays(RegisterGuiOverlaysEvent event){
        event.registerAboveAll("mod_hud", HUD);
    }

    public final IGuiOverlay HUD = ((forgeGui, guiGraphics, v, i, i1) -> {

        final var extendedPlayer =
                PlayerManager.GetExtendedPlayerByName(Minecraft.getInstance().getUser().getName());

        final var font = forgeGui.getFont();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);

        //region STATUS HUD


        //region CORE CACHE

        final var pos = _statusHUD.getPosition();
        final var scale = _statusHUD.getScale();

        //endregion

        //region STATUS DATA CACHE

        assert extendedPlayer != null;
        final var statusData = extendedPlayer.getStatusData();

        final var levelingData = statusData.getLevelingData();
        final var health = StatusData.getHealth();
        final var mana = statusData.getMana();

        //endregion

        // Background
        RenderSystem.setShaderTexture(0, StatusHUD.BACKGROUND);
        guiGraphics.blit(StatusHUD.BACKGROUND,
                (int)(pos.x),
                (int)(pos.y),
                0,
                0,
                (int)(140 * scale),(int)(60 * scale),
                (int)(140 * scale),(int)(60 * scale));

        // HealthBar
        guiGraphics.drawString(font,
                "HP / " + (int)(health * 10),
                (int)(pos.x) + 12,
                (int)(pos.y) + 18,
                0x666666,
                false);

        //endregion
    });
}
