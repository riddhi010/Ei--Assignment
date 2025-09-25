package exercise1.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Assignment {

    private static final Logger logger = Logger.getLogger(Assignment.class.getName());
    private final String name;
    private String deadline;
    private final List<StudentObserver> students;

    public Assignment(String name, String deadline) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment name cannot be empty.");
        }
        if (deadline == null || !deadline.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
            throw new IllegalArgumentException("Invalid time format for deadline.");
        }
        this.name = name.trim();
        this.deadline = deadline;
        this.students = new ArrayList<>();
        logger.info("Assignment '" + this.name + "' created with deadline " + this.deadline);
    }

    public void addStudent(StudentObserver student) {
        if (student == null) {
            logger.warning("Cannot add null student.");
            return;
        }
        if (students.contains(student)) {
            logger.warning(student.getName() + " is already enrolled.");
            return;
        }
        students.add(student);
        logger.info(student.getName() + " added to assignment '" + name + "'.");
    }

    public void removeStudent(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            logger.warning("Student name cannot be empty.");
            return;
        }
        boolean removed = students.removeIf(s -> s.getName().equalsIgnoreCase(studentName.trim()));
        if (removed) {
            logger.info(studentName + " removed from assignment '" + name + "'.");
        } else {
            logger.warning("Student '" + studentName + "' not found.");
        }
    }

    public void updateDeadline(String newDeadline) {
        if (newDeadline == null || !newDeadline.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
            logger.warning("Invalid time format. Deadline not updated.");
            return;
        }
        this.deadline = newDeadline;
        logger.info("Deadline for assignment '" + name + "' updated to " + newDeadline);
        notifyStudents();
    }

    private void notifyStudents() {
        for (StudentObserver student : students) {
            int retries = 3;
            while (retries > 0) {
                try {
                    if (Math.random() < 0.1) {
                        throw new RuntimeException("Transient notification failure.");
                    }
                    student.update(name, deadline);
                    break;
                } catch (RuntimeException e) {
                    retries--;
                    logger.warning("Failed to notify " + student.getName() + ". Retries left: " + retries);
                    if (retries == 0) {
                        logger.severe("Could not notify " + student.getName() + " due to repeated transient failures.");
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        logger.severe("Retry interrupted: " + ie.getMessage());
                    }
                }
            }
        }
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            logger.info("No students enrolled for assignment '" + name + "'.");
            return;
        }
        System.out.println("\n--- Students Enrolled for '" + name + "' | Deadline: " + deadline + " ---");
        for (StudentObserver student : students) {
            System.out.println("• " + student.getName());
        }
    }
}
