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
public class Engine {
    private String engineName;
    private int maxSpeed;
    private int minSpeed;
    
    public void setEngineName(String engineName){
        this.engineName=engineName;
    }
    public String getEngineName(){return engineName;}
    
    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed=maxSpeed;
    }
    public int getMaxSpeed(){return maxSpeed;}
    
    public void setMinSpeed(int minSpeed){
        this.minSpeed=minSpeed;
    }
    public int getMinSpeed(){return minSpeed;}
}
