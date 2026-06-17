import java.util.Arrays;
//calculate the bill for counsumer category time of use
class TOUConsumer extends Consumer {
    private double dayConsumption;
    private double peakConsumption;
    private double offPeakConsumption;

    //get the costermer details
    public TOUConsumer(String id, String name, double solarExport, double day, double peak, double off) {
        super(id, name, solarExport);
        this.dayConsumption = day;
        this.peakConsumption = peak;
        this.offPeakConsumption = off;
    }

    @Override
    //calculate Energy Charge  methode added
    public double calculateEnergyCharge(TariffRate rates) {
        return (dayConsumption * rates.getTOUDayRate()) +
                (peakConsumption * rates.getTOUPeakRate()) +
                (offPeakConsumption * rates.getTOUOffPeakRate());
    }

    @Override
    public double getFixedCharge(TariffRate rates) {
        return rates.getTOUFixedCharge();
    }

    public double getTotalConsumption(String unitLabel) {
        return dayConsumption + peakConsumption + offPeakConsumption;
    }


}
