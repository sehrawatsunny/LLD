package com.practice.design.AdvertisementEngine.service.impl;

import com.practice.design.AdvertisementEngine.exception.EntityNotFoundException;
import com.practice.design.AdvertisementEngine.exception.InvalidParameterException;
import com.practice.design.AdvertisementEngine.model.AdCampaign;
import com.practice.design.AdvertisementEngine.service.AdvertiserService;
import com.practice.design.AdvertisementEngine.service.CampaignService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CampaignServiceImpl implements CampaignService {
    private final ConcurrentHashMap<String, AdCampaign> campaigns = new ConcurrentHashMap<>();

    private final AdvertiserService advertiserService;

    public CampaignServiceImpl(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Override
    public String createCampaign(String advertiserId, double bidAmount, String url,
                                 String type, int age, String city, Map<String, String> constraints) {

        validateCampaignParameters(advertiserId, bidAmount, url, type, age, city);

        // Verify advertiser exists
        advertiserService.getAdvertiser(advertiserId);

        String campaignId = UUID.randomUUID().toString();

        AdCampaign campaign = new AdCampaign(campaignId, advertiserId, bidAmount,
                url, type, age, city, constraints);

        campaigns.put(campaignId, campaign);
        return campaignId;
    }

    @Override
    public AdCampaign getCampaign(String campaignId) {
        AdCampaign campaign = campaigns.get(campaignId);
        if (campaign == null) {
            throw new EntityNotFoundException("Campaign", campaignId);
        }
        return campaign;
    }

    @Override
    public List<AdCampaign> getAllCampaigns() {
        return new ArrayList<>(campaigns.values());
    }

    private void validateCampaignParameters(String advertiserId, double bidAmount,
                                            String url, String type, int age, String city) {
        if (advertiserId == null || advertiserId.trim().isEmpty()) {
            throw new InvalidParameterException("advertiserId", "cannot be null or empty");
        }
        if (bidAmount <= 0) {
            throw new InvalidParameterException("bidAmount", "must be positive");
        }
        if (url == null || url.trim().isEmpty()) {
            throw new InvalidParameterException("url", "cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidParameterException("type", "cannot be null or empty");
        }
        if (age < 0 || age > 120) {
            throw new InvalidParameterException("age", "must be between 0 and 120");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidParameterException("city", "cannot be null or empty");
        }
    }
}

