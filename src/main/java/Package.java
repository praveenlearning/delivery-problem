import java.util.Objects;

public class Package {
    final String packageId;
    final int weight;
    final int distance;
    final String offerCode;

    public Package(String packageId, int weight, int distance, String offerCode) {
        this.packageId = packageId;
        this.distance = distance;
        this.weight = weight;
        this.offerCode = offerCode;
    }

    public static Package parsePackage(String input) {
        String[] packageInput = input.split(" ");
        String packageId = packageInput[0];
        int weight = Integer.parseInt(packageInput[1]);
        int distance = Integer.parseInt(packageInput[2]);
        String offerCode = null;
        try {
            offerCode = packageInput[3];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("No offer code provided");
        }
        return new Package(packageId, weight, distance, offerCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return weight == aPackage.weight && distance == aPackage.distance && packageId.equals(aPackage.packageId)
                && ((offerCode == null && aPackage.offerCode == null) || offerCode.equals(aPackage.offerCode));
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, weight, distance, offerCode);
    }
}
