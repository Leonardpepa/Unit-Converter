/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter.service;

import java.math.BigDecimal;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Leonard
 */
public class VolumeHandler implements Convertable{
    private final double m3;

    public VolumeHandler(String number, String from) {
        this.m3 =  (double) convertToBase(number.replace(",", "."), from);
    }
    
    @Override
    public String convert(String to){
        switch(to){
            case "M3":
                return (new BigDecimal(convertToM3())).toString();
            case "CM3":
                return (new BigDecimal(convertToCm3())).toString();
            case "FT3":
                return (new BigDecimal(convertToFt3())).toString();
            case "KM3":
                return (new BigDecimal(convertToKm3())).toString();
            case "L":
                return (new BigDecimal(convertToL())).toString();
        }
        return "";
    }
    
    public String convertToM3(){
        return Double.toString(m3);
    }
    
    public String convertToCm3(){
        return Double.toString(m3 * 1000000);
    }
    
    public String convertToFt3(){
        return Double.toString(m3 / 0.028316846592);
    }
    
    public String convertToKm3(){
        return Double.toString(((double) m3 / 1000000000));
    }
    
    public String convertToL(){
        return Double.toString(m3 * 1000);
    }
    
    
    
    

    @Override
    public Number convertToBase(String number, String from){
        double temp = Double.NEGATIVE_INFINITY;
        
        try {
             temp = Double.parseDouble(number);
             
             if(null != from)switch (from) {
                case "CM3":
                    temp *= 1000000;
                    break;
                case "FT3":
                    temp *= 0.028316846592;
                    break;
                case "KM3":
                    temp *= 1000000000;
                    break;
                case "L":
                    temp /= 1000;
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong input \n the numbers that are displayed are not correct \n please enter correct input to see the correct results", ButtonType.OK);
            alert.show();
        }
        return temp;
    }
    
    
}
