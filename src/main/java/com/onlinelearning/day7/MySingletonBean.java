package com.onlinelearning.day7;

import org.springframework.beans.factory.annotation.Autowired;

public class MySingletonBean {

    @Autowired
    private MyPrototypeBean prototypeBean;

    public void showMessage(){
        System.out.println("Hi, the time is "+prototypeBean.getDateTime());
    }
}