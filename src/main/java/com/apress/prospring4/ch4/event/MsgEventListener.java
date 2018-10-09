package com.apress.prospring4.ch4.event;


import org.springframework.context.ApplicationListener;

public class MsgEventListener implements ApplicationListener<MessageEvent> {

    public void onApplicationEvent(MessageEvent event) {
        MessageEvent ent = event;
        System.out.println("Received: " + ent.getMsg());
    }
}
