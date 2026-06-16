import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class BillExporter {
    private static final Path EXPORT_DIR = Paths.get("bills");

    // BillExporter Constructor - (Sonar Issue එක විසඳා ඇත)
    private BillExporter() {
        throw new IllegalStateException("Utility class");
    }

    public static String export(BillRecord billRecord) {
        try {
            Files.createDirectories(EXPORT_DIR);

            String timestamp = billRecord.getGeneratedAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String safeId = billRecord.getConsumerID().replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = safeId + "_" + timestamp + ".txt";
            Path filePath = EXPORT_DIR.resolve(fileName);

            Files.writeString(filePath, billRecord.toFormattedString());
            return filePath.toString();
        } catch (IOException e) {
            return null;
        }
    }
}

// Sonar Issue (java:S1118) එක නැති කිරීමට ExportUtil එකටද private constructor එකක් එකතු කරන ලදී
class ExportUtil {

    private ExportUtil() {
        throw new IllegalStateException("Utility class");
    }

    static String cleanName(String name) {
        return name.trim();
    }
}