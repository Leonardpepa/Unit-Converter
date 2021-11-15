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
public class WeightHandler implements Convertable{
    private  double g;

    public WeightHandler(String number, String from) {
        g = (double) convertToBase(number.replace(",", "."), from);
    }
    
    @Override
    public String convert(String to){
        switch(to){
            case "G":
                return (new BigDecimal(convertToG())).toString();
            case "KG":
                return (new BigDecimal(convertToKg())).toString();
            case "MG":
                return (new BigDecimal(convertToMg())).toString();
            case "MCG":
                return (new BigDecimal(convertToMkg())).toString();
            case "LB":
                return (new BigDecimal(convertToLb())).toString();
        }
        return "";
    }
    
    public String convertToG(){
        return Double.toString(g);
    }
    
    public String convertToKg(){
        return Double.toString(g/1000);
    }
    
    public String convertToMg(){
        return Double.toString(g * 1000);
    }
    
    public String convertToMkg(){
        return Double.toString(g * 1000000);
    }
    
    public String convertToLb(){
        return Double.toString(g / 453.59237);
    }
    
    @Override
    public Number convertToBase(String number, String from){
           double temp = Double.NEGATIVE_INFINITY;
        try {
             temp = Double.parseDouble(number);
             
             if("KG".equals(from)){
                 temp *= 1000;
             }else if("MG".equals(from)){
                 temp /= 1000;
             }else if("MCG".equals(from)){
                 temp /= 1000000;
             }
             else if("LB".equals(from)){
                 temp *= 453.59237;
             }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong input \n the numbers that are displayed are not correct \n please enter correct input to see the correct results", ButtonType.OK);
            alert.show();
        }
        return temp;
    }
}

