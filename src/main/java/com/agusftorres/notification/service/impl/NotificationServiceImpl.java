package com.agusftorres.notification.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.agusftorres.notification.domain.RateLimitRule;
import com.agusftorres.notification.gateway.Gateway;
import com.agusftorres.notification.repository.RuleService;
import com.agusftorres.notification.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

   private static final String LIMIT_EXCEED = "Rate limit exceeded for notification type: %s";

   private static final String RULE_NOT_EXIST = "No rate limit rule found for notification type: %s. Consider add a rule for this kind of "
         + "notification to avoid abuse or spam.";

   private final Gateway gateway;

   private final RuleService ruleService;

   private final Map<String, RateLimitRule> rateLimitRules;

   private final Map<String, Map<String, List<Long>>> userNotificationHistory;

   public NotificationServiceImpl(Gateway gateway, RuleService ruleService) {
      this.gateway = gateway;
      this.ruleService = ruleService;
      this.rateLimitRules = ruleService.findRules();
      this.userNotificationHistory = new HashMap<>();
      ;
   }

   // TASK: IMPLEMENT this
   @Override
   public void send(String type, String userId, String message) {
      RateLimitRule rule = rateLimitRules.get(type);
      if (rule == null) {
         sendAlert(type, RULE_NOT_EXIST);
      } else {
         List<Long> timestamps = userNotificationHistory.computeIfAbsent(userId, k -> new HashMap<>()).computeIfAbsent(type, k -> new ArrayList<>());

         long now = System.currentTimeMillis();

         timestamps.removeIf(ts -> (now - ts) > rule.getTimeWindowInMillis());

         if (timestamps.size() >= rule.getMaxRequests()) {
            sendAlert(type, LIMIT_EXCEED);
            return;
         } else {
            timestamps.add(now);
         }
      }
      gateway.send(userId, message);
   }

   private void sendAlert(String type, String alertMessage) {
      System.out.println(String.format(alertMessage, type));
   }

}
