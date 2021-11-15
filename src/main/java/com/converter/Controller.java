/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter;

import com.converter.service.Convertable;
import com.converter.service.LengthHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import com.converter.service.NumberHandler;
import com.converter.service.VolumeHandler;
import com.converter.service.WeightHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Leonard
 */
public class Controller {
   
    @FXML
    private Pane root;

    @FXML
    private Pane pane;
    
    //changes vissibility to true in the right panel
    public void ShowPane(ActionEvent e){
        Button btnPressed = (Button) e.getSource();
        String btnId = btnPressed.getId();
        root.getChildren().forEach(item -> {
            if(item instanceof Pane){
                String paneId = item.getId().substring(0, item.getId().length() - "pane".length());
                if(btnId.equals(paneId)){
                    ((Pane)item).setVisible(true);
                    pane = ((Pane)item);
                }else{
                    ((Pane)item).setVisible(false);
                }
            }
        });
    }
    
    public void handleInputDynamicaly(KeyEvent e){
        TextField textField = (TextField)(e.getTarget());
        updateTextFields(textField.getText(), textField.getId());
    }
    
    public void handleEnter(ActionEvent e){
        TextField textField = (TextField) e.getSource();
        updateTextFields(textField.getText(), textField.getId());
    }
    
    public void updateTextFields(String input, String fromUnit){
        if("".equals(input)){
            return;
        }
        Convertable handler;
        handler = chooseHandler(pane,input, fromUnit.toUpperCase());
        pane.getChildren().forEach(children -> {
            if(children.getId() == null){
                return;
            }
            
            if(!(children instanceof TextField)){
                return;
            }
            
            String  toUnit = children.getId();
            
            if(fromUnit.equals(toUnit)){
                return;
            }

            ((TextField) children).setText(handler.convert(toUnit.toUpperCase()));

        });
    }
    
    /**  
     *    @param pane provide the pane that is open
     *    @param number provide the number that needs to be handled in String format
     *    @param fromUnit provide the unit of the number
     *    @return handler object that implements Convertable
     */
    public Convertable chooseHandler(Pane pane, String number, String fromUnit){
        switch(pane.getId()){
            case "numberPane":
                return new NumberHandler(number, fromUnit);
            case "volumePane":
                return new VolumeHandler(number, fromUnit);
            case "weightPane":
                return new WeightHandler(number, fromUnit);
            case "lengthPane":
                return new LengthHandler(number, fromUnit);
        }
        return null;
    }
  
}
