package net.miraistd.testmod.player;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtendedPlayerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ExtendedPlayer> EXTENDED_PLAYER = CapabilityManager.get(new CapabilityToken<ExtendedPlayer>() {});

    private ExtendedPlayer extendedPlayer = null;
    private final LazyOptional<ExtendedPlayer> optional = LazyOptional.of(this::validateExtendedPlayer);
    private ExtendedPlayer validateExtendedPlayer(){
        if(this.extendedPlayer == null)
            this.extendedPlayer = new ExtendedPlayer();

        return this.extendedPlayer;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if(capability == EXTENDED_PLAYER)
            return optional.cast();

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        validateExtendedPlayer().SaveNBTData(nbt);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        validateExtendedPlayer().LoadNBTData(nbt);
    }
}
