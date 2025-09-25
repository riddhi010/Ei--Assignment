package exercise1.creational.singleton;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * CLI to demonstrate Singleton ConfigurationManager.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Configure file logging only
        try {
            // Remove default console handlers
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // Set up file logging
            FileHandler fileHandler = new FileHandler("singleton.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ConfigurationManager config = ConfigurationManager.getInstance();
        boolean running = true;

        try {
            while (running) {
                System.out.println("\n===== Configuration Manager Menu =====");
                System.out.println("1. View Current Configuration");
                System.out.println("2. Update Default Deadline");
                System.out.println("3. Update Max Login Attempts");
                System.out.println("4. Toggle Notifications");
                System.out.println("5. Reload Default Settings");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> {
                        config.viewConfiguration();
                        logger.info("Viewed current configuration");
                    }
                    case "2" -> {
                        System.out.print("Enter new deadline (HH:mm): ");
                        String deadline = scanner.nextLine().trim();
                        config.setDefaultDeadline(deadline);
                        logger.info("Default deadline updated to: " + deadline);
                    }
                    case "3" -> {
                        System.out.print("Enter max login attempts: ");
                        String attemptsStr = scanner.nextLine().trim();
                        try {
                            int attempts = Integer.parseInt(attemptsStr);
                            config.setMaxLoginAttempts(attempts);
                            logger.info("Max login attempts updated to: " + attempts);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31mInvalid number format.\u001B[0m");
                            logger.warning("Invalid number input for max login attempts: " + attemptsStr);
                        }
                    }
                    case "4" -> {
                        System.out.print("Enable notifications? (yes/no): ");
                        String notifInput = scanner.nextLine().trim().toLowerCase();
                        if (notifInput.equals("yes") || notifInput.equals("y")) {
                            config.setNotificationsEnabled(true);
                            logger.info("Notifications enabled");
                        } else if (notifInput.equals("no") || notifInput.equals("n")) {
                            config.setNotificationsEnabled(false);
                            logger.info("Notifications disabled");
                        } else {
                            System.out.println("\u001B[33mInvalid input. Please enter yes or no.\u001B[0m");
                            logger.warning("Invalid input for notifications toggle: " + notifInput);
                        }
                    }
                    case "5" -> {
                        config.loadDefaultSettings();
                        System.out.println("\u001B[32mDefaults reloaded successfully.\u001B[0m");
                        logger.info("Default settings reloaded");
                    }
                    case "6" -> {
                        running = false;
                        System.out.println("Exiting Configuration Manager. Goodbye!");
                        logger.info("Program exited by user");
                    }
                    default -> {
                        System.out.println("\u001B[33mInvalid choice. Try again.\u001B[0m");
                        logger.warning("Invalid menu choice: " + choice);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred: " + e.getMessage(), e);
        } finally {
            scanner.close();
        }
    }
}
