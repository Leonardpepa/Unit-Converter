/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Leonard
 */
public class NumberHandler implements Convertable{

    private final Long temp;

    public NumberHandler(String number, String from) {
        temp = (Long)  convertToBase(number, from);
    }

    @Override
    public String convert(String to) {
        switch(to){
            case "DECIMAL":
               return convertToDecimal();
            case "BINARY":
               return convertToBinary();
            case "HEX":
               return convertToHex();
            case "OCTAL": 
               return convertToOctal();
        }
        return "";
    }
    
    public String convertToBinary(){
        return Long.toBinaryString(temp);
    }
    
    public String convertToOctal(){
        return Long.toOctalString(temp);
    }
    
    public String convertToHex(){
        return Long.toHexString(temp);
    }
    
    public String convertToDecimal(){
           return Long.toString(temp);
    }
    
    @Override
    public Number convertToBase(String number, String from) {
        Long intNumber = Long.MIN_VALUE;
        
        try {
            if (null != from) switch (from) {
                case "BINARY":
                    intNumber = Long.parseLong(number, 2);
                    break;
                case "OCTAL":
                    intNumber =  Long.parseLong(number, 8);
                    break;
                case "HEX":
                    intNumber = Long.parseLong(number, 16);
                    break;
                case "DECIMAL":
                    intNumber = Long.parseLong(number);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong input \n the numbers that are displayed are not correct \n please enter correct input to see the correct results", ButtonType.OK);
            alert.show();
        }
        return intNumber;
    }
}
