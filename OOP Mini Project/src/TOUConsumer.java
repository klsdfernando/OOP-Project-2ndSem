class TOUConsumer extends Consumer {
    private double dayConsumption;
    private double peakConsumption;
    private double offPeakConsumption;

    public TOUConsumer(String id, String name, double solarExport, double day, double peak, double off) {
        super(id, name, solarExport);
        this.dayConsumption = day;
        this.peakConsumption = peak;
        this.offPeakConsumption = off;
    }

    @Override
    public double calculateEnergyCharge(TariffRate rates) {
        return (dayConsumption * rates.getTOUDayRate()) +
                (peakConsumption * rates.getTOUPeakRate()) +
                (offPeakConsumption * rates.getTOUOffPeakRate());
    }

    @Override
    public double getFixedCharge(TariffRate rates) {
        return rates.getTOUFixedCharge();
    }
}
