abstract class Consumer {
    private String consumerID;
    private String name;
    private double solarExportUnits;

    public Consumer(String consumerID, String name, double solarExportUnits) {
        this.consumerID = consumerID;
        this.name = name;
        this.solarExportUnits = solarExportUnits;
    }

    public abstract double calculateEnergyCharge(TariffRate rates);

    public abstract double getFixedCharge(TariffRate rates);

    public String getConsumerID() { return consumerID; }
    public String getName() { return name; }
    public double getSolarExportUnits() { return solarExportUnits; }
}
