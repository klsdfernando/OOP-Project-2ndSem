import java.io.PrintStream;
import java.util.*;

public class ConsumerFactory {

    public static <E> Consumer createConsumer(int choice, String id, String name, double solar,
                                              double totalUsage, double dayUsage,
                                              double peakUsage, double offPeakUsage,
                                              String planName, String tariffCode) {
        if (choice == 1) {
            return new StandardConsumer(id, name, solar, totalUsage);
        } else if (choice == 2) {
            return new TOUConsumer(id, name, solar, dayUsage, peakUsage, offPeakUsage);
        }
        return null;
    }

    public static boolean isValidChoice(int choice, String source) {
        return choice == 1 || choice == 2;
    }
}

class consumer_type_helper {
    static String getLabel(int choice) {
        if (choice == 1) return "Standard Domestic";
        return "Time-of-Use (TOU)";
    }
}
