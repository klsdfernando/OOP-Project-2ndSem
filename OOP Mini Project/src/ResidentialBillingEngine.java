class ResidentialBillingEngine implements BillingService {

    @Override
    public double calculateTotalBill(Consumer c, TariffRate r) {
        double energyCharge = c.calculateEnergyCharge(r);

        double fixedCharge = c.getFixedCharge(r);

        double solarCredit = c.getSolarExportUnits() * r.getSolarExportRate();

        return (energyCharge + fixedCharge) - solarCredit;
    }

    @Override
    public void printBill(Consumer c, TariffRate r) {
        double energy = c.calculateEnergyCharge(r);
        double fixed = c.getFixedCharge(r);
        double solar = c.getSolarExportUnits() * r.getSolarExportRate();
        double net = calculateTotalBill(c, r);

        System.out.println("\n========= MONTHLY ELECTRICITY BILL =========");
        System.out.println("Consumer: " + c.getName() + " (" + c.getConsumerID() + ")");

        if (c instanceof TOUConsumer) {
            System.out.println("Tariff Type: Time-of-Use (TOU)");
        } else {
            System.out.println("Tariff Type: Standard Domestic");
        }

        System.out.println("--------------------------------------------");
        System.out.printf("Energy Charge:         Rs. %10.2f%n", energy);
        System.out.printf("Fixed Charge:          Rs. %10.2f%n", fixed);
        System.out.printf("Solar Credit:         -Rs. %10.2f%n", solar);
        System.out.println("--------------------------------------------");
        System.out.printf("NET PAYABLE AMOUNT:    Rs. %10.2f%n", net);
        System.out.println("============================================\n");
    }
}
