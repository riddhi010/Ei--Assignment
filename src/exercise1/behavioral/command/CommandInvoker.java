package exercise1.behavioral.command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommandInvoker {
    private static final Logger logger = Logger.getLogger(CommandInvoker.class.getName());
    private final List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        try {
            command.execute();
            commandHistory.add(command);
        } catch (Exception e) {
            System.out.println("\u001B[31mError: " + e.getMessage() + "\u001B[0m");
            logger.warning("Command execution failed: " + e.getMessage());
        }
    }

    public void showCommandHistory() {
        System.out.println("\nCommand History (" + commandHistory.size() + " commands):");
        for (int i = 0; i < commandHistory.size(); i++) {
            System.out.println((i + 1) + ". " + commandHistory.get(i).getClass().getSimpleName());
        }
    }
}
