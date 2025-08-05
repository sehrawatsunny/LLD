package com.practice.design.AdvertisementEngine.model;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdCampaign {
    private final String campaignId;
    private final String advertiserId;
    private final double bidAmount;
    private final String url;
    private final String type;
    private final int targetAge;
    private final String targetCity;
    private final Map<String, String> constraints;
    private final List<Instant> servedTimestamps;

    public AdCampaign(String campaignId, String advertiserId, double bidAmount,
                      String url, String type, int targetAge, String targetCity,
                      Map<String, String> constraints) {
        this.campaignId = campaignId;
        this.advertiserId = advertiserId;
        this.bidAmount = bidAmount;
        this.url = url;
        this.type = type;
        this.targetAge = targetAge;
        this.targetCity = targetCity;
        this.constraints = constraints != null ? new HashMap<>(constraints) : new HashMap<>();
        this.servedTimestamps = new CopyOnWriteArrayList<>();
    }

    // Getters
    public String getCampaignId() {
        return campaignId;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public int getTargetAge() {
        return targetAge;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public Map<String, String> getConstraints() {
        return new HashMap<>(constraints);
    }

    public synchronized void recordServing() {
        servedTimestamps.add(Instant.now());
    }

    public synchronized boolean canServeNow() {
        Instant oneMinuteAgo = Instant.now().minusSeconds(60);
        servedTimestamps.removeIf(timestamp -> timestamp.isBefore(oneMinuteAgo));
        return servedTimestamps.size() < 5;
    }
}
