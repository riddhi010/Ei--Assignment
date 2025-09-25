package exercise2.model;

import java.util.*;

public class Classroom {
    private final String className;
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Assignment> assignments = new HashMap<>();

    public Classroom(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be empty.");
        }
        this.className = className.trim();
    }

    public String getClassName() {
        return className;
    }

    public boolean addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) return false;
        students.put(student.getStudentId(), student);
        return true;
    }

    public boolean removeStudent(String studentId) {
        return students.remove(studentId) != null;
    }

    public boolean scheduleAssignment(Assignment assignment) {
        if (assignments.containsKey(assignment.getAssignmentName())) return false;
        assignments.put(assignment.getAssignmentName(), assignment);

        // Notify all students about the new assignment
        for (Student s : students.values()) {
            s.update(assignment, className);
        }
        return true;
    }

    public boolean submitAssignment(String studentId, String assignmentName) {
        Student s = students.get(studentId);
        Assignment a = assignments.get(assignmentName);
        if (s == null || a == null) return false;
        s.submitAssignment(a);
        return true;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Assignment> getAllAssignments() {
        return assignments.values();
    }
}
