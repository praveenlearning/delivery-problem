import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Vehicle {
    final int maxSpeed;
    final int maxWeight;
    private boolean isAvailable = true;
    private double availableIn = 0;

    public Vehicle(int maxSpeed, int maxWeight) {
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
    }

    public Map<Package, Double> deliver(List<Package> packages) {
        Map<Package, Double> deliveryReport0 = packages.stream().collect(Collectors.toMap(pkg -> pkg, this::calculateTime));

        Map<Package, Double> deliveryReport = packages.stream()
                .collect(Collectors.toMap(pkg -> pkg, pkg -> availableIn + calculateTime(pkg)));

        Double max = deliveryReport0.values().stream().max(Comparator.comparingDouble(value -> value)).get();
        availableIn += 2 * max;
        return deliveryReport;
    }

    public double calculateTime(Package pkg) {
        double value = (double) pkg.distance / maxSpeed;
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.FLOOR).doubleValue();
    }

    public static List<Vehicle> parseVehicles(String input) {
        String[] inputArray = input.split(" ");
        int nVehicles = Integer.parseInt(inputArray[0]);
        int maxSpeed = Integer.parseInt(inputArray[1]);
        int maxWeight = Integer.parseInt(inputArray[2]);

        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < nVehicles; i++) vehicles.add(new Vehicle(maxSpeed, maxWeight));
        return vehicles;
    }

    public void setUnavailable() {
        isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double availableIn() {
        return availableIn;
    }
}
