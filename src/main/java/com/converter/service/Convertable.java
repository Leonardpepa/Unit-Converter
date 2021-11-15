/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter.service;

/**
 *
 * @author Leonard
 */
public interface Convertable {
    String convert(String to);
    Number convertToBase(String number, String from);
}
