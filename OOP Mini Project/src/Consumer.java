//Represents an abstract electricity consumer.
abstract class Consumer {
    private String consumerID;
    private String name;
    private double solarExportUnits;

    /**
     * Constructs a new Consumer with the specified details.
     *
     * @parameter consumerID       The identifier for the consumer.
     * @parameter name             The name of the consumer.
     * @parameter solarExportUnits The number of energy units exported to the grid.
     */
    public Consumer(String consumerID, String name, double solarExportUnits) {
        this.consumerID = consumerID;
        this.name = name;
        this.solarExportUnits = solarExportUnits;
    }

    /**
     * Calculates the energy charge based on the consumer's usage and tariff rates.
     *
     * @parameter rates The tariff rates applied to the consumer.
     * @return The calculated total energy charge.
     */
    public abstract double calculateEnergyCharge(TariffRate rates);

    /**
     * Retrieves the fixed charge applied to the consumer.
     *
     * @parameter rates The tariff rates containing the fixed charge values.
     * @return The fixed monthly charge.
     */
    public abstract double getFixedCharge(TariffRate rates);

    /**
     * Gets the consumer's unique ID.
     *
     * @return The consumer ID.
     */
    public String getConsumerID() { return consumerID; }

    /**
     * Gets the consumer's name.
     *
     * @return The name of the consumer.
     */
    public String getName() { return name; }

    /**
     * Gets the amount of solar units exported by the consumer.
     *
     * @return The solar export units.
     */
    public double getSolarExportUnits() { return solarExportUnits; }
}