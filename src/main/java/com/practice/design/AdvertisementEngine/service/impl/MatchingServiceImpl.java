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

        // Sort by bid amount first
        // If the bid amount is higher, there are more chances of serving that campaign.
        List<AdCampaign> sortedCampaigns = eligibleCampaigns.stream()
                .sorted(Comparator.comparingDouble(AdCampaign::getBidAmount).reversed())
                .collect(java.util.stream.Collectors.toList());

        // Try campaigns in decreasing order of bidAmount, until one found.
        // i.e., Select the highest bidding campaign
        for (AdCampaign campaign : sortedCampaigns) {
            Advertiser advertiser = advertiserService.getAdvertiser(campaign.getAdvertiserId());

            // Try to deduct budget
            // Atomically deduct the budget if possible and return success
            if (advertiser.deductBudget(campaign.getBidAmount())) {
                // To record campaign serving globally.
                campaign.recordServing();
                // To record a user's past 10 campaigns.
                user.addViewedAd(campaign.getCampaignId());
                return campaign;
            }
        }
        return null;
    }
}

