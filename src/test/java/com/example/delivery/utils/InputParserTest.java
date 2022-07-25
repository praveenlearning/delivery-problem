package com.example.delivery.utils;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    @Test
    void parsePackage() {
        Package pkg = new Package("PKG1", 50, 5, "OFR001");

        assertEquals(pkg, InputParser.parsePackage("PKG1 50 5 OFR001"));
    }

    @Test
    void parsePackageIfOfferCodeNotEntered() {
        Package pkg = new Package("PKG1", 50, 5, null);

        assertEquals(pkg, InputParser.parsePackage("PKG1 50 5"));
    }

    @Test
    public void shouldParseVehiclesFromInput() {
        List<Vehicle> vehicles = InputParser.parseVehicles("2 70 100");

        var expected = new ArrayList<>() {{
            add(new Vehicle(70, 100));
            add(new Vehicle(70, 100));
        }};

        assertEquals(expected, vehicles);
    }

    @Test
    public void shouldReturnZeroVehiclesForInvalidInput() {
        List<Vehicle> vehicles = InputParser.parseVehicles("0 70 100");
        List<Vehicle> vehiclesOne = InputParser.parseVehicles("X 70 100");
        List<Vehicle> vehiclesTwo = InputParser.parseVehicles("0");

        assertEquals(0, vehicles.size());
        assertEquals(0, vehiclesOne.size());
        assertEquals(0, vehiclesTwo.size());
    }
}
