package com.example.delivery.utils;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static Package parsePackage(String input) {
        String[] packageInput = input.split(" ");
        String packageId = packageInput[0];
        int weight = Integer.parseInt(packageInput[1]);
        int distance = Integer.parseInt(packageInput[2]);
        String offerCode = null;
        try {
            offerCode = packageInput[3];
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return new Package(packageId, weight, distance, offerCode);
    }

    public static List<Vehicle> parseVehicles(String input) {
        String[] inputArray = input.split(" ");

        List<Vehicle> vehicles = new ArrayList<>();
        try {
            int nVehicles = Integer.parseInt(inputArray[0]);
            int maxSpeed = Integer.parseInt(inputArray[1]);
            int maxWeight = Integer.parseInt(inputArray[2]);
            for (int i = 0; i < nVehicles; i++) vehicles.add(new Vehicle(maxSpeed, maxWeight));
        } catch (Exception ignored) { }

        return vehicles;
    }
}
