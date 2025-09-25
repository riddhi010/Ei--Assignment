package exercise2;

import exercise2.manager.VirtualClassroomManager;

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
            FileHandler fileHandler = new FileHandler("virtual_classroom.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            return;
        }

        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Virtual Classroom Manager ===");

        while (running) {
            try {
                System.out.println("\nCommands:");
                System.out.println("1. add_classroom <className>");
                System.out.println("2. add_student <studentId> <studentName> <className>");
                System.out.println("3. schedule_assignment <className> <assignmentName>");
                System.out.println("4. submit_assignment <studentId> <className> <assignmentName>");
                System.out.println("5. list_classrooms");
                System.out.println("6. list_students <className>");
                System.out.println("7. list_assignments <className>");
                System.out.println("8. exit");
                System.out.print("Enter command: ");

                String[] input = scanner.nextLine().trim().split("\\s+", 4);
                if (input.length == 0 || input[0].isEmpty()) continue;

                switch (input[0].toLowerCase()) {
                    case "add_classroom" -> {
                        if (input.length < 2) {
                            System.out.println("Usage: add_classroom <className>");
                            break;
                        }
                        if (manager.addClassroom(input[1])) {
                            System.out.println("Classroom " + input[1] + " has been created.");
                            logger.info("Classroom created: " + input[1]);
                        }
                    }
                    case "add_student" -> {
                        if (input.length < 4) {
                            System.out.println("Usage: add_student <studentId> <studentName> <className>");
                            break;
                        }
                        if (manager.addStudent(input[1], input[2], input[3])) {
                            System.out.println("Student " + input[1] + " has been enrolled in " + input[3] + ".");
                            logger.info("Student added: " + input[1] + " Name: " + input[2] + " Class: " + input[3]);
                        }
                    }
                    case "schedule_assignment" -> {
                        if (input.length < 3) {
                            System.out.println("Usage: schedule_assignment <className> <assignmentName>");
                            break;
                        }
                        if (manager.scheduleAssignment(input[1], input[2])) {
                            System.out.println("Assignment for " + input[1] + " has been scheduled.");
                            logger.info("Assignment scheduled: " + input[2] + " for class " + input[1]);
                        }
                    }
                    case "submit_assignment" -> {
                        if (input.length < 4) {
                            System.out.println("Usage: submit_assignment <studentId> <className> <assignmentName>");
                            break;
                        }
                        if (manager.submitAssignment(input[1], input[2], input[3])) {
                            System.out.println("Assignment submitted by Student " + input[1] + " in " + input[2] + ".");
                            logger.info("Assignment submitted: " + input[3] + " by student " + input[1] + " in class " + input[2]);
                        }
                    }
                    case "list_classrooms" -> {
                        manager.listClassrooms();
                        logger.info("Listed all classrooms");
                    }
                    case "list_students" -> {
                        if (input.length < 2) {
                            System.out.println("Usage: list_students <className>");
                            break;
                        }
                        manager.listStudents(input[1]);
                        logger.info("Listed students in class: " + input[1]);
                    }
                    case "list_assignments" -> {
                        if (input.length < 2) {
                            System.out.println("Usage: list_assignments <className>");
                            break;
                        }
                        manager.listAssignments(input[1]);
                        logger.info("Listed assignments in class: " + input[1]);
                    }
                    case "exit" -> {
                        running = false;
                        System.out.println("Exiting Virtual Classroom Manager. Goodbye!");
                        logger.info("Program exited by user");
                    }
                    default -> logger.warning("Unknown command: " + input[0]);
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
            }
        }
        scanner.close();
    }
}
