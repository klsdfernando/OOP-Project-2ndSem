import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class BillHistoryManager {
    private final List<BillRecord> history = new ArrayList<>();
    private final Path historyFile = Paths.get("bill_history.txt");

    public void addBill(BillRecord record, boolean confirmSave) {
        history.add(record);
        appendToFile(record);
    }

    public List<BillRecord> getAllBills() {
        return Collections.unmodifiableList(history);
    }

    public int getBillCount() {
        return history.size();
    }

    public void printHistory(boolean showDetails) {
        if (history.isEmpty()) {
            System.out.println("\nNo bills in history yet.");
            return;
        }

        System.out.println("\n========= BILL HISTORY =========");
        for (int i = 0; i < history.size(); i++) {
            BillRecord record = history.get(i);
            System.out.printf("%d. %s (%s) | %s | Net: Rs. %.2f%n",
                    i + 1,
                    record.getConsumerName(),
                    record.getConsumerID(),
                    record.getTariffType(),
                    record.getNetPayable());
        }
        System.out.println("================================\n");
    }

    private void appendToFile(BillRecord record) {
        try {
            Files.writeString(historyFile, record.toFormattedString() + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Warning: Could not save to bill history file.");
        }
    }
}
