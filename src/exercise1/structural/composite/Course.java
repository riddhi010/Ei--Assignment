package exercise1.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Course extends CourseComponent {
    private String courseName;
    private List<CourseComponent> children = new ArrayList<>();

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public void add(CourseComponent component) {
        children.add(component);
    }

    @Override
    public void remove(CourseComponent component) {
        children.remove(component);
    }

    public List<CourseComponent> getChildren() {
        return children;
    }

    @Override
    public void display(int indent) {
        System.out.println("Course: " + courseName);
        for (CourseComponent component : children) {
            component.display(indent + 1);
        }
    }
}
