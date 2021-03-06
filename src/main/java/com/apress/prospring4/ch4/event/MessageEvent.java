package com.apress.prospring4.ch4.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
