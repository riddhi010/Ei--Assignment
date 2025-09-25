package exercise2.manager;

import exercise2.model.*;

import java.util.*;
import java.util.logging.Logger;

public class VirtualClassroomManager {
    private static final Logger logger = Logger.getLogger(VirtualClassroomManager.class.getName());
    private final Map<String, Classroom> classrooms = new HashMap<>();

    public boolean addClassroom(String className) {
        if (classrooms.containsKey(className)) {
            logger.warning("Classroom already exists.");
            return false;
        }
        classrooms.put(className, new Classroom(className));
        return true;
    }

    public boolean addStudent(String studentId, String studentName, String className) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            logger.warning("Classroom not found.");
            return false;
        }
        return c.addStudent(new Student(studentId, studentName));
    }

    public boolean scheduleAssignment(String className, String assignmentName) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            logger.warning("Classroom not found.");
            return false;
        }
        return c.scheduleAssignment(new Assignment(assignmentName));
    }

    public boolean submitAssignment(String studentId, String className, String assignmentName) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            logger.warning("Classroom not found.");
            return false;
        }
        return c.submitAssignment(studentId, assignmentName);
    }

    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
            return;
        }
        classrooms.keySet().forEach(c -> System.out.println("- " + c));
    }

    public void listStudents(String className) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            System.out.println("Classroom not found.");
            return;
        }
        if (c.getAllStudents().isEmpty()) {
            System.out.println("No students enrolled in " + className + ".");
            return;
        }
        c.getAllStudents().forEach(s -> System.out.println("- " + s.getStudentId() + " (" + s.getName() + ")"));
    }

    public void listAssignments(String className) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            System.out.println("Classroom not found.");
            return;
        }
        if (c.getAllAssignments().isEmpty()) {
            System.out.println("No assignments scheduled in " + className + ".");
            return;
        }
        c.getAllAssignments().forEach(a -> System.out.println("- " + a));
    }
}
