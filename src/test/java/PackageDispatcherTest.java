import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageDispatcherTest {
    private final PackageDispatcher dispatcher = new PackageDispatcher(100);

    @Test
    public void shouldCalculateTotalCostForPackage() {
        Package pkg = Package.parsePackage("PKG1 15 5 OFR001");

        int expected = 275;
        int cost = dispatcher.calculateCost(pkg);

        assertEquals(expected, cost);
    }

    @Test
    public void shouldCalculateDiscountForPackage() {
        Package pkg = Package.parsePackage("PKG1 75 50 OFR001");

        int expected = 110;
        int discount = dispatcher.calculateDiscount(pkg);

        assertEquals(expected, discount);
    }

    @Test
    public void shouldReturnZeroDiscountForInvalidOfferCode() {
        Package pkg = Package.parsePackage("PKG1 75 50 INVALID");

        int expected = 0;
        int discount = dispatcher.calculateDiscount(pkg);

        assertEquals(expected, discount);
    }

    @Test
    public void shouldCalculateTotalCostWithDiscount() {
        Package pkg = Package.parsePackage("PKG1 75 50 OFR001");

        int expected = 990;
        int finalAmount = dispatcher.finalCost(pkg);

        assertEquals(expected, finalAmount);
    }

    @Test
    public void shouldReturnDetailsAboutDiscountAndPrice() {
        Package pkg = Package.parsePackage("PKG1 75 50 OFR001");

        String expected = "PKG1 110 990";
        String finalAmount = dispatcher.detail(pkg);

        assertEquals(expected, finalAmount);
    }
}