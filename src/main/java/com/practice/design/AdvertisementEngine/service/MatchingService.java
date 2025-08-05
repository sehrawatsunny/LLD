package com.practice.design.AdvertisementEngine.service;

import com.practice.design.AdvertisementEngine.model.AdCampaign;

public interface MatchingService {
    AdCampaign matchAdvertisement(String userId, String city);
}
