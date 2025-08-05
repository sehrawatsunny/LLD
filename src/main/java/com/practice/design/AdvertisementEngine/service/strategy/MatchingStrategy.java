package com.practice.design.AdvertisementEngine.service.strategy;

import com.practice.design.AdvertisementEngine.model.AdCampaign;
import com.practice.design.AdvertisementEngine.model.User;

import java.util.List;

public interface MatchingStrategy {
    List<AdCampaign> filterCandidates(List<AdCampaign> campaigns, User user, String city);
}
