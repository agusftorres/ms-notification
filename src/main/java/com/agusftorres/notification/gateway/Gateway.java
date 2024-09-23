package com.agusftorres.notification.gateway;

public class Gateway {
   public void send(String userId, String message) {

      System.out.println(String.format("sending message: %s to user: %s", message, userId));

   }

}
