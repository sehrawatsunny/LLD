package com.practice.designpractice.PaymentsProcessingSystem.routing;

//
//public class GatewayRouter {
//    public PaymentGateway route(PaymentRequest request) {
//        if (request.getAmount() <= 1000) {
//            return new RazorpayGateway();
//        }
//        return new PayUGateway();
//    }
//}


import com.practice.designpractice.PaymentsProcessingSystem.gateway.PaymentGateway;

import java.util.HashMap;
import java.util.Map;

public class GatewayRouter {
    private final Map<String, PaymentGateway> gatewayMap = new HashMap<>();

    public void register(String method, PaymentGateway gateway) {
        gatewayMap.put(method.toLowerCase(), gateway);
    }

    public PaymentGateway getGateway(String method) {
        return gatewayMap.get(method.toLowerCase());
    }
}