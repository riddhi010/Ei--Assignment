package exercise1.behavioral.observer;

public interface StudentObserver {
    void update(String assignmentName, String newDeadline);
    String getName();
}
