package exercise1.creational.factory;

public class DocumentFactory {

    public static Document createDocument(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Document type cannot be null or empty.");
        }
        switch (type.toLowerCase()) {
            case "pdf": return new PdfDocument();
            case "word": return new WordDocument();
            case "excel": return new ExcelDocument();
            default: throw new IllegalArgumentException("Unsupported document type: " + type);
        }
    }
}
