package at.speedyhopperfurnace.mixin;

import at.speedyhopperfurnace.Config;
import at.speedyhopperfurnace.SpeedyHopperFurnace;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperMixin {

    @Inject(method = "setCooldown", at = @At("HEAD"))
    private void speedy_logCooldown(int i, CallbackInfo ci) {
        if (i > 0) {
            SpeedyHopperFurnace.LOGGER.debug("HopperMixin: setCooldown called with original value: {}", i); // Changed to debug to reduce spam
        }
    }

    @ModifyVariable(method = "setCooldown", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private int speedy_modifyCooldown(int originalCooldown) {
        int targetCooldown = Config.HOPPER_COOLDOWN.get();

        if (originalCooldown > 0 && originalCooldown > targetCooldown) {
            SpeedyHopperFurnace.LOGGER.info("HopperMixin: Changing cooldown from {} to configured {}", originalCooldown, targetCooldown);
            return targetCooldown;
        }

        return originalCooldown;
    }
}