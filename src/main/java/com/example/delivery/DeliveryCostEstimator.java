package com.example.delivery;

import com.example.delivery.model.Package;
import com.example.delivery.service.PackageService;
import com.example.delivery.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryCostEstimator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input1 = scanner.nextLine().split(" ");
        int baseCost = Integer.parseInt(input1[0]);
        int packagesCount = Integer.parseInt(input1[1]);

        List<Package> packages = new ArrayList<>();

        for (int i = 0; i < packagesCount; i++) {
            String input = scanner.nextLine();
            Package pkg = InputParser.parsePackage(input);
            packages.add(pkg);
        }

        PackageService packageService = new PackageService(baseCost);

        packages.forEach(pkg -> System.out.println(packageService.costReport(pkg)));
    }
}
