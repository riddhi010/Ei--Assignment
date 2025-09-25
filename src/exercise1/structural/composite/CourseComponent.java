package exercise1.structural.composite;

public abstract class CourseComponent {
    public void add(CourseComponent component) {
        throw new UnsupportedOperationException("Add operation not supported");
    }

    public void remove(CourseComponent component) {
        throw new UnsupportedOperationException("Remove operation not supported");
    }

    public abstract void display(int indent);
}
