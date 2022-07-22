import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    @Test
    void testOfferApply() {
        Offer offer = new Offer(pkg -> pkg.weight <= 70 && pkg.distance <= 100);
        Package pkgOne = Package.parsePackage("PKG1 5 10 OFR001");
        Package pkgTwo = Package.parsePackage("PKG2 75 50");

        assertTrue(offer.apply(pkgOne));
        assertFalse(offer.apply(pkgTwo));
    }
}