package com.agusftorres.notification.domain;

public class RateLimitRule {
   private final int maxRequests;
   private final long timeWindowInMillis;

   public RateLimitRule(int maxRequests, long timeWindowInMillis) {
      this.maxRequests = maxRequests;
      this.timeWindowInMillis = timeWindowInMillis;
   }

   public int getMaxRequests() {
      return maxRequests;
   }

   public long getTimeWindowInMillis() {
      return timeWindowInMillis;
   }
}
