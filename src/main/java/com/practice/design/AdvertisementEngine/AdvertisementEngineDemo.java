package com.practice.design.AdvertisementEngine;

import com.practice.design.AdvertisementEngine.engine.AdvertisementEngine;
import com.practice.design.AdvertisementEngine.model.AdCampaign;

import java.util.Map;

public class AdvertisementEngineDemo {
    private static final AdvertisementEngine engine = new AdvertisementEngine();

    public static void main(String[] args) {
        // Don't serve same ad, check for the last 10 ads.
        testAdRotation();
        // Don't serve an ad after budget exhaustion.
        testBudgetExhaustion();
        // Don't serve an ad more than 5 times globally in a 1 minute window.
        testGlobalServeLimit();
        // Serve same ad after 1 minute passed.
        testGlobalServeLimitWithWindow();
    }

    private static void testAdRotation() {
        System.out.println("\n[Ad Rotation Test]");
        String advertiser = engine.addAdvertiser("MainAdvertiser");
        engine.addBudget(advertiser, 500.0);
        Map<String, String> constraints = Map.of("interest", "sports");
        String user = createUserWithInterest("RotationUser", 25, "sports");
        for (int i = 1; i <= 12; i++)
            engine.createCampaign(advertiser, 5.0, "http://ad.com/" + i, "banner", 25, "CityX", constraints);
        loopServe(user, "CityX", 15);
    }

    private static void testBudgetExhaustion() {
        System.out.println("\n[Budget Exhaustion Test]");
        String advertiser = engine.addAdvertiser("LimitedBudget");
        engine.addBudget(advertiser, 30.0); // 3 ads at bid=10

        Map<String, String> constraints = Map.of("interest", "uniqueinterest");
        engine.createCampaign(advertiser, 10.0, "http://budget.com/ad", "banner", 29, "CityY", constraints);

        // Create 5 users, all eligible
        int served = 0;
        for (int i = 1; i <= 5; i++) {
            String userId = createUserWithInterest("BudgetUser" + i, 30, "uniqueinterest");
            AdCampaign ad = engine.matchAdvertisement(userId, "CityY");
            System.out.printf("Attempt %2d: user=%s, adUrl=%s\n", i, userId, ad != null ? ad.getUrl() : "None");
            if (ad != null) served++;
        }
        System.out.println("(Total served before budget ran out: " + served + ")");
    }

    private static void testGlobalServeLimit() {
        System.out.println("\n[Global Serve Limit Test]");
        String advertiser = engine.addAdvertiser("RateLimitTest");
        engine.addBudget(advertiser, 100.0);
        Map<String, String> constraints = Map.of("interest", "globalinterest");
        engine.createCampaign(advertiser, 5.0, "http://rate-limit.com/ad", "banner", 29, "CityZ", constraints);

        // Create multiple eligible users
        int users = 8;
        String[] userIds = new String[users];
        for (int i = 0; i < users; i++)
            userIds[i] = createUserWithInterest("RLUser" + i, 29, "globalinterest");

        // Try to serve to each user, should be allowed only 5 times IN TOTAL
        int globalServed = 0;
        for (int i = 1; i <= users; i++) {
            AdCampaign ad = engine.matchAdvertisement(userIds[i - 1], "CityZ");
            System.out.printf("Attempt %2d: user=%s, adUrl=%s\n", i, userIds[i - 1], ad != null ? ad.getUrl() : "None");
            if (ad != null) globalServed++;
        }
        System.out.println("(Ad served " + globalServed + " times globally; all further attempts in 1 min: None)");
    }

    // ad engine enforces both ‘global campaign limits in a window’ and ‘per-user no-repeat history.
    private static void testGlobalServeLimitWithWindow() {
        System.out.println("\n[Global Serve Limit Test With Time Window Reset]");
        String advertiser = engine.addAdvertiser("RateLimitTest2");
        engine.addBudget(advertiser, 100.0);
        Map<String, String> constraints = Map.of("interest", "globalinterest2");
        engine.createCampaign(advertiser, 5.0, "http://time-window.com/ad", "banner", 29, "TimeCity", constraints);

        int users = 8;
        String[] userIds = new String[users];
        for (int i = 0; i < users; i++)
            userIds[i] = createUserWithInterest("TWUser" + i, 29, "globalinterest2");

        // First round: reach the global serve cap (should serve only 5 users)
        int globalServedBefore = 0;
        for (int i = 1; i <= users; i++) {
            AdCampaign ad = engine.matchAdvertisement(userIds[i - 1], "TimeCity");
            System.out.printf("Attempt %2d (1st min): user=%s, adUrl=%s\n", i, userIds[i - 1], ad != null ? ad.getUrl() : "None");
            if (ad != null) globalServedBefore++;
        }
        System.out.println("(1st minute: Ad served " + globalServedBefore + " times globally; all further attempts returned None)");

        // Wait one minute to clear the global serve window
        System.out.println("Waiting 61 seconds for global serve window to reset...");
        try {
            Thread.sleep(61000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Second round: should again allow ad to be served up to 5 times
        int globalServedAfter = 0;
        for (int i = 1; i <= users; i++) {
            AdCampaign ad = engine.matchAdvertisement(userIds[i - 1], "TimeCity");
            System.out.printf("Attempt %2d (2nd min): user=%s, adUrl=%s\n", i, userIds[i - 1], ad != null ? ad.getUrl() : "None");
            if (ad != null) globalServedAfter++;
        }
        System.out.println("(2nd minute: Ad served " + globalServedAfter + " times globally; all further attempts returned None)");
    }

    private static String createUserWithInterest(String userId, int age, String interest) {
        int year = 2025 - age;
        int dob = year * 10000 + 101;             // YYYY0101
        engine.addUser(userId, dob, "any");
        engine.addAttribute(userId, "interest", interest);
        return userId;
    }


    private static void loopServe(String user, String city, int attempts) {
        for (int i = 1; i <= attempts; i++) {
            AdCampaign ad = engine.matchAdvertisement(user, city);
            System.out.printf("Attempt %2d: user=%s, adUrl=%s\n", i, user, ad != null ? ad.getUrl() : "None");
        }
    }
}
