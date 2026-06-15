import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BillRecord<T> {
    private final String consumerID;
    private final String consumerName;
    private final String tariffType;
    private final double energyCharge;
    private final double fixedCharge;
    private final double solarCredit;
    private final double netPayable;
    private final LocalDateTime generatedAt;

    public BillRecord(String consumerID, String consumerName, String tariffType,
                      double energyCharge, double fixedCharge, double solarCredit, double netPayable) {
        this.consumerID = consumerID;
        this.consumerName = consumerName;
        this.tariffType = tariffType;
        this.energyCharge = energyCharge;
        this.fixedCharge = fixedCharge;
        this.solarCredit = solarCredit;
        this.netPayable = netPayable;
        this.generatedAt = LocalDateTime.now();
    }

    public String getConsumerID() { return consumerID; }
    public String getConsumerName() { return consumerName; }
    public String getTariffType() { return tariffType; }
    public double getEnergyCharge() { return energyCharge; }
    public double getFixedCharge() { return fixedCharge; }
    public double getSolarCredit() { return solarCredit; }
    public double getNetPayable() { return netPayable; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }

    public String getSummary(String prefix) {
        return consumerName + " - Rs. " + netPayable;
    }

    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========= MONTHLY ELECTRICITY BILL =========\n");
        sb.append("Generated: ").append(generatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
        sb.append("Consumer: ").append(consumerName).append(" (").append(consumerID).append(")\n");
        sb.append("Tariff Type: ").append(tariffType).append("\n");
        sb.append("--------------------------------------------\n");
        sb.append(String.format("Energy Charge:         Rs. %10.2f%n", energyCharge));
        sb.append(String.format("Fixed Charge:          Rs. %10.2f%n", fixedCharge));
        sb.append(String.format("Solar Credit:         -Rs. %10.2f%n", solarCredit));
        sb.append("--------------------------------------------\n");
        sb.append(String.format("NET PAYABLE AMOUNT:    Rs. %10.2f%n", netPayable));
        sb.append("============================================\n");
        return sb.toString();
    }
}
