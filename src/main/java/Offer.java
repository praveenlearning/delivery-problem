import java.util.function.Predicate;

public class Offer {
    final Predicate<Package> criteria;
    final int discount;

    public Offer(Predicate<Package> criteria, int discount) {
        this.criteria = criteria;
        this.discount = discount;
    }

    public int apply(Package pkg) {
        if (criteria.test(pkg))
            return discount;
        else
            return 0;
    }
}
