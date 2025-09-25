package exercise1.creational.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            FileHandler fileHandler = new FileHandler("factory.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        List<Document> documents = new ArrayList<>();

        try {
            boolean running = true;

            while (running) {
                System.out.println("\n===== Document Generator Menu =====");
                System.out.println("1. Create Document");
                System.out.println("2. View All Documents");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter document type (PDF/Word/Excel): ");
                        String type = scanner.nextLine().trim();
                        System.out.print("Enter document content: ");
                        String content = scanner.nextLine().trim();

                        try {
                            Document document = DocumentFactory.createDocument(type);
                            document.generate(content);
                            documents.add(document);
                            logger.info(type + " document added to the system with content: " + content);
                        } catch (IllegalArgumentException e) {
                            logger.warning("Failed to create document: " + e.getMessage());
                        }
                    }
                    case "2" -> {
                        if (documents.isEmpty()) {
                            System.out.println("No documents created yet.");
                            logger.info("Viewed all documents: none found");
                        } else {
                            System.out.println("\n--- All Documents ---");
                            int count = 1;
                            for (Document doc : documents) {
                                System.out.println(count + ") [" + doc.getType() + "] " + doc.getContent());
                                count++;
                            }
                            System.out.println("-------------------");
                            logger.info("Viewed all documents, total count: " + documents.size());
                        }
                    }
                    case "3" -> {
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
