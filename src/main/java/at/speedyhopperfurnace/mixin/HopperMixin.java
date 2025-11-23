package at.speedyhopperfurnace.mixin;

import at.speedyhopperfurnace.Config;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HopperBlockEntity.class)
public class HopperMixin {
    @ModifyVariable(method = "setCooldown", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private int speedy_setCooldown(int originalCooldown) {
        // Force the cooldown to the configured value
        return Config.HOPPER_COOLDOWN.get();
    }
}