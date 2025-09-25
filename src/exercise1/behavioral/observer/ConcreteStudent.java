package exercise1.behavioral.observer;

import java.util.logging.Logger;

public class ConcreteStudent implements StudentObserver {

    private static final Logger logger = Logger.getLogger(ConcreteStudent.class.getName());
    private final String name;

    // ANSI escape codes for colored messages
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    public ConcreteStudent(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String assignmentName, String newDeadline) {
        // Colored notification in green
        System.out.println(GREEN + "[NOTIFICATION] " + name +
                ", new deadline for '" + assignmentName + "' is " + newDeadline + RESET);
        logger.info("Student " + name + " notified for assignment '" + assignmentName + "'.");
    }
}
