import java.util.*;
import java.util.stream.Collectors;

public class PackageDispatcher {
    final int basePrice;
    final List<Vehicle> vehicles = new ArrayList<>();

    public PackageDispatcher(int basePrice) {
        this.basePrice = basePrice;
    }

    public PackageDispatcher(int basePrice, List<Vehicle> vehicles) {
        this.basePrice = basePrice;
        this.vehicles.addAll(vehicles);
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

    public List<Package> findPackagesForVehicle(List<Package> packageList, Vehicle vehicle) {
        List<List<Package>> possibleSubsets = ListUtils.findSubsets(packageList);

        for (int i = packageList.size(); i > 0; i--) {
            int finalI = i;
            List<List<Package>> validPackagesList = possibleSubsets.stream().filter(subset -> subset.size() == finalI).filter(list -> ListUtils.totalWeightOfPackageList(list) < vehicle.maxWeight).collect(Collectors.toList());

            int validPackagesListCount = validPackagesList.size();
            if (validPackagesListCount >= 1) {
                if (validPackagesListCount == 1) {
                    return validPackagesList.get(0);
                } else {
                    List<List<Package>> sortedValidPackagesListByWeight = validPackagesList.stream().sorted((l1, l2) -> {
                        int sortCondition = ListUtils.totalWeightOfPackageList(l2) - ListUtils.totalWeightOfPackageList(l1);
                        if (sortCondition == 0) {
                            sortCondition = ListUtils.totalDistanceOfPackageList(l1) - ListUtils.totalDistanceOfPackageList(l2);
                        }
                        return sortCondition;
                    }).collect(Collectors.toList());
                    return sortedValidPackagesListByWeight.get(0);
                }
            }
        }
        return new ArrayList<>();
    }

    public Map<Package, Double> dispatch(List<Package> packages) {
        Map<Package, Double> deliveredPackagesReport = packages.stream().collect(Collectors.toMap(p -> p, p -> Double.MAX_VALUE));

        while (packages.size() != 0 && vehicles.size() > 0) {
            Optional<Vehicle> vehicleOption = vehicles.stream().filter(Vehicle::isAvailable).min(Comparator.comparingDouble(Vehicle::availableIn));

            if (vehicleOption.isEmpty()) break;
            Vehicle vehicle = vehicleOption.get();
            List<Package> packagesForVehicle = findPackagesForVehicle(packages, vehicle);

            if (!packagesForVehicle.isEmpty()) {
                Map<Package, Double> packageDeliveryReport;
                if (packages.size() == packagesForVehicle.size()) {
                    packageDeliveryReport = vehicle.deliver(packagesForVehicle);
                } else {
                    packageDeliveryReport = vehicle.deliver(packagesForVehicle);
                }
                deliveredPackagesReport.putAll(packageDeliveryReport);
            }

            if (packagesForVehicle.size() > 0) packages.removeAll(packagesForVehicle);
            else vehicle.setUnavailable();
        }
        return deliveredPackagesReport;
    }
}
