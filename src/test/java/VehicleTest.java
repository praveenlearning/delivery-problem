import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    public void shouldParseVehiclesFromInput() {
        List<Vehicle> vehicles = Vehicle.parseVehicles("2 70 100");

        var expected = new ArrayList<>(){{
            add(new Vehicle(70, 100));
            add(new Vehicle(70, 100));
        }};

        assertEquals(expected, vehicles);
    }

    @Test
    public void shouldReturnZeroVehiclesForInvalidInput() {
        List<Vehicle> vehicles = Vehicle.parseVehicles("0 70 100");
        List<Vehicle> vehiclesOne = Vehicle.parseVehicles("X 70 100");
        List<Vehicle> vehiclesTwo = Vehicle.parseVehicles("0");

        assertEquals(0, vehicles.size());
        assertEquals(0, vehiclesOne.size());
        assertEquals(0, vehiclesTwo.size());
    }

    @Test
    public void calculateTimeToDeliverAPackage() {
        Package pkg = Package.parsePackage("P1 50 30");
        Vehicle vehicle = new Vehicle(15, 60);

        var timeToDeliver = vehicle.calculateTime(pkg);

        assertEquals(2, timeToDeliver);
    }

    @Test
    public void shouldReturnDeliveryReportForPackages() {
        List<Package> packages = new ArrayList<>() {{
            add(Package.parsePackage("P1 50 30"));
            add(Package.parsePackage("P2 120 80"));
            add(Package.parsePackage("P3 90 150"));
        }};
        Vehicle vehicle = new Vehicle(60, 300);

        var expected = new HashMap<>() {{
            put(packages.get(0), 0.5);
            put(packages.get(1), 1.33);
            put(packages.get(2), 2.5);
        }};
        Map<Package, Double> deliveryReport = vehicle.deliver(packages);

        assertEquals(expected, deliveryReport);
    }

    @Test
    public void shouldReturnDeliveryReportForPackagesGivenItHadPreviousDelivery() {
        List<Package> packages = new ArrayList<>() {{
            add(Package.parsePackage("P1 50 30"));
            add(Package.parsePackage("P2 120 80"));
            add(Package.parsePackage("P3 90 150"));
        }};

        List<Package> otherPackages = new ArrayList<>() {{
            add(Package.parsePackage("P1 45 75"));
            add(Package.parsePackage("P2 100 20"));
            add(Package.parsePackage("P3 60 180"));
        }};

        Vehicle vehicle = new Vehicle(60, 300);

        var expected = new HashMap<>() {{
            put(otherPackages.get(0), 6.25);
            put(otherPackages.get(1), 5.33);
            put(otherPackages.get(2), 8.0);
        }};
        vehicle.deliver(packages);
        Map<Package, Double> deliveryReport = vehicle.deliver(otherPackages);

        assertEquals(expected, deliveryReport);
    }
}