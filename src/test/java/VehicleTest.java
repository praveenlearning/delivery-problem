import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}