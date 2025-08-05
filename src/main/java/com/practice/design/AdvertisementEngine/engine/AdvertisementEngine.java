package com.practice.design.AdvertisementEngine.engine;

import com.practice.design.AdvertisementEngine.model.AdCampaign;
import com.practice.design.AdvertisementEngine.service.AdvertiserService;
import com.practice.design.AdvertisementEngine.service.CampaignService;
import com.practice.design.AdvertisementEngine.service.MatchingService;
import com.practice.design.AdvertisementEngine.service.UserService;
import com.practice.design.AdvertisementEngine.service.impl.AdvertiserServiceImpl;
import com.practice.design.AdvertisementEngine.service.impl.CampaignServiceImpl;
import com.practice.design.AdvertisementEngine.service.impl.MatchingServiceImpl;
import com.practice.design.AdvertisementEngine.service.impl.UserServiceImpl;
import com.practice.design.AdvertisementEngine.service.strategy.DefaultMatchingStrategy;
import com.practice.design.AdvertisementEngine.service.strategy.MatchingStrategy;

import java.util.Map;

public class AdvertisementEngine {
    private final AdvertiserService advertiserService;
    private final UserService userService;
    private final CampaignService campaignService;
    private final MatchingService matchingService;

    public AdvertisementEngine() {
        this.advertiserService = new AdvertiserServiceImpl();
        this.userService = new UserServiceImpl();
        this.campaignService = new CampaignServiceImpl(advertiserService);

        MatchingStrategy defaultStrategy = new DefaultMatchingStrategy();
        this.matchingService = new MatchingServiceImpl(userService, advertiserService,
                campaignService, defaultStrategy);
    }

    // Advertiser operations
    public String addAdvertiser(String name) {
        return advertiserService.addAdvertiser(name);
    }

    public void addBudget(String advertiserId, double budget) {
        advertiserService.addBudget(advertiserId, budget);
    }

    // User operations
    public void addUser(String userId, int dob, String gender) {
        userService.addUser(userId, dob, gender);
    }

    public void addAttribute(String userId, String key, String value) {
        userService.addAttribute(userId, key, value);
    }

    // Campaign operations
    public String createCampaign(String advertiserId, double bidAmount, String url,
                                 String type, int age, String city, Map<String, String> constraints) {
        return campaignService.createCampaign(advertiserId, bidAmount, url, type, age, city, constraints);
    }

    // Core matching operation
    public AdCampaign matchAdvertisement(String userId, String city) {
        return matchingService.matchAdvertisement(userId, city);
    }
}

