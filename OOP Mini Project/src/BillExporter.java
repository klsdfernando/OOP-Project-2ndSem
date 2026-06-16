import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class BillExporter {
    private static final Path EXPORT_DIR = Paths.get("bills");

    // Private constructor to prevent instantiation (Sonar issue එක වෙනුවෙන්)
    private BillExporter() {
        throw new IllegalStateException("Utility class");
    }

    // Method එක static කළ නිසා object නොසා කෙලින්ම භාවිත කළ හැක
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

class ExportUtil {
    static String cleanName(String name) {
        return name.trim();
    }
}