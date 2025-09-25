package exercise1.structural.adapter;

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
            FileHandler fileHandler = new FileHandler("adapter_report.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        try {
            boolean running = true;

            while (running) {
                System.out.println("\n===== Adapter Pattern Report Menu =====");
                System.out.println("1. Generate CSV Report");
                System.out.println("2. Exit");
                System.out.print("Enter choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter report title: ");
                        String title = scanner.nextLine().trim();
                        if (title.isEmpty()) {
                            logger.warning("Report title cannot be empty.");
                            break;
                        }

                        System.out.print("Enter report content: ");
                        String content = scanner.nextLine().trim();
                        if (content.isEmpty()) {
                            logger.warning("Report content cannot be empty.");
                            break;
                        }

                        // Adapter in action
                        CsvReport csvReport = new CsvReport();
                        ReportGenerator report = new CsvReportAdapter(csvReport);
                        report.generateReport(title, content);
                        logger.info("CSV report generated with title: " + title);
                    }
                    case "2" -> {
                        running = false;
                        System.out.println("Exiting program. Goodbye!");
                        logger.info("Program exited by user");
                    }
                    default -> logger.warning("Invalid menu choice: " + choice);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred", e);
        } finally {
            scanner.close();
        }
    }
}
