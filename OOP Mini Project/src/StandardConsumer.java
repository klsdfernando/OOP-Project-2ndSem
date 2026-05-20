class StandardConsumer extends Consumer {
    private double totalConsumption;

    public StandardConsumer(String id, String name, double solarExportUnits, double totalConsumption) {
        super(id, name, solarExportUnits);
        this.totalConsumption = totalConsumption;
    }

    @Override
    public double calculateEnergyCharge(TariffRate rates) {
        double units = totalConsumption;
        double charge = 0;

        if (units <= 60) {
            if (units <= 30) {
                charge = units * rates.getDomesticSlab1Rate();
            } else {
                charge = (30 * rates.getDomesticSlab1Rate()) +
                        ((units - 30) * rates.getDomesticSlab2Rate());
            }
        } else {
            if (units <= 90) {
                charge = (60 * rates.getAbove60Block1Rate()) +
                        ((units - 60) * rates.getAbove60Block2Rate());
            } else {
                charge = (60 * rates.getAbove60Block1Rate()) +
                        (30 * rates.getAbove60Block2Rate()) +
                        ((units - 90) * rates.getAbove60Block3Rate());
            }
        }
        return charge;
    }

    @Override
    public double getFixedCharge(TariffRate rates) {
        if (totalConsumption <= 30) return rates.getDomesticFixedCharge1();
        return rates.getDomesticFixedCharge2();
    }
}
