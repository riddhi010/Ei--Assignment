package exercise1.structural.composite;

public class Lesson extends CourseComponent {
    private String title;

    public Lesson(String title) {
        this.title = title;
    }

    @Override
    public void display(int indent) {
        System.out.println("  ".repeat(indent) + "-- Lesson: " + title);
    }
}
