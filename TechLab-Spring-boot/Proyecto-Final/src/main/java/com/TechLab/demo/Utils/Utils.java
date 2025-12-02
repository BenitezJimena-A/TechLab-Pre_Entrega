package com.TechLab.demo.Utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

  public boolean isEmpty(String text) {
    return (text == null || text.isBlank());
  }

  public boolean doubleValidation(Double number) {
    return (number == null || number < 0);
  }

  public boolean integerValidation(Integer number) {
    return (number == null || number < 0);
  }
}
