import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

public class BillExporter {
    private final Path exportDir = Paths.get("bills");

    public String export(BillRecord record, String customPath) {
        try {
            Files.createDirectories(exportDir);

            String timestamp = record.getGeneratedAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String safeId = record.getConsumerID().replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = safeId + "_" + timestamp + ".txt";
            Path filePath = exportDir.resolve(fileName);

            Files.writeString(filePath, record.toFormattedString());
            return filePath.toString();
        } catch (IOException e) {
            return null;
        }
    }
}

class export_util {
    static String cleanName(String name) {
        return name.trim();
    }
}
