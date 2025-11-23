package at.speedyhopperfurnace.mixin;

import at.speedyhopperfurnace.Config;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceMixin {
    @Inject(method = "getTotalCookTime", at = @At("RETURN"), cancellable = true)
    private static void speedy_getTotalCookTime(ServerLevel level, AbstractFurnaceBlockEntity furnace, CallbackInfoReturnable<Integer> cir) {
        int multiplier = Config.FURNACE_SPEED_MULTIPLIER.get();
        if (multiplier > 1) {
            int originalTime = cir.getReturnValue();
            // Ensure we don't divide by zero or return 0 (which might cause instant completion issues or loops)
            cir.setReturnValue(Math.max(1, originalTime / multiplier));
        }
    }
}