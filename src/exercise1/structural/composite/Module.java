package exercise1.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Module extends CourseComponent {
    private String name;
    private List<CourseComponent> children = new ArrayList<>();

    public Module(String name) {
        this.name = name;
    }

    public String getModuleName() {
        return name;
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
        System.out.println("  ".repeat(indent) + "- Module: " + name);
        for (CourseComponent child : children) {
            child.display(indent + 1);
        }
    }
}
