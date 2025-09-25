package exercise2.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment {
    private final String assignmentName;
    private final LocalDateTime createdAt;

    public Assignment(String assignmentName) {
        if (assignmentName == null || assignmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment name cannot be empty.");
        }
        this.assignmentName = assignmentName.trim();
        this.createdAt = LocalDateTime.now();
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return assignmentName + " (Created: " + createdAt + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment that = (Assignment) o;
        return assignmentName.equalsIgnoreCase(that.assignmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentName.toLowerCase());
    }
}
