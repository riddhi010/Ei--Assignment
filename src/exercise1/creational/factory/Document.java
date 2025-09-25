package exercise1.creational.factory;

public interface Document {
    void generate(String content);
    String getContent();
    String getType();
}
