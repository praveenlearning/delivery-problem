package com.example.delivery.utils;

import com.example.delivery.model.Offer;

import java.util.HashMap;
import java.util.Map;

public class OfferUtil {
    public static Map<String, Offer> allOffers = new HashMap<>() {{
        put("OFR001", new Offer(pkg -> pkg.getDistance() < 200 && pkg.getWeight() >= 70 && pkg. getWeight() <= 200, 10));
        put("OFR002", new Offer(pkg -> pkg.getDistance() >= 50 && pkg.getDistance() <= 150 && pkg.getWeight() >= 70 && pkg. getWeight() <= 200, 7));
        put("OFR003", new Offer(pkg -> pkg.getDistance() >= 50 && pkg.getDistance() <= 250 && pkg.getWeight() >= 10 && pkg. getWeight() <= 150, 5));
    }};
}
