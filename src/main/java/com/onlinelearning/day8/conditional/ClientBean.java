package com.onlinelearning.day8.conditional;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientBean {
    @Autowired
    private MyService myService;

    public MyService getMyService () {
        return myService;
    }
}