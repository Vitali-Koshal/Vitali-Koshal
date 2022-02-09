/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitali.koshel.ratesharing;

/**
 *
 * @author KoshelVR
 */
public class SpeedSet {
    SpeedSet(){}
    double speed=0;
    int gear=0;
    int state=0;//0 красное, 1 желтое, 2 зеленое..
    @Override
    public String toString(){
        if(state==0){
        return "Ne dopystimay peredacha! "+gear;}
        else {
            if(state==1){
                return "Dopystimay peredacha "+gear+", speed is "+speed;
            }
            else {return "!!!!!!!Super peredacha "+gear+", speed is "+speed;}
        }
        
    }
}
