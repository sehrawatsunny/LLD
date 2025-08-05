package com.practice.design.AdvertisementEngine.service;

import com.practice.design.AdvertisementEngine.model.AdCampaign;

import java.util.List;
import java.util.Map;

public interface CampaignService {
    String createCampaign(String advertiserId, double bidAmount, String url,
                          String type, int age, String city, Map<String, String> constraints);

    AdCampaign getCampaign(String campaignId);

    List<AdCampaign> getAllCampaigns();
}
