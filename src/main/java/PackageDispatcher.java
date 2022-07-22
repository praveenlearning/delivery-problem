public class PackageDispatcher {
    final int basePrice;

    public PackageDispatcher(int basePrice) {
        this.basePrice = basePrice;
    }

    public int calculateCost(Package pkg) {
        return basePrice + pkg.weight * 10 + pkg.distance * 5;
    }

    public int calculateDiscount(Package pkg) {
        Offer offer = OfferUtil.allOffers.get(pkg.offerCode);
        try {
            int discount = offer.apply(pkg);
            int totalCost = calculateCost(pkg);
            return totalCost * discount / 100;
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    public int finalCost(Package pkg) {
        return calculateCost(pkg) - calculateDiscount(pkg);
    }

    public String detail(Package pkg) {
        return pkg.packageId + " " + calculateDiscount(pkg) + " " + finalCost(pkg);
    }
}
