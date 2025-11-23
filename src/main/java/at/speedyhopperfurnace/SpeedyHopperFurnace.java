package at.speedyhopperfurnace;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(SpeedyHopperFurnace.MODID)
public class SpeedyHopperFurnace {
    public static final String MODID = "speedyhopperfurnace";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpeedyHopperFurnace(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Speedy Hopper & Furnace Loaded");
        LOGGER.info("Furnace Speed Multiplier: {}", Config.FURNACE_SPEED_MULTIPLIER.get());
        LOGGER.info("Hopper Cooldown: {}", Config.HOPPER_COOLDOWN.get());
    }
}