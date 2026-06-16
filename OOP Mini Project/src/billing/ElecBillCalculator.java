package billing;

import billing.BillExporter;

import java.util.Scanner;

import java.util.logging.Logger;

public class ElecBillCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        private static final Logger logger = Logger.getLogger(ElecBillCalculator.class.getName());
        TariffRate rates = new TariffRate();
        ResidentialBillingEngine engine = new ResidentialBillingEngine();
        BillHistoryManager historyManager = new BillHistoryManager();
        BillExporter billExporter = new BillExporter();

        logger.info("--- Team 22 Electricity Bill Calculator ---");

        logger.info("Enter Consumer Name: ");
        String name = scanner.nextLine();

        logger.info("Enter Consumer ID: ");
        String id = scanner.nextLine();

        logger.info("Enter Solar Export Units (kWh) (0 if none): ");
        double solar = scanner.nextDouble();

        logger.info("\nSelect Tariff Plan:");
        logger.info("1. Standard Domestic");
        System.out.println("2. Time-of-Use (TOU)");
        System.out.print("Choice: ");
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
            System.out.print("Enter Total Monthly Consumption (kWh): ");
            total = scanner.nextDouble();
        } else {
            System.out.print("Enter Day Consumption (kWh): ");
            day = scanner.nextDouble();
            System.out.print("Enter Peak Consumption (kWh): ");
            peak = scanner.nextDouble();
            System.out.print("Enter Off-Peak Consumption (kWh): ");
            off = scanner.nextDouble();
        }

        consumer = ConsumerFactory.createConsumer(choice, id, name, solar,
                total, day, peak, off);

        BillRecord billRecord = engine.createBillRecord(consumer, rates);
        engine.printBill(billRecord, rates);

        historyManager.addBill(billRecord);
        String exportPath = billExporter.export(billRecord);

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
            historyManager.printHistory(true);
        }

        scanner.close();
    }
}
