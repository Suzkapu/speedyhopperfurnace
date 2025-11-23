package at.speedyhopperfurnace.mixin;

import at.speedyhopperfurnace.Config;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperMixin {
    // Shadow the private cooldown field so we can write to it
    @Shadow
    private int cooldownTime;

    @Inject(method = "setCooldown", at = @At("HEAD"), cancellable = true)
    private void speedy_setCooldown(int i, CallbackInfo ci) {
        // Force the cooldown to be the Config value (e.g., 1 tick)
        // This overrides whatever vanilla tried to set (usually 8)
        this.cooldownTime = Config.HOPPER_COOLDOWN.get();

        // Cancel the original method so vanilla logic doesn't overwrite us
        ci.cancel();
    }
}