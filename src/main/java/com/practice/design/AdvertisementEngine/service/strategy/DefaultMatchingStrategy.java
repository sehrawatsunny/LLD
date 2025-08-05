package com.practice.design.AdvertisementEngine.service.strategy;

import com.practice.design.AdvertisementEngine.model.AdCampaign;
import com.practice.design.AdvertisementEngine.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultMatchingStrategy implements MatchingStrategy {
    @Override
    public List<AdCampaign> filterCandidates(List<AdCampaign> campaigns, User user, String city) {
        return campaigns.stream()
                .filter(campaign -> campaign.getTargetCity().equalsIgnoreCase(city))
                .filter(campaign -> Math.abs(user.getAge() - campaign.getTargetAge()) <= 2) // age group match
                .filter(campaign -> matchesConstraints(campaign, user))
                .filter(campaign -> !user.hasSeenRecentAd(campaign.getCampaignId()))
                .filter(AdCampaign::canServeNow)
                .collect(Collectors.toList());
    }

    private boolean matchesBasicCriteria(AdCampaign campaign, User user, String city) {
        return campaign.getTargetAge() == user.getAge() &&
                campaign.getTargetCity().equalsIgnoreCase(city);
    }

    private boolean matchesConstraints(AdCampaign campaign, User user) {
        Map<String, String> userAttributes = user.getAttributes();
        return campaign.getConstraints().entrySet().stream()
                .allMatch(constraint -> {
                    String userValue = userAttributes.get(constraint.getKey());
                    return userValue != null && userValue.equalsIgnoreCase(constraint.getValue());
                });
    }
}