import java.util.function.Predicate;

public class Offer {
    final Predicate<Package> criteria;

    public Offer(Predicate<Package> criteria) {
        this.criteria = criteria;
    }

    public boolean apply(Package pkg) {
        return criteria.test(pkg);
    }
}
