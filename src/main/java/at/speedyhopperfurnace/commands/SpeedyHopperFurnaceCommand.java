package at.speedyhopperfurnace.commands;

import at.speedyhopperfurnace.Config;
import at.speedyhopperfurnace.SpeedyHopperFurnace;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SpeedyHopperFurnaceCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(SpeedyHopperFurnace.MODID)
                .requires(cs -> cs.hasPermission(2)).then(Commands.literal("furnaceSpeed")
                        .executes(context -> getFurnaceSpeed(context.getSource()))
                        .then(Commands.argument("multiplier", IntegerArgumentType.integer(1, 100))
                                .executes(context -> setFurnaceSpeed(context.getSource(), IntegerArgumentType.getInteger(context, "multiplier")))))
                .then(Commands.literal("hopperCooldown").executes(context -> getHopperCooldown(context.getSource()))
                        .then(Commands.argument("cooldown", IntegerArgumentType.integer(1, 20))
                                .executes(context -> setHopperCooldown(context.getSource(), IntegerArgumentType.getInteger(context, "cooldown")))))
                .then(Commands.literal("reload").executes(context -> reloadConfig(context.getSource())))
                .executes(context -> showHelp(context.getSource())));
    }

    private static int getFurnaceSpeed(CommandSourceStack source) {
        int speed = Config.FURNACE_SPEED_MULTIPLIER.get();
        source.sendSuccess(() -> Component.literal("Current Furnace Speed Multiplier: " + speed), false);
        return speed;
    }

    private static int setFurnaceSpeed(CommandSourceStack source, int multiplier) {
        Config.FURNACE_SPEED_MULTIPLIER.set(multiplier);
        Config.SPEC.save();
        source.sendSuccess(() -> Component.literal("Furnace Speed Multiplier set to: " + multiplier), false);
        return multiplier;
    }

    private static int getHopperCooldown(CommandSourceStack source) {
        int cooldown = Config.HOPPER_COOLDOWN.get();
        source.sendSuccess(() -> Component.literal("Current Hopper Cooldown: " + cooldown + " ticks"), false);
        return cooldown;
    }

    private static int setHopperCooldown(CommandSourceStack source, int cooldown) {
        Config.HOPPER_COOLDOWN.set(cooldown);
        Config.SPEC.save();
        source.sendSuccess(() -> Component.literal("Hopper Cooldown set to: " + cooldown + " ticks"), false);
        return cooldown;
    }

    private static int reloadConfig(CommandSourceStack source) {
        Config.SPEC.save();
        source.sendSuccess(() -> Component.literal("Speedy Hopper & Furnace config reloaded."), false);
        SpeedyHopperFurnace.LOGGER.info("Config reloaded by command. Furnace Speed Multiplier: {}, Hopper Cooldown: {}", Config.FURNACE_SPEED_MULTIPLIER.get(), Config.HOPPER_COOLDOWN.get());
        return 1;
    }

    private static int showHelp(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal("--- Speedy Hopper & Furnace Commands ---"), false);
        source.sendSuccess(() -> Component.literal("/" + SpeedyHopperFurnace.MODID + " furnaceSpeed [multiplier] - View or set furnace speed."), false);
        source.sendSuccess(() -> Component.literal("/" + SpeedyHopperFurnace.MODID + " hopperCooldown [cooldown] - View or set hopper cooldown."), false);
        source.sendSuccess(() -> Component.literal("/" + SpeedyHopperFurnace.MODID + " reload - Reloads the mod configuration."), false);
        return 1;
    }
}