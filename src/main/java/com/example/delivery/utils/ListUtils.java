package com.example.delivery.utils;

import com.example.delivery.model.Package;

import java.util.ArrayList;
import java.util.List;

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

    public static int totalWeightOfPackageList(List<Package> packageList) {
        return packageList.stream().mapToInt(Package::getWeight).sum();
    }

    public static int totalDistanceOfPackageList(List<Package> packageList) {
        return packageList.stream().mapToInt(Package::getDistance).sum();
    }
}
