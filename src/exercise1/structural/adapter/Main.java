package exercise1.structural.adapter;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
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
                    case "1":
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

                        break;

                    case "2":
                        running = false;
                        System.out.println("Exiting program. Goodbye!");
                        break;

                    default:
                        logger.warning("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred", e);
        } finally {
            scanner.close();
        }
    }
}
