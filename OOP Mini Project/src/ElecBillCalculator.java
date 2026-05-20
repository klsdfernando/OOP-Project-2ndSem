import java.util.Scanner;

public class ElecBillCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TariffRate rates = new TariffRate();
        ResidentialBillingEngine engine = new ResidentialBillingEngine();

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
            System.exit(0);
        }

        engine.printBill(consumer, rates);

        scanner.close();
    }
}