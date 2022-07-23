import java.util.*;

public class DeliveryTimeEstimator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input1 = scanner.nextLine().split(" ");
        int baseCost = Integer.parseInt(input1[0]);
        int packagesCount = Integer.parseInt(input1[1]);

        List<Package> packages = new ArrayList<>();
        for (int i = 0; i < packagesCount; i++) {
            String input = scanner.nextLine();
            Package pkg = Package.parsePackage(input);
            packages.add(pkg);
        }

        List<Vehicle> vehicles = Vehicle.parseVehicles(scanner.next());
        PackageDispatcher dispatcher = new PackageDispatcher(baseCost, vehicles);

        Map<Package, Double> report = dispatcher.dispatch(new ArrayList<>(packages));
        packages.forEach(pkg -> System.out.println(dispatcher.detail(pkg) + " " + report.getOrDefault(pkg, 0.0)));
    }
}
