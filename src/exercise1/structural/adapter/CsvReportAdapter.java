package exercise1.structural.adapter;

public class CsvReportAdapter implements ReportGenerator {

    private final CsvReport csvReport;

    public CsvReportAdapter(CsvReport csvReport) {
        if (csvReport == null) {
            throw new IllegalArgumentException("CsvReport cannot be null.");
        }
        this.csvReport = csvReport;
    }

    @Override
    public void generateReport(String title, String content) {
        // Translate the client call into the adaptee method
        csvReport.generateCsvReport(title, content);
    }
}
