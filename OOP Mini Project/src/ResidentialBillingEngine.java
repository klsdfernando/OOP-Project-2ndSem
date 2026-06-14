class ResidentialBillingEngine implements BillingService {

    @Override
    public double calculateTotalBill(Consumer c, TariffRate r) {
        double energyCharge = c.calculateEnergyCharge(r);

        double fixedCharge = c.getFixedCharge(r);

        double solarCredit = c.getSolarExportUnits() * r.getSolarExportRate();

        return (energyCharge + fixedCharge) - solarCredit;
    }

    public BillRecord createBillRecord(Consumer c, TariffRate r) {
        double energy = c.calculateEnergyCharge(r);
        double fixed = c.getFixedCharge(r);
        double solar = c.getSolarExportUnits() * r.getSolarExportRate();
        double net = calculateTotalBill(c, r);
        String tariffType = c instanceof TOUConsumer ? "Time-of-Use (TOU)" : "Standard Domestic";

        return new BillRecord(c.getConsumerID(), c.getName(), tariffType, energy, fixed, solar, net);
    }

    @Override
    public void printBill(Consumer c, TariffRate r) {
        printBill(createBillRecord(c, r));
    }

    public void printBill(BillRecord record) {
        System.out.println("\n" + record.toFormattedString());
    }
}
