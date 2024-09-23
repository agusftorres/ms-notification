package com.agusftorres.notification.repository;

import java.util.HashMap;
import java.util.Map;

import com.agusftorres.notification.commons.ERuleType;
import com.agusftorres.notification.domain.RateLimitRule;

public class RuleService {
   public Map<String, RateLimitRule> findRules() {
      Map<String, RateLimitRule> rateLimitRules = new HashMap<>();

      //2 per min
      rateLimitRules.put(ERuleType.UPDATE.name(), new RateLimitRule(2, 60 * 1000));

      //1 per day
      rateLimitRules.put(ERuleType.NEWS.name(), new RateLimitRule(1, 24 * 60 * 60 * 1000));

      //3 per hour
      rateLimitRules.put(ERuleType.MARKETING.name(), new RateLimitRule(3, 60 * 60 * 1000));

      return rateLimitRules;
   }
}
