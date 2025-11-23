package at.speedyhopperfurnace;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(SpeedyHopperFurnace.MODID)
public class SpeedyHopperFurnace {
    public static final String MODID = "speedyhopperfurnace";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpeedyHopperFurnace(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Speedy Hopper & Furnace Loaded");
        LOGGER.info("Furnace Speed Multiplier: {}", Config.FURNACE_SPEED_MULTIPLIER.get());
        LOGGER.info("Hopper Cooldown: {}", Config.HOPPER_COOLDOWN.get());
    }
}