package com.practice.design.AdvertisementEngine.service.strategy;

import com.practice.design.AdvertisementEngine.model.AdCampaign;
import com.practice.design.AdvertisementEngine.model.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Filters eligible campaigns for a user in a city according to core business rules:
 * - MUST match city (case-insensitive)
 * - User age MUST be >= campaign's targetAge (strict minimum age requirement)
 * - User must NOT have seen this ad in last 10 fetches
 * - Campaign must NOT have exceeded its global serve rate
 * - All campaign constraints (e.g. interest/attribute) must match
 */
public class DefaultMatchingStrategy implements MatchingStrategy {

    @Override
    public List<AdCampaign> filterCandidates(List<AdCampaign> campaigns, User user, String city) {
        Objects.requireNonNull(campaigns, "Campaigns list cannot be null");
        Objects.requireNonNull(user, "User cannot be null");
        Objects.requireNonNull(city, "City cannot be null");

        if (campaigns.isEmpty()) {
            return Collections.emptyList();
        }

        int userAge = user.getAge();

        return campaigns.stream()
                .filter(campaign -> campaign.getTargetCity().equalsIgnoreCase(city.trim()))
                .filter(campaign -> userAge >= campaign.getTargetAge())
                .filter(campaign -> !user.hasSeenRecentAd(campaign.getCampaignId()))
                .filter(AdCampaign::canServeNow)
                .filter(campaign -> matchesConstraints(campaign, user))
                .collect(Collectors.toList());
    }

    //Check that all campaign constraints (e.g. interest=sports) exist in user attributes.
    private boolean matchesConstraints(AdCampaign campaign, User user) {
        Map<String, String> userAttributes = user.getAttributes();
        Map<String, String> constraints = campaign.getConstraints();

        if (constraints == null || constraints.isEmpty()) {
            return true;
        }

        if (userAttributes == null || userAttributes.isEmpty()) {
            return false;  // User has no attributes but campaign has constraints
        }

        return constraints.entrySet().stream()
                .allMatch(constraint -> {
                    String key = constraint.getKey();
                    String constraintValue = constraint.getValue();
                    String userValue = userAttributes.get(key);

                    return userValue != null &&
                            userValue.equalsIgnoreCase(constraintValue);
                });
    }
}