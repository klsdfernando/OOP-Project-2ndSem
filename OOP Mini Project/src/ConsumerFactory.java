public class ConsumerFactory {

    public static Consumer createConsumer(int choice, String id, String name, double solar,
                                              double totalUsage, double dayUsage,
                                              double peakUsage, double offPeakUsage) {
        if (choice == 1) {
            return new StandardConsumer(id, name, solar, totalUsage);
        } else if (choice == 2) {
            return new TOUConsumer(id, name, solar, dayUsage, peakUsage, offPeakUsage);
        }
        return null;
    }

    public static boolean isValidChoice(int choice) {
        return choice == 1 || choice == 2;
    }
}

class consumerTypeHelper {
    static String getLabel(int choice) {
        if (choice == 1) return "Standard Domestic";
        return "Time-of-Use (TOU)";
    }
}
