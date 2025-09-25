package exercise1.behavioral.observer;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
            FileHandler fileHandler = new FileHandler("observer.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter assignment name:");
            String assignmentName = scanner.nextLine().trim();
            if (assignmentName.isEmpty()) throw new IllegalArgumentException("Assignment name cannot be empty.");
            logger.info("Assignment created: " + assignmentName);

            System.out.println("Enter initial deadline (HH:mm):");
            String deadline = scanner.nextLine().trim();
            if (!deadline.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) throw new IllegalArgumentException("Invalid time format.");
            logger.info("Initial deadline set: " + deadline);

            Assignment assignment = new Assignment(assignmentName, deadline);

            boolean running = true;

            while (running) {
                System.out.println("\n===== Assignment Management Menu =====");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. Update Assignment Deadline");
                System.out.println("4. View Students");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                String input = scanner.nextLine().trim();
                switch (input) {
                    case "1" -> {
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            logger.warning("Student name cannot be empty.");
                            break;
                        }
                        assignment.addStudent(new ConcreteStudent(name));
                        logger.info("Student added: " + name);
                    }
                    case "2" -> {
                        System.out.print("Enter student name to remove: ");
                        String removeName = scanner.nextLine().trim();
                        assignment.removeStudent(removeName);
                        logger.info("Student removed: " + removeName);
                    }
                    case "3" -> {
                        System.out.print("Enter new deadline (HH:mm): ");
                        String newDeadline = scanner.nextLine().trim();
                        assignment.updateDeadline(newDeadline);
                        logger.info("Assignment deadline updated to: " + newDeadline);
                    }
                    case "4" -> {
                        assignment.viewStudents();
                        logger.info("Viewed students");
                    }
                    case "5" -> {
                        running = false;
                        System.out.println("Exiting program. Goodbye!");
                        logger.info("Program exited by user");
                    }
                    default -> logger.warning("Invalid choice: " + input);
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred: " + e.getMessage(), e);
        } finally {
            scanner.close();
        }
    }
}
