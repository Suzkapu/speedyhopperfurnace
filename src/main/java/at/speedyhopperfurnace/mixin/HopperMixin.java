package at.speedyhopperfurnace.mixin;

import at.speedyhopperfurnace.Config;
import at.speedyhopperfurnace.SpeedyHopperFurnace;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HopperBlockEntity.class)
public class HopperMixin {
    @ModifyVariable(method = "setCooldown", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private int speedy_modifyCooldown(int originalCooldown) {
        int targetCooldown = Config.HOPPER_COOLDOWN.get();

        if (originalCooldown > targetCooldown) {
            SpeedyHopperFurnace.LOGGER.info("HopperMixin: Reducing cooldown from {} to {}", originalCooldown, targetCooldown);
            return targetCooldown;
        }

        return originalCooldown;
    }
}