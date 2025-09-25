package exercise1.behavioral.command;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;

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
            FileHandler fileHandler = new FileHandler("command.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Classroom classroom = new Classroom();
        CommandInvoker invoker = new CommandInvoker();

        boolean running = true;

        try {
            while (running) {
                System.out.println("\n===== Classroom Command Menu =====");
                System.out.println("1. Start Lecture");
                System.out.println("2. End Lecture");
                System.out.println("3. Take Attendance");
                System.out.println("4. View Command History");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        invoker.executeCommand(new StartLectureCommand(classroom));
                        logger.info("StartLectureCommand executed");
                        break;
                    case "2":
                        invoker.executeCommand(new EndLectureCommand(classroom));
                        logger.info("EndLectureCommand executed");
                        break;
                    case "3":
                        invoker.executeCommand(new TakeAttendanceCommand(classroom));
                        logger.info("TakeAttendanceCommand executed");
                        break;
                    case "4":
                        invoker.showCommandHistory();
                        logger.info("Command history viewed");
                        break;
                    case "5":
                        running = false;
                        System.out.println("Exiting program. Goodbye!");
                        logger.info("Program exited by user");
                        break;
                    default:
                        System.out.println("\u001B[33mInvalid choice. Try again.\u001B[0m");
                        logger.warning("Invalid menu choice: " + choice);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
        } finally {
            scanner.close();
        }
    }
}
