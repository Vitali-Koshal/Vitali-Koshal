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
public class PumpUnit implements Comparable<PumpUnit>{
    private String pumpUnitName;
    private int minAllowSpeed;
    private int maxAllowSpeed;
    private int middleSpeed;//желательная минимальная скорость
    public int blockTransSpeed;
    public int engineType=0;
    public boolean state=false;
    SpeedSet[] speedSet;
    
    double pumpLevel=0;
    int windowNumber=0;
    Engine engine;
    Transmission transmission;
    Pump pump;
    public PumpUnit(int number){
        windowNumber=number;
        speedSet=new SpeedSet[10];
        for(int i=0;i<speedSet.length;i++){
            speedSet[i]=new SpeedSet();
        }
        pumpUnitName="default";
        transmission=new Transmission(1);
        pump=new Pump();
    }
    public void setMinAllowSpeed(int minAllowSpeed){
        this.minAllowSpeed=minAllowSpeed;
    }
    public int getMinAllowSpeed(){return minAllowSpeed;}
    
    public void setMaxAllowSpeed(int maxAllowSpeed){
        this.maxAllowSpeed=maxAllowSpeed;
    }
    public int getMaxAllowSpeed(){return maxAllowSpeed;}
    
    public int getMiddleSpeed(){return middleSpeed;}
    public void setMiddleSpeed(int middleSpeed){
        this.middleSpeed=middleSpeed;
    }
    
    
    public void setPump(String pumpName){
        ClassPathXmlApplicationContext context=
            new ClassPathXmlApplicationContext("applicationContext.xml");
            if (pumpName.equals("SPM2250_4.5_inches")){
                pump=context.getBean("SPM2250_4.5_inches",Pump.class );}
            else {
                if (pumpName.equals("SPM2250_4_inches")){
                    pump=context.getBean("SPM2250_4_inches",Pump.class );
                }
                else {pump=context.getBean("default",Pump.class );}
            }
            
    context.close();
    }
    public void setEngine(int engineType){
        ClassPathXmlApplicationContext context=
            new ClassPathXmlApplicationContext("applicationContext.xml");
        if (engineType==1){
            this.engineType=1;
            engine=context.getBean("1",Engine.class);
        }
        else {
            if(engineType==2){
                this.engineType=2;
                engine=context.getBean("2",Engine.class);
            }
            else {
                this.engineType=0;
                engine=context.getBean("0",Engine.class);}
        }
        minAllowSpeed=engine.getMinSpeed();
        maxAllowSpeed=engine.getMaxSpeed();
        
        context.close();
    }
    public void setTransmission(int transtype){
        transmission=new Transmission(transtype);
        
        blockTransSpeed=transmission.getBlockSpeed();
        middleSpeed=(blockTransSpeed+maxAllowSpeed)/2;
    }
    public void copypump(PumpUnit pumpUnit){
          //System.out.println("process ............"+blockTransSpeed);
          pumpUnitName=pumpUnit.getPumpUnitName();
          //pump.setPlungerDiametr(pumpUnit.pump.getPlungerDiametr());
          pump=pumpUnit.pump;
          engine=pumpUnit.engine;
          engineType=pumpUnit.engineType;
          engine.setEngineName(pumpUnit.engine.getEngineName());
          transmission=pumpUnit.transmission;
          minAllowSpeed=pumpUnit.engine.getMinSpeed();
          maxAllowSpeed=pumpUnit.engine.getMaxSpeed();
          middleSpeed=pumpUnit.middleSpeed;
          blockTransSpeed=pumpUnit.blockTransSpeed;
        
    }
    public String getPumpUnitName(){return pumpUnitName;}
    public void setPumpUnitName(String pumpUnitName){
        this.pumpUnitName=pumpUnitName;
    }
    public double minRate(){
        double minRate=pump.oneTurnRate()*pump.getPumpEfficiency()*transmission.getBlockSpeed()
                /transmission.getGearsRatio()[0]
                /pump.getPumpGearRatio();
        return minRate;
    }
    public double maxRate(){
        double maxRate=pump.oneTurnRate()*pump.getPumpEfficiency()*maxAllowSpeed
                /transmission.getGearsRatio()[transmission.getGearsRatio().length-1]
                /pump.getPumpGearRatio();
        return maxRate;
    }

    @Override
    public int compareTo(PumpUnit o) {
        //Сортируем первые самые эффективные и большой макс расход 
        if ((int)this.pump.getPumpEfficiency()*100!=(int)o.pump.getPumpEfficiency()*100){
        return -(int)this.pump.getPumpEfficiency()*100+(int)o.pump.getPumpEfficiency()*100;}
        else {return (int)(-this.minRate()+o.minRate());}
    }
    public SpeedSet[] ratecalcul(double rate){
        //System.out.println(pump.oneTurnRate()+" odin oborot");
        for(int k=0;k<transmission.getGearsRatio().length;k++){
            speedSet[k].speed=rate/pump.oneTurnRate()*pump.getPumpGearRatio()/pump.getPumpEfficiency()
                           *transmission.getGearsRatio()[k];
            speedSet[k].gear=k+1;
            if(speedSet[k].speed<(transmission.getBlockSpeed()-1)
                   || speedSet[k].speed>(getMaxAllowSpeed()+1)){
                        speedSet[k].state=0;
                }
                else {
                    if(speedSet[k].speed<getMiddleSpeed()){
                          speedSet[k].state=1; 
                    }
                       else{speedSet[k].state=2;
                       
                       }
                   }
            //System.out.println(speedSet[k].toString());
        }  
    
    return  speedSet;      
        
    }
}
