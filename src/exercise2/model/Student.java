package exercise2.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student implements AssignmentObserver {
    private final String studentId;
    private final String name;
    private final Set<Assignment> submittedAssignments = new HashSet<>();

    public Student(String studentId, String name) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId.trim();
        this.name = (name == null || name.isBlank()) ? "Unknown" : name.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void submitAssignment(Assignment assignment) {
        submittedAssignments.add(assignment);
    }

    public boolean hasSubmitted(Assignment assignment) {
        return submittedAssignments.contains(assignment);
    }

    public Set<Assignment> getSubmittedAssignments() {
        return new HashSet<>(submittedAssignments);
    }

    @Override
    public void update(Assignment assignment, String className) {
        // Console notification when a new assignment is scheduled
        System.out.println("[Notification] Student " + studentId +
                ": New assignment '" + assignment.getAssignmentName() +
                "' scheduled in classroom '" + className + "'.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studentId.equalsIgnoreCase(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId.toLowerCase());
    }
}
