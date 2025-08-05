package com.practice.design.AdvertisementEngine.service.impl;

import com.practice.design.AdvertisementEngine.exception.InsufficientBudgetException;
import com.practice.design.AdvertisementEngine.model.AdCampaign;
import com.practice.design.AdvertisementEngine.model.Advertiser;
import com.practice.design.AdvertisementEngine.model.User;
import com.practice.design.AdvertisementEngine.service.AdvertiserService;
import com.practice.design.AdvertisementEngine.service.CampaignService;
import com.practice.design.AdvertisementEngine.service.MatchingService;
import com.practice.design.AdvertisementEngine.service.UserService;
import com.practice.design.AdvertisementEngine.service.strategy.MatchingStrategy;

import java.util.Comparator;
import java.util.List;

public class MatchingServiceImpl implements MatchingService {
    private final UserService userService;
    private final AdvertiserService advertiserService;
    private final CampaignService campaignService;
    private final MatchingStrategy matchingStrategy;

    public MatchingServiceImpl(UserService userService, AdvertiserService advertiserService,
                               CampaignService campaignService, MatchingStrategy matchingStrategy) {
        this.userService = userService;
        this.advertiserService = advertiserService;
        this.campaignService = campaignService;
        this.matchingStrategy = matchingStrategy;
    }

    @Override
    public synchronized AdCampaign matchAdvertisement(String userId, String city) {
        User user = userService.getUser(userId);
        List<AdCampaign> allCampaigns = campaignService.getAllCampaigns();

        // Filter eligible campaigns using strategy
        List<AdCampaign> eligibleCampaigns = matchingStrategy.filterCandidates(allCampaigns, user, city);

        // Filter by budget availability
        List<AdCampaign> budgetFilteredCampaigns = eligibleCampaigns.stream()
                .filter(campaign -> {
                    Advertiser advertiser = advertiserService.getAdvertiser(campaign.getAdvertiserId());
                    return advertiser.hasSufficientBudget(campaign.getBidAmount());
                })
                .sorted(Comparator.comparingDouble(AdCampaign::getBidAmount).reversed())
                .collect(java.util.stream.Collectors.toList());

        if (budgetFilteredCampaigns.isEmpty()) {
            return null;
        }

        // Select highest bidding campaign
        AdCampaign selectedCampaign = budgetFilteredCampaigns.get(0);

        // Process the serving
        Advertiser advertiser = advertiserService.getAdvertiser(selectedCampaign.getAdvertiserId());
        if (!advertiser.deductBudget(selectedCampaign.getBidAmount())) {
            throw new InsufficientBudgetException(advertiser.getAdvertiserId(),
                    selectedCampaign.getBidAmount(), advertiser.getBudget());
        }

        selectedCampaign.recordServing();
        user.addViewedAd(selectedCampaign.getCampaignId());

        return selectedCampaign;
    }
}

