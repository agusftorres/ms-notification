package com.agusftorres.notification;

import com.agusftorres.notification.commons.ERuleType;
import com.agusftorres.notification.gateway.Gateway;
import com.agusftorres.notification.repository.RuleService;
import com.agusftorres.notification.service.NotificationService;
import com.agusftorres.notification.service.impl.NotificationServiceImpl;

public class App {

   public static void main(String[] args) {
      NotificationService service = new NotificationServiceImpl(new Gateway(), new RuleService());

      //Use cases
      service.send(ERuleType.NEWS.name(), "1", "news 1");
      service.send(ERuleType.NEWS.name(), "1", "news 2");
      service.send(ERuleType.NEWS.name(), "1", "news 3");
      service.send(ERuleType.NEWS.name(), "2", "news 1");

      service.send(ERuleType.UPDATE.name(), "1", "update 1");
      service.send(ERuleType.UPDATE.name(), "1", "update 2");
      service.send(ERuleType.UPDATE.name(), "1", "update 3");

      service.send(ERuleType.MARKETING.name(), "1", "promo 1");
      service.send(ERuleType.MARKETING.name(), "1", "promo 2");
      service.send(ERuleType.MARKETING.name(), "1", "promo 3");
      service.send(ERuleType.MARKETING.name(), "1", "promo 4");

      service.send(ERuleType.FINANCIAL.name(), "1", "financial things");

   }
}
