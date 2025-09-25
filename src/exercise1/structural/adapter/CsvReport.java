package exercise1.structural.adapter;

import java.util.logging.Logger;

public class CsvReport {
    private static final Logger logger = Logger.getLogger(CsvReport.class.getName());

    // Third-party method that doesn't match the interface
    public void generateCsvReport(String reportTitle, String reportContent) {
        logger.info("📄 CSV Report Generated!");
        System.out.println("----- CSV Report -----");
        System.out.println("Title: " + reportTitle);
        System.out.println("Content: " + reportContent);
        System.out.println("----------------------");
    }
}
