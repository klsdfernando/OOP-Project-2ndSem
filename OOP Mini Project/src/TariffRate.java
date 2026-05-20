public class TariffRate {
    private final double domesticSlab1Rate = 4.50;
    private final double domesticSlab2Rate = 8.00;

    private final double above60Block1Rate = 12.75;
    private final double above60Block2Rate = 18.50;
    private final double above60Block3Rate = 24.00;

    private final double domesticFixedCharge1 = 80.00;
    private final double domesticFixedCharge2 = 210.00;

    private final double TOUDayRate = 35.00;
    private final double TOUPeakRate = 67.00;
    private final double TOUOffPeakRate = 21.00;
    private final double TOUFixedCharge = 2100.00; // [cite: 34]

    private final double solarExportRate = 25.00;

    public double getDomesticSlab1Rate() { return domesticSlab1Rate; }
    public double getDomesticSlab2Rate() { return domesticSlab2Rate; }
    public double getAbove60Block1Rate() { return above60Block1Rate; }
    public double getAbove60Block2Rate() { return above60Block2Rate; }
    public double getAbove60Block3Rate() { return above60Block3Rate; }
    public double getDomesticFixedCharge1() { return domesticFixedCharge1; }
    public double getDomesticFixedCharge2() { return domesticFixedCharge2; }
    public double getTOUDayRate() { return TOUDayRate; }
    public double getTOUPeakRate() { return TOUPeakRate; }
    public double getTOUOffPeakRate() { return TOUOffPeakRate; }
    public double getTOUFixedCharge() { return TOUFixedCharge; }
    public double getSolarExportRate() { return solarExportRate; }
}
