import java.util.ArrayList;
import java.util.Scanner;

public class ElecBillCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TariffRate rates = new TariffRate();
        ResidentialBillingEngine engine = new ResidentialBillingEngine();
        BillHistoryManager historyManager = new BillHistoryManager();
        BillExporter billExporter = new BillExporter();

        System.out.println("--- Team 22 Electricity Bill Calculator ---");

        System.out.print("Enter Consumer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Consumer ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Solar Export Units (kWh) (0 if none): ");
        double solar = scanner.nextDouble();

        System.out.println("\nSelect Tariff Plan:");
        System.out.println("1. Standard Domestic");
        System.out.println("2. Time-of-Use (TOU)");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if (!ConsumerFactory.isValidChoice(choice)) {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            System.exit(0);
        }

        Consumer consumer = null;
        double total = 0, day = 0, peak = 0, off = 0;

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

        BillRecord record = engine.createBillRecord(consumer, rates);
        engine.printBill(record, rates);

        historyManager.addBill(record, true);
        String exportPath = billExporter.export(record, "bills");

        System.out.println("Bill added to history. Total bills this session: " + historyManager.getBillCount());
        if (exportPath != null) {
            System.out.println("Bill exported to: " + exportPath);
        } else {
            System.out.println("Warning: Bill could not be exported to file.");
        }

        scanner.nextLine();
        System.out.print("\nView bill history? (y/n): ");
        String viewHistory = scanner.nextLine().trim();
        if (viewHistory.equalsIgnoreCase("y")) {
            historyManager.printHistory(true);
        }

        scanner.close();
    }
}
