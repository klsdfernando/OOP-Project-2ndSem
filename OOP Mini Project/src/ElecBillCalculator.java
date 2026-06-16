import java.util.Scanner;
import java.util.logging.Logger;

public class ElecBillCalculator {
    private static final Logger logger = Logger.getLogger(ElecBillCalculator.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TariffRate rates = new TariffRate();
        ResidentialBillingEngine engine = new ResidentialBillingEngine();
        BillHistoryManager historyManager = new BillHistoryManager();

        logger.info("--- Team 22 Electricity Bill Calculator ---");

        logger.info("Enter Consumer Name: ");
        String name = scanner.nextLine();

        logger.info("Enter Consumer ID: ");
        String id = scanner.nextLine();

        logger.info("Enter Solar Export Units (kWh) (0 if none): ");
        double solar = scanner.nextDouble();

        logger.info("\nSelect Tariff Plan:");
        logger.info("1. Standard Domestic");
        logger.info("2. Time-of-Use (TOU)");
        logger.info("Choice: ");
        int choice = scanner.nextInt();

        if (!ConsumerFactory.isValidChoice(choice)) {
            logger.info("Invalid choice. Exiting.");
            scanner.close();
            System.exit(0);
        }

        Consumer consumer = null;
        double total = 0;
        double day = 0;
        double peak = 0;
        double off = 0;

        if (choice == 1) {
            logger.info("Enter Total Monthly Consumption (kWh): ");
            total = scanner.nextDouble();
        } else {
            logger.info("Enter Day Consumption (kWh): ");
            day = scanner.nextDouble();
            logger.info("Enter Peak Consumption (kWh): ");
            peak = scanner.nextDouble();
            logger.info("Enter Off-Peak Consumption (kWh): ");
            off = scanner.nextDouble();
        }

        consumer = ConsumerFactory.createConsumer(choice, id, name, solar,
                total, day, peak, off);

        BillRecord billRecord = engine.createBillRecord(consumer, rates);
        engine.printBill(billRecord, rates);

        historyManager.addBill(billRecord);

        // මෙන්න මෙතන තිබුණු "new BillExporter()" පේළිය අයින් කරලා කෙලින්ම static call එක දීලා තියෙනවා:
        String exportPath = BillExporter.export(billRecord);

        // Logger placeholder formatting නිවැරදි කර ඇත:
        logger.info("Bill added to history. Total bills this session: " + historyManager.getBillCount());

        if (exportPath != null) {
            logger.info("Bill exported to: " + exportPath);
        } else {
            logger.info("Warning: Bill could not be exported to file.");
        }

        scanner.nextLine();
        logger.info("\nView bill history? (y/n): ");
        String viewHistory = scanner.nextLine().trim();
        if (viewHistory.equalsIgnoreCase("y")) {
            // ඔයාගේ BillHistoryManager එකේ printHistory method එකට parameters අවශ්‍ය නැති නිසා එය නිවැරදි කර ඇත:
            historyManager.printHistory();
        }

        scanner.close();
    }
}