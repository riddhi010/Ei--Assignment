package exercise1.creational.factory;

import java.util.logging.Logger;

public class WordDocument implements Document {
    private static final Logger logger = Logger.getLogger(WordDocument.class.getName());
    private String content;

    @Override
    public void generate(String content) {
        this.content = content;
        logger.info("Word Document created successfully!");
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getType() {
        return "Word";
    }
}
