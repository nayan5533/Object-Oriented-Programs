/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.regexdemo;

import java.util.regex.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author nayan
 */


public class RegexDemo {
    public static void main(String[] args) {
        
        String message = "Hello <<name>>, We have your full name as <<full name>> in our system. "
                        + "Your contact number is 91-xxxxxxxxxx. "
                        + "Please, let us know in case of any clarification. "
                        + "Thank you BridgeLabz 01/01/2016.";

        
        String modifiedMessage = replacePlaceholders(message);

       
        System.out.println(modifiedMessage);
    }

    public static String replacePlaceholders(String message) {
      
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());

        
        message = message.replaceAll("<<name>>", "John"); 
        message = message.replaceAll("<<full name>>", "John Doe"); 
        message = message.replaceAll("91-\\d{10}", "91-9876543210"); 
        message = message.replaceAll("\\d{2}/\\d{2}/\\d{4}", currentDate); 

        return message;
    }
}

