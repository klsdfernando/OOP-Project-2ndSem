package billing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class BillExporter {
    private final Path exportDir = Paths.get("bills");

    public String export(BillRecord billRecord) {
        try {
            Files.createDirectories(exportDir);

            String timestamp = billRecord.getGeneratedAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String safeId = billRecord.getConsumerID().replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = safeId + "_" + timestamp + ".txt";
            Path filePath = exportDir.resolve(fileName);

            Files.writeString(filePath, billRecord.toFormattedString());
            return filePath.toString();
        } catch (IOException e) {
            return null;
        }
    }
}

// Private constructor to prevent instantiation
private BillExporter() {
    throw new IllegalStateException("Utility class");
}

class ExportUtil {
    static String cleanName(String name) {
        return name.trim();
    }
}
