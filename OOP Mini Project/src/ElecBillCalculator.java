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

        Consumer consumer = null;

        if (choice == 1) {
            System.out.print("Enter Total Monthly Consumption (kWh): ");
            double total = scanner.nextDouble();
            consumer = new StandardConsumer(id, name, solar, total);

        } else if (choice == 2) {
            System.out.print("Enter Day Consumption (kWh): ");
            double day = scanner.nextDouble();
            System.out.print("Enter Peak Consumption (kWh): ");
            double peak = scanner.nextDouble();
            System.out.print("Enter Off-Peak Consumption (kWh): ");
            double off = scanner.nextDouble();
            consumer = new TOUConsumer(id, name, solar, day, peak, off);

        } else {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            System.exit(0);
        }

        BillRecord record = engine.createBillRecord(consumer, rates);
        engine.printBill(record);

        historyManager.addBill(record);
        String exportPath = billExporter.export(record);

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
            historyManager.printHistory();
        }

        scanner.close();
    }
}
