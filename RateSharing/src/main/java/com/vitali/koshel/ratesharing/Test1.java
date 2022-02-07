/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitali.koshel.ratesharing;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author KoshelVR
 */
public class Test1 {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context=
            new ClassPathXmlApplicationContext("transmissionXMLConfig.xml");
            Transmission trans1=context.getBean("DEFAULT",Transmission.class );
            System.out.println(trans1.toString());
    context.close();
    }
    
}
