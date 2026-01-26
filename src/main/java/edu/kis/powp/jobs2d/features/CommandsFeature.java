package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentFlippedCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentRotatedCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentScaledDownCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentScaledUpCommandOptionListener;

public class CommandsFeature {

    private static CommandManager commandManager;

    public static void setupCommandManager() {
        commandManager = new CommandManager();

        LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
        commandManager.getChangePublisher().addSubscriber(loggerObserver);
    }

    /**
     * Set up the "Commands" menu in the application GUI.
     */
    public static void setupCommandsMenu(Application application) {
        application.addComponentMenu(CommandsFeature.class, "Commands");

        application.addComponentMenuElement(CommandsFeature.class, "Run command",
            new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

        application.addComponentMenuElement(CommandsFeature.class, "Flip",
            new SelectRunCurrentFlippedCommandOptionListener());

        application.addComponentMenuElement(CommandsFeature.class, "Rotate 90",
            new SelectRunCurrentRotatedCommandOptionListener());

        application.addComponentMenuElement(CommandsFeature.class, "Scale 2.0",
            new SelectRunCurrentScaledUpCommandOptionListener());

        application.addComponentMenuElement(CommandsFeature.class, "Scale 0.5",
            new SelectRunCurrentScaledDownCommandOptionListener());
    }

    /**
     * Get manager of application driver command.
     * 
     * @return plotterCommandManager.
     */
    public static CommandManager getDriverCommandManager() {
        return commandManager;
    }
}
