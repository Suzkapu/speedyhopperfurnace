package at.speedyhopperfurnace;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue FURNACE_SPEED_MULTIPLIER = BUILDER
            .comment("The speed multiplier for the furnace. 1 is normal speed, 2 is double speed, etc.")
            .defineInRange("furnaceSpeedMultiplier", 10, 1, 100);

    public static final ModConfigSpec.IntValue HOPPER_COOLDOWN = BUILDER
            .comment("The cooldown in ticks for hopper transfer. Lower is faster. Default vanilla is 8.")
            .defineInRange("hopperCooldown", 1, 1, 20);

    public static final ModConfigSpec SPEC = BUILDER.build();
}