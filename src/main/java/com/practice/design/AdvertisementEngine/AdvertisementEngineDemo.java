// src/main/java/com/practice/design/AdvertisementEngine/AdvertisementEngineDemo.java
package com.practice.design.AdvertisementEngine;

import com.practice.design.AdvertisementEngine.engine.AdvertisementEngine;
import com.practice.design.AdvertisementEngine.model.AdCampaign;

import java.util.HashMap;
import java.util.Map;

public class AdvertisementEngineDemo {

    public static void main(String[] args) {
        AdvertisementEngine engine = new AdvertisementEngine();

        // Test 1: Match a single user to a single campaign
        System.out.println("Test 1: Single Campaign Match");
        String adv1 = engine.addAdvertiser("Adv1");
        engine.addBudget(adv1, 20.0);

        engine.addUser("UserA", 19910101, "male");
        engine.addAttribute("UserA", "interest", "sports");

        Map<String, String> constraints1 = new HashMap<>();
        constraints1.put("interest", "sports");
        engine.createCampaign(adv1, 5.0, "http://adv1.com/ad1", "banner", 33, "CityA", constraints1);

        AdCampaign ad = engine.matchAdvertisement("UserA", "CityA");
        printMatch("UserA", ad);

        // Test 2: No repeat ad in last 10 fetches
        System.out.println("Test 2: No Repeat Ad in Last 10 Fetches");
        for (int i = 2; i <= 12; i++) {
            AdCampaign repeatedAd = engine.matchAdvertisement("UserA", "CityA");
            printAttempt(i, repeatedAd);
        }

        // Test 3: Multiple campaigns and ad rotation
        System.out.println("Test 3: Multiple Campaign Rotation");
        engine.createCampaign(adv1, 5.0, "http://adv1.com/ad2", "banner", 33, "CityA", constraints1);

        for (int i = 1; i <= 15; i++) {
            AdCampaign rotatedAd = engine.matchAdvertisement("UserA", "CityA");
            printAttempt(i, rotatedAd);
        }

        // Test 4: Budget exhaustion
        System.out.println("Test 4: Budget Exhaustion");
        engine.addUser("UserB", 19890101, "female");
        engine.addAttribute("UserB", "interest", "sports");

        AdCampaign adToExhaust = engine.matchAdvertisement("UserA", "CityA");
        if (adToExhaust != null) {
            // Reduce advertiser budget to simulate exhaustion
            engine.addBudget(adv1, -adToExhaust.getBidAmount() * 4);
        } else {
            System.out.println("No ad available to exhaust budget.");
        }

        // UserB tries to get ad, but budget is insufficient
        AdCampaign noBudgetAd = engine.matchAdvertisement("UserB", "CityA");
        printMatch("UserB", noBudgetAd);

        System.out.println("All tests completed.");
    }

    // Print result of ad match for a user
    private static void printMatch(String user, AdCampaign ad) {
        if (ad != null) {
            System.out.println("User " + user + " matched Ad URL: " + ad.getUrl() + " (Bid: " + ad.getBidAmount() + ")");
        } else {
            System.out.println("User " + user + " did NOT match any ad.");
        }
    }

    // Print result of each attempt to serve an ad
    private static void printAttempt(int attempt, AdCampaign ad) {
        if (ad != null) {
            System.out.println("Attempt " + attempt + ": Ad served: " + ad.getUrl());
        } else {
            System.out.println("Attempt " + attempt + ": No ad served");
        }
    }
}