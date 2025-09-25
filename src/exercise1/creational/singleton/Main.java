package exercise1.creational.singleton;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CLI to demonstrate Singleton ConfigurationManager.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
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
                    case "1":
                        config.viewConfiguration();
                        break;

                    case "2":
                        System.out.print("Enter new deadline (HH:mm): ");
                        String deadline = scanner.nextLine().trim();
                        config.setDefaultDeadline(deadline);
                        break;

                    case "3":
                        System.out.print("Enter max login attempts: ");
                        String attemptsStr = scanner.nextLine().trim();
                        try {
                            int attempts = Integer.parseInt(attemptsStr);
                            config.setMaxLoginAttempts(attempts);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31mInvalid number format.\u001B[0m");
                            logger.warning("Invalid number input for max login attempts.");
                        }
                        break;

                    case "4":
                        System.out.print("Enable notifications? (yes/no): ");
                        String notifInput = scanner.nextLine().trim().toLowerCase();
                        if (notifInput.equals("yes") || notifInput.equals("y")) {
                            config.setNotificationsEnabled(true);
                        } else if (notifInput.equals("no") || notifInput.equals("n")) {
                            config.setNotificationsEnabled(false);
                        } else {
                            System.out.println("\u001B[33mInvalid input. Please enter yes or no.\u001B[0m");
                        }
                        break;

                    case "5":
                        config.loadDefaultSettings();
                        System.out.println("\u001B[32mDefaults reloaded successfully.\u001B[0m");
                        break;

                    case "6":
                        running = false;
                        System.out.println("Exiting Configuration Manager. Goodbye!");
                        break;

                    default:
                        System.out.println("\u001B[33mInvalid choice. Try again.\u001B[0m");
                        logger.warning("Invalid menu choice: " + choice);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred: " + e.getMessage(), e);
        } finally {
            scanner.close();
        }
    }
}
