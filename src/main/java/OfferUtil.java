import java.util.HashMap;
import java.util.Map;

public class OfferUtil {
    static Map<String, Offer> allOffers = new HashMap<>() {{
        put("OFR001", new Offer(pkg -> pkg.distance < 200 && pkg.weight >= 70 && pkg. weight <= 200, 10));
        put("OFR002", new Offer(pkg -> pkg.distance >= 50 && pkg.distance <= 150 && pkg.weight >= 70 && pkg. weight <= 200, 7));
        put("OFR003", new Offer(pkg -> pkg.distance >= 50 && pkg.distance <= 250 && pkg.weight >= 10 && pkg. weight <= 150, 5));
    }};
}
