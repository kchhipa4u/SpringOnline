package com.onlinelearning.day6;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FieldLevelGetterSetterDemo {

  private int userId;
  private String userName;
  private int userAge;
  
  public FieldLevelGetterSetterDemo(int userAge){
    this.userAge=userAge;
  }
  
}