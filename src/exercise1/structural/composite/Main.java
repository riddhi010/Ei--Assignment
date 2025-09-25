package exercise1.structural.composite;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for course name
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine().trim();
        if (courseName.isEmpty()) {
            logger.warning("Course name cannot be empty. Using default 'Java Programming'.");
            courseName = "Java Programming";
        }

        Course course = new Course(courseName);
        logger.info("Course created: " + courseName);

        boolean running = true;
        while (running) {
            try {
                System.out.println("\n===== Course Management Menu =====");
                System.out.println("1. Add Module");
                System.out.println("2. Add Lesson to Module");
                System.out.println("3. View Course Hierarchy");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter module name: ");
                        String moduleName = scanner.nextLine().trim();
                        if (moduleName.isEmpty()) {
                            logger.warning("Module name cannot be empty.");
                            break;
                        }
                        Module module = new Module(moduleName);
                        course.add(module);
                        logger.info("Module added: " + moduleName);
                        break;

                    case 2:
                        if (course.getChildren().isEmpty()) {
                            logger.warning("No modules available. Add a module first.");
                            break;
                        }
                        System.out.println("Select module by number:");
                        for (int i = 0; i < course.getChildren().size(); i++) {
                            System.out.println((i + 1) + ". " + ((Module) course.getChildren().get(i)).getModuleName());
                        }
                        int moduleIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        if (moduleIndex < 0 || moduleIndex >= course.getChildren().size()) {
                            logger.warning("Invalid module selection.");
                            break;
                        }
                        Module selectedModule = (Module) course.getChildren().get(moduleIndex);

                        System.out.print("Enter lesson title: ");
                        String lessonTitle = scanner.nextLine().trim();
                        if (lessonTitle.isEmpty()) {
                            logger.warning("Lesson title cannot be empty.");
                            break;
                        }
                        Lesson lesson = new Lesson(lessonTitle);
                        selectedModule.add(lesson);
                        logger.info("Lesson added: " + lessonTitle + " to Module: " + selectedModule.getModuleName());
                        break;

                    case 3:
                        if (course.getChildren().isEmpty()) {
                            System.out.println("No modules/lessons added yet.");
                        } else {
                            course.display(0);
                        }
                        break;

                    case 4:
                        running = false;
                        System.out.println("Exiting program. Goodbye!");
                        break;

                    default:
                        logger.warning("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                logger.warning("Invalid input. Enter a number.");
            } catch (InputMismatchException e) {
                logger.warning("Invalid input type.");
                scanner.nextLine(); // clear buffer
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
            }
        }

        scanner.close();
    }
}
