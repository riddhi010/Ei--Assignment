package exercise1.creational.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
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
                    case "1":
                        System.out.print("Enter document type (PDF/Word/Excel): ");
                        String type = scanner.nextLine().trim();
                        System.out.print("Enter document content: ");
                        String content = scanner.nextLine().trim();

                        try {
                            Document document = DocumentFactory.createDocument(type);
                            document.generate(content);
                            documents.add(document);
                            logger.info(type + " document added to the system.");
                        } catch (IllegalArgumentException e) {
                            logger.warning(e.getMessage());
                        }
                        break;

                    case "2":
                        if (documents.isEmpty()) {
                            System.out.println("No documents created yet.");
                        } else {
                            System.out.println("\n--- All Documents ---");
                            int count = 1;
                            for (Document doc : documents) {
                                System.out.println(count + ") [" + doc.getType() + "] " + doc.getContent());
                                count++;
                            }
                            System.out.println("-------------------");
                        }
                        break;

                    case "3":
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
