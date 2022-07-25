package com.example.delivery.utils;

import com.example.delivery.model.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static <T> List<List<T>> findSubsets(List<T> list) {
        int n = list.size();
        List<List<T>> subsets = new ArrayList<>();
        for (int i = 1; i < (1<<n); i++) {
            List<T> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(list.get(j));
                }
            }
            subsets.add(subset);
        }
        return subsets;
    }

    public static <T> List<List<T>> filterBySize(List<List<T>> subsets, int size) {
        return subsets.stream()
                .filter(list -> list.size() == size)
                .collect(Collectors.toList());
    }

    public static int totalWeightOfPackageList(List<Package> packageList) {
        return packageList.stream().mapToInt(Package::getWeight).sum();
    }

    public static int totalDistanceOfPackageList(List<Package> packageList) {
        return packageList.stream().mapToInt(Package::getDistance).sum();
    }
}
