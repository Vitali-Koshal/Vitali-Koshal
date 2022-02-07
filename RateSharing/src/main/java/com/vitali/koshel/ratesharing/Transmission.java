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
public class Transmission {
    private String transmissionName;
    private int gearQty;
    private double[] gearsRatio;
    private int blockSpeed;
    public int transtype=0;
    public Transmission(int transtype){
        if (transtype==2){
            transmissionName="CATERPILLAR";
            this.transtype=transtype;
            gearQty=7;
            gearsRatio=new double[] {7,6.25,4.59,3.38,2.48,1.83,1.36,1};
            blockSpeed=1500;
        }
        else{
            if (transtype==1){
                transmissionName="ALLISON";
                this.transtype=transtype;
                gearQty=8;
                gearsRatio=new double[] {3.75,2.69,2.2,1.77,1.58,1.27,1,0.72};
                blockSpeed=1400;
            }
            else{
                transmissionName="default";
                this.transtype=0;
                gearQty=7;
                gearsRatio=new double[] {7,6.25,4.59,3.38,2.48,1.83,1.36,1};
                blockSpeed=1500;
            }
        }
        //System.out.println("Cozdana trans "+transmissionName);
    }
    
    public void setTransmissionName(String transmissionName){
        this.transmissionName=transmissionName;
    }
    public String getTransmissionName(){
        return transmissionName;
    }
    public void setGearQty(int gearQty){
        this.gearQty=gearQty;
    }
    public int getGearQty(){return gearQty;}
    
    public void setGearsRatio(double[] gearsRatio){
        this.gearsRatio=gearsRatio;
    }
    public double[] getGearsRatio(){return gearsRatio;}
    
    public void setBlockSpeed(int blockSpeed){
        this.blockSpeed=blockSpeed;
    }
    public int getBlockSpeed(){return blockSpeed;}
}
