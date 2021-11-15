 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter.service;

import java.math.BigDecimal;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Leonard
 */
public class LengthHandler implements Convertable{
    
    private final double cm;
    
    public LengthHandler(String number, String from) {
        cm = (double) convertToBase(number.replace(",", "."), from);
    }
    
    @Override
    public String convert(String to){
        switch(to){
            case "CM":
                return (new BigDecimal(convertToCm())).toString();
            case "MM":
                return (new BigDecimal(convertToMm())).toString();
            case "M":
                return (new BigDecimal(convertToM())).toString();
            case "KM":
                return (new BigDecimal(convertToKm())).toString();
            case "IN":
                return (new BigDecimal( convertToInches())).toString();
            case "FT":
                return (new BigDecimal(ConvertToFeet()).toString());
        }
        return "";
    }
    
    
    public String convertToCm(){
        return Double.toString(cm);
    }
    
    public String convertToMm(){
        return Double.toString(cm * 10);
    }
    
    public String convertToM(){
        return Double.toString(cm/100);
    }
    
    public String convertToKm(){
        return Double.toString(cm/100000);
    }
    
    public String convertToInches(){
        return Double.toString(cm/2.54);
    }
    
    public String ConvertToFeet(){    
        return Double.toString(cm/30.48);
    }
    
    @Override
    public Number convertToBase(String number, String from){
          double temp = Double.NEGATIVE_INFINITY;
        try {
             temp = Double.parseDouble(number);
             
             if(null != from)switch (from) {
                  case "MM":
                      temp /= 10;
                      break;
                  case "M":
                      temp *= 100;
                      break;
                  case "KM":
                      temp *= 100000;
                      break;
                  case "IN":
                      temp *= 2.54;
                      break;
                  case "FT":
                      temp *= 30.48;
                      break;
                  default:
                      break;
              }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR, "Wrong input \n the numbers that are displayed are not correct \n please enter correct input to see the correct results", ButtonType.OK);
            alert.show();
        }
        return temp;
    }
    
}
