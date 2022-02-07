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
public class Pump {
    private String pumpName;
    private double plungerDiametr=100;
    private int plungerQty=3;
    private double plungerStroke;
    private double pumpEfficiency=1.0;
    private double pumpGearRatio=6.333333;//уточнить
    
    public void setPumpName(String pumpName){
        this.pumpName=pumpName;
    }
    public String getPumpName(){return pumpName;}
    public void setPlungerQty(int plungerQty){
        this.plungerQty=plungerQty;}
    public int getPlungerQty(){return this.plungerQty;}
    public double getPlungerStroke(){return plungerStroke;}
    public void setPlungerStroke(double plungerStroke){
        //System.out.println("plunger stroke is set");
        this.plungerStroke=plungerStroke;
    }
    public double getPlungerDiametr(){return plungerDiametr;}
    public void setPlungerDiametr(double plungerDiametr){
        //System.out.println("Plunger diameter is set");
        this.plungerDiametr=plungerDiametr;
    }
    public double getPumpEfficiency(){return pumpEfficiency;}
    public void setPumpEfficiency(double pumpEfficiency){
        this.pumpEfficiency=pumpEfficiency;
    }
    public double getPumpGearRatio(){return pumpGearRatio;}
    public void setPumpGearRatio(double pumpGearRatio){
        //System.out.println("Pump gear ratio is set");
        this.pumpGearRatio=pumpGearRatio;
    }
    //Расчет подачи за один оборот
    public double oneTurnRate(){
        double OneTurnRate=Math.PI*Math.pow(plungerDiametr/2, 2)*plungerStroke
                *plungerQty*pumpEfficiency/1000000;
        return OneTurnRate;
    }
    public void pumpInfo(){
        System.out.println("Pump "+pumpName+" characteristic is: ");
        System.out.println("Plunger diameter is " +plungerDiametr+" mm");
        System.out.println("Plunger stroke is " +plungerStroke+" mm");
        System.out.println("Number of plunger is " +plungerQty);
        System.out.println("Gear ratio is "+pumpGearRatio);
        System.out.println("One turn rate is "
                +String.format("%.3f", oneTurnRate())+" l");
        
    }
    
}
