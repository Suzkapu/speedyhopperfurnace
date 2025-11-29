package at.speedyhopperfurnace.mixin;

import at.speedyhopperfurnace.Config;
import at.speedyhopperfurnace.SpeedyHopperFurnace;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceMixin {
    @Inject(method = "getTotalCookTime", at = @At("RETURN"), cancellable = true)
    private static void speedy_getTotalCookTime(Level level, AbstractFurnaceBlockEntity blockEntity, CallbackInfoReturnable<Integer> cir) {
        int multiplier = Config.FURNACE_SPEED_MULTIPLIER.get();

        SpeedyHopperFurnace.LOGGER.debug("FurnaceMixin: Checking cook time. Multiplier: {}", multiplier);

        if (multiplier > 1) {
            int originalTime = cir.getReturnValue();
            int newTime = Math.max(1, originalTime / multiplier);

            SpeedyHopperFurnace.LOGGER.info("FurnaceMixin: Speeding up furnace! Original: {}, New: {}", originalTime, newTime);

            cir.setReturnValue(newTime);
        }
    }
}