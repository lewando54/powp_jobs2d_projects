package edu.kis.powp.jobs2d.command.manager;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.importer.CommandImportParser;
import edu.kis.powp.jobs2d.command.importer.CommandImportResult;
import edu.kis.powp.jobs2d.visitor.CommandCounterVisitor;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

/**
 * Driver command Manager.
 */
public class CommandManager {
    private DriverCommand currentCommand = null;
    private List<Subscriber> observersStore = null;

    private final Publisher changePublisher = new Publisher();

    /**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        changePublisher.notifyObservers();
    }

    /**
     * Set current command.
     *
     * @param commandList list of commands representing a compound command.
     * @param name        name of the command.
     */
    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        CompoundCommand compoundCommand = CompoundCommand.fromListOfCommands(commandList, name);
        setCurrentCommand(compoundCommand);
    }

    public synchronized void importCurrentCommandFromText(String text, CommandImportParser parser) {
        CommandImportResult result = parser.parse(text);
        setCurrentCommand(result.getCommands(), result.getName());
    }

    /**
     * Return current command.
     *
     * @return Current command.
     */
    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
        changePublisher.notifyObservers();
    }

    public synchronized void runCurrentCommand(Job2dDriver driver) {
        if (currentCommand != null) {
            currentCommand.execute(driver);
        }
    }

    public synchronized void addSubscriber(Subscriber subscriber) {
        changePublisher.addSubscriber(subscriber);
    }

    public synchronized List<Subscriber> getSubscribers() {
        return changePublisher.getSubscribers();
    }

    public synchronized void deleteObservers() {
        observersStore = new ArrayList<>(changePublisher.getSubscribers());
        changePublisher.clearObservers();
    }

    public synchronized void resetObservers() {
        if (observersStore != null) {
            for (Subscriber subscriber : observersStore) {
                changePublisher.addSubscriber(subscriber);
            }
            observersStore = null;
        }
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else {
            CommandCounterVisitor.CommandStats stats = CommandCounterVisitor.countCommands(getCurrentCommand());
            StringBuilder sb = new StringBuilder(getCurrentCommand().toString());
            sb.append("\n\nStats:\n");
            sb.append("Total commands: ").append(stats.getCount()).append("\n");
            sb.append("OperateTo count: ").append(stats.getOperateToCount()).append("\n");
            sb.append("SetPosition count: ").append(stats.getSetPositionCount()).append("\n");
            return sb.toString();
        }
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }
}