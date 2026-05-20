public interface BillingService {
    double calculateTotalBill(Consumer c, TariffRate r);
    void printBill(Consumer c, TariffRate r);
}
