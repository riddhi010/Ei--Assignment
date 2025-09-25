package exercise1.behavioral.observer;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter assignment name:");
            String assignmentName = scanner.nextLine().trim();
            if (assignmentName.isEmpty()) throw new IllegalArgumentException("Assignment name cannot be empty.");

            System.out.println("Enter initial deadline (HH:mm):");
            String deadline = scanner.nextLine().trim();
            if (!deadline.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) throw new IllegalArgumentException("Invalid time format.");

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
                    }
                    case "2" -> {
                        System.out.print("Enter student name to remove: ");
                        String removeName = scanner.nextLine().trim();
                        assignment.removeStudent(removeName);
                    }
                    case "3" -> {
                        System.out.print("Enter new deadline (HH:mm): ");
                        String newDeadline = scanner.nextLine().trim();
                        assignment.updateDeadline(newDeadline);
                    }
                    case "4" -> assignment.viewStudents();
                    case "5" -> {
                        running = false;
                        System.out.println("Exiting program. Goodbye!");
                    }
                    default -> logger.warning("Invalid choice. Please try again.");
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred: " + e.getMessage(), e);
        } finally {
            scanner.close();
        }
    }
}
