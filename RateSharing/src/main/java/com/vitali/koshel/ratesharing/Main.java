/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitali.koshel.ratesharing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author KoshelVR
 */
public class Main extends javax.swing.JFrame implements WindowListener{
   
    
    public static String tempName;
    public static boolean setAllstate=false;
    public static int engineType=0;
    public static int pumpnumber=20;
    public static int transtype=0;
    public static double pumpEfficiency=1.0;
    ArrayList<JPanel> pumpPanel;
    List<PumpUnit> pumpUnitList;
    JLabel[] pumpName;
    PumpUnit[] pumpUnit;
    JTextField[] engineSpeed;
    JTextField[] rateField;
    JTextField[] transGear;
    double globalMaxRate;
    double globalMinRate;
    boolean process=false;
    double shifting=0;//смещение расхода для нахождения других наборов
    boolean pumpingOn;
    SetVariant[] oldSet;
    /**
     * Creates new form Main
     */
    int qcounter1=0;
    public Main() {
        addWindowListener(this);
        
        initComponents();
        pumpPanel=new ArrayList<>();
        pumpPanel.add(jPanelPump1);
        pumpPanel.add(jPanelPump2);
        pumpPanel.add(jPanelPump3);
        pumpPanel.add(jPanelPump4);
        pumpPanel.add(jPanelPump5);
        pumpPanel.add(jPanelPump6);
        pumpPanel.add(jPanelPump7);
        pumpPanel.add(jPanelPump8);
        pumpPanel.add(jPanelPump9);
        pumpPanel.add(jPanelPump10);
        pumpPanel.add(jPanelPump11);
        pumpPanel.add(jPanelPump12);
        pumpPanel.add(jPanelPump13);
        pumpPanel.add(jPanelPump14);
        pumpPanel.add(jPanelPump15);
        pumpPanel.add(jPanelPump16);
        for(JPanel panel:pumpPanel){
            panel.setVisible(false);
        }
        pumpName=new JLabel[16];
        pumpName[0]=jLabelPump1Name;
        pumpName[1]=jLabelPump2Name;
        pumpName[2]=jLabelPump3Name;
        pumpName[3]=jLabelPump4Name;
        pumpName[4]=jLabelPump5Name;
        pumpName[5]=jLabelPump6Name;
        pumpName[6]=jLabelPump7Name;
        pumpName[7]=jLabelPump8Name;
        pumpName[8]=jLabelPump9Name;
        pumpName[9]=jLabelPump10Name;
        pumpName[10]=jLabelPump11Name;
        pumpName[11]=jLabelPump12Name;
        pumpName[12]=jLabelPump13Name;
        pumpName[13]=jLabelPump14Name;
        pumpName[14]=jLabelPump15Name;
        pumpName[15]=jLabelPump16Name;
        pumpUnit=new PumpUnit[16];
        pumpUnit[0]=new PumpUnit(0);
        pumpUnit[1]=new PumpUnit(1);
        pumpUnit[2]=new PumpUnit(2);
        pumpUnit[3]=new PumpUnit(3);
        pumpUnit[4]=new PumpUnit(4);
        pumpUnit[5]=new PumpUnit(5);
        pumpUnit[6]=new PumpUnit(6);
        pumpUnit[7]=new PumpUnit(7);
        pumpUnit[8]=new PumpUnit(8);
        pumpUnit[9]=new PumpUnit(9);
        pumpUnit[10]=new PumpUnit(10);
        pumpUnit[11]=new PumpUnit(11);
        pumpUnit[12]=new PumpUnit(12);
        pumpUnit[13]=new PumpUnit(13);
        pumpUnit[14]=new PumpUnit(14);
        pumpUnit[15]=new PumpUnit(15);
        
        engineSpeed=new JTextField[16]; 
        engineSpeed[0]=jTextField1RPM;
        engineSpeed[1]=jTextField2RPM;
        engineSpeed[2]=jTextField3RPM;
        engineSpeed[3]=jTextField4RPM;
        engineSpeed[4]=jTextField5RPM;
        engineSpeed[5]=jTextField6RPM;
        engineSpeed[6]=jTextField7RPM;
        engineSpeed[7]=jTextField8RPM;
        engineSpeed[8]=jTextField9RPM;
        engineSpeed[9]=jTextField10RPM;
        engineSpeed[10]=jTextField11RPM;
        engineSpeed[11]=jTextField12RPM;
        engineSpeed[12]=jTextField13RPM;
        engineSpeed[13]=jTextField14RPM;
        engineSpeed[14]=jTextField15RPM;
        engineSpeed[15]=jTextField16RPM;
        
        rateField=new JTextField[16];
        rateField[0]=jTextField1Rate;
        rateField[1]=jTextField2Rate;
        rateField[2]=jTextField3Rate;
        rateField[3]=jTextField4Rate;
        rateField[4]=jTextField5Rate;
        rateField[5]=jTextField6Rate;
        rateField[6]=jTextField7Rate;
        rateField[7]=jTextField8Rate;
        rateField[8]=jTextField9Rate;
        rateField[9]=jTextField10Rate;
        rateField[10]=jTextField11Rate;
        rateField[11]=jTextField12Rate;
        rateField[12]=jTextField13Rate;
        rateField[13]=jTextField14Rate;
        rateField[14]=jTextField15Rate;
        rateField[15]=jTextField16Rate;
        
        transGear=new JTextField[16];
        transGear[0]=jTextField1Gear;
        transGear[1]=jTextField2Gear;
        transGear[2]=jTextField3Gear;
        transGear[3]=jTextField4Gear;
        transGear[4]=jTextField5Gear;
        transGear[5]=jTextField6Gear;
        transGear[6]=jTextField7Gear;
        transGear[7]=jTextField8Gear;
        transGear[8]=jTextField9Gear;
        transGear[9]=jTextField10Gear;
        transGear[10]=jTextField11Gear;
        transGear[11]=jTextField12Gear;
        transGear[12]=jTextField13Gear;
        transGear[13]=jTextField14Gear;
        transGear[14]=jTextField15Gear;
        transGear[15]=jTextField16Gear;
        for (int i=0;i<pumpUnit.length;i++){
            pumpUnit[i].windowNumber=i;
        }
        for (int i=0;i<pumpName.length;i++){
            pumpName[i].setText("Pump "+(i+1)+" "+pumpUnit[0].getPumpUnitName());
        }
        
    }
public void rateCalculation(){
    //Находим минимальное и максимальное значение расхода для флота насосак
    System.out.println("Find max min rate function working............."+ jPumpQtyField.getText());
    globalMaxRate=0;
    for (int i=0;i<Integer.parseInt(jPumpQtyField.getText());i++){
        System.out.println("Max="+i+"= "+pumpUnit[i].maxRate());
        globalMaxRate=globalMaxRate+pumpUnit[i].maxRate();
    }
    
    globalMinRate=globalMaxRate;
    for (int i=0;i<Integer.parseInt(jPumpQtyField.getText());i++){
        System.out.println("Min="+i+"= "+pumpUnit[i].minRate()+ "...."+pumpUnit[i].blockTransSpeed);
        if(pumpUnit[i].minRate()<globalMinRate){
            globalMinRate=pumpUnit[i].minRate();
        }
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPumpQtyField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelPump2 = new javax.swing.JPanel();
        jLabelPump2Name = new javax.swing.JLabel();
        jButtonPump2Set = new javax.swing.JButton();
        jLabel2RPM = new javax.swing.JLabel();
        jLabel2Gear = new javax.swing.JLabel();
        jLabel2Rate = new javax.swing.JLabel();
        jTextField2RPM = new javax.swing.JTextField();
        jTextField2Gear = new javax.swing.JTextField();
        jTextField2Rate = new javax.swing.JTextField();
        jPanelPump1 = new javax.swing.JPanel();
        jLabelPump1Name = new javax.swing.JLabel();
        jButtonPump1Set = new javax.swing.JButton();
        jLabel1RPM = new javax.swing.JLabel();
        jLabel1Gear = new javax.swing.JLabel();
        jLabel1Rate = new javax.swing.JLabel();
        jTextField1RPM = new javax.swing.JTextField();
        jTextField1Gear = new javax.swing.JTextField();
        jTextField1Rate = new javax.swing.JTextField();
        jPanelPump3 = new javax.swing.JPanel();
        jLabelPump3Name = new javax.swing.JLabel();
        jButtonPump1Set4 = new javax.swing.JButton();
        jLabel1RPM4 = new javax.swing.JLabel();
        jLabel1Gear4 = new javax.swing.JLabel();
        jLabel1Rate4 = new javax.swing.JLabel();
        jTextField3RPM = new javax.swing.JTextField();
        jTextField3Gear = new javax.swing.JTextField();
        jTextField3Rate = new javax.swing.JTextField();
        jPanelPump4 = new javax.swing.JPanel();
        jLabelPump4Name = new javax.swing.JLabel();
        jButtonPump1Set5 = new javax.swing.JButton();
        jLabel1RPM5 = new javax.swing.JLabel();
        jLabel1Gear5 = new javax.swing.JLabel();
        jLabel1Rate5 = new javax.swing.JLabel();
        jTextField4RPM = new javax.swing.JTextField();
        jTextField4Gear = new javax.swing.JTextField();
        jTextField4Rate = new javax.swing.JTextField();
        jPanelPump8 = new javax.swing.JPanel();
        jLabelPump8Name = new javax.swing.JLabel();
        jButtonPump1Set6 = new javax.swing.JButton();
        jLabel1RPM6 = new javax.swing.JLabel();
        jLabel1Gear6 = new javax.swing.JLabel();
        jLabel1Rate6 = new javax.swing.JLabel();
        jTextField8RPM = new javax.swing.JTextField();
        jTextField8Gear = new javax.swing.JTextField();
        jTextField8Rate = new javax.swing.JTextField();
        jPanelPump7 = new javax.swing.JPanel();
        jLabelPump7Name = new javax.swing.JLabel();
        jButtonPump1Set7 = new javax.swing.JButton();
        jLabel1RPM7 = new javax.swing.JLabel();
        jLabel1Gear7 = new javax.swing.JLabel();
        jLabel1Rate7 = new javax.swing.JLabel();
        jTextField7RPM = new javax.swing.JTextField();
        jTextField7Gear = new javax.swing.JTextField();
        jTextField7Rate = new javax.swing.JTextField();
        jPanelPump9 = new javax.swing.JPanel();
        jLabelPump9Name = new javax.swing.JLabel();
        jButtonPump1Set8 = new javax.swing.JButton();
        jLabel1RPM8 = new javax.swing.JLabel();
        jLabel1Gear8 = new javax.swing.JLabel();
        jLabel1Rate8 = new javax.swing.JLabel();
        jTextField9RPM = new javax.swing.JTextField();
        jTextField9Gear = new javax.swing.JTextField();
        jTextField9Rate = new javax.swing.JTextField();
        jPanelPump10 = new javax.swing.JPanel();
        jLabelPump10Name = new javax.swing.JLabel();
        jButtonPump1Set9 = new javax.swing.JButton();
        jLabel1RPM9 = new javax.swing.JLabel();
        jLabel1Gear9 = new javax.swing.JLabel();
        jLabel1Rate9 = new javax.swing.JLabel();
        jTextField10RPM = new javax.swing.JTextField();
        jTextField10Gear = new javax.swing.JTextField();
        jTextField10Rate = new javax.swing.JTextField();
        jPanelPump12 = new javax.swing.JPanel();
        jLabelPump12Name = new javax.swing.JLabel();
        jButtonPump1Set10 = new javax.swing.JButton();
        jLabel1RPM10 = new javax.swing.JLabel();
        jLabel1Gear10 = new javax.swing.JLabel();
        jLabel1Rate10 = new javax.swing.JLabel();
        jTextField12RPM = new javax.swing.JTextField();
        jTextField12Gear = new javax.swing.JTextField();
        jTextField12Rate = new javax.swing.JTextField();
        jPanelPump11 = new javax.swing.JPanel();
        jLabelPump11Name = new javax.swing.JLabel();
        jButtonPump1Set11 = new javax.swing.JButton();
        jLabel1RPM11 = new javax.swing.JLabel();
        jLabel1Gear11 = new javax.swing.JLabel();
        jLabel1Rate11 = new javax.swing.JLabel();
        jTextField11RPM = new javax.swing.JTextField();
        jTextField11Gear = new javax.swing.JTextField();
        jTextField11Rate = new javax.swing.JTextField();
        jPanelPump6 = new javax.swing.JPanel();
        jLabelPump6Name = new javax.swing.JLabel();
        jButtonPump1Set12 = new javax.swing.JButton();
        jLabel1RPM12 = new javax.swing.JLabel();
        jLabel1Gear12 = new javax.swing.JLabel();
        jLabel1Rate12 = new javax.swing.JLabel();
        jTextField6RPM = new javax.swing.JTextField();
        jTextField6Gear = new javax.swing.JTextField();
        jTextField6Rate = new javax.swing.JTextField();
        jPanelPump13 = new javax.swing.JPanel();
        jLabelPump13Name = new javax.swing.JLabel();
        jButtonPump1Set14 = new javax.swing.JButton();
        jLabel1RPM14 = new javax.swing.JLabel();
        jLabel1Gear14 = new javax.swing.JLabel();
        jLabel1Rate14 = new javax.swing.JLabel();
        jTextField13RPM = new javax.swing.JTextField();
        jTextField13Gear = new javax.swing.JTextField();
        jTextField13Rate = new javax.swing.JTextField();
        jPanelPump5 = new javax.swing.JPanel();
        jLabelPump5Name = new javax.swing.JLabel();
        jButtonPump1Set13 = new javax.swing.JButton();
        jLabel1RPM13 = new javax.swing.JLabel();
        jLabel1Gear13 = new javax.swing.JLabel();
        jLabel1Rate13 = new javax.swing.JLabel();
        jTextField5RPM = new javax.swing.JTextField();
        jTextField5Gear = new javax.swing.JTextField();
        jTextField5Rate = new javax.swing.JTextField();
        jPanelPump14 = new javax.swing.JPanel();
        jLabelPump14Name = new javax.swing.JLabel();
        jButtonPump1Set15 = new javax.swing.JButton();
        jLabel1RPM15 = new javax.swing.JLabel();
        jLabel1Gear15 = new javax.swing.JLabel();
        jLabel1Rate15 = new javax.swing.JLabel();
        jTextField14RPM = new javax.swing.JTextField();
        jTextField14Gear = new javax.swing.JTextField();
        jTextField14Rate = new javax.swing.JTextField();
        jPanelPump15 = new javax.swing.JPanel();
        jLabelPump15Name = new javax.swing.JLabel();
        jButtonPump1Set16 = new javax.swing.JButton();
        jLabel1RPM16 = new javax.swing.JLabel();
        jLabel1Gear16 = new javax.swing.JLabel();
        jLabel1Rate16 = new javax.swing.JLabel();
        jTextField15RPM = new javax.swing.JTextField();
        jTextField15Gear = new javax.swing.JTextField();
        jTextField15Rate = new javax.swing.JTextField();
        jPanelPump16 = new javax.swing.JPanel();
        jLabelPump16Name = new javax.swing.JLabel();
        jButtonPump1Set17 = new javax.swing.JButton();
        jLabel1RPM17 = new javax.swing.JLabel();
        jLabel1Gear17 = new javax.swing.JLabel();
        jLabel1Rate17 = new javax.swing.JLabel();
        jTextField16RPM = new javax.swing.JTextField();
        jTextField16Gear = new javax.swing.JTextField();
        jTextField16Rate = new javax.swing.JTextField();
        SetButton = new javax.swing.JButton();
        jLabelRate = new javax.swing.JLabel();
        jButtonRateSet = new javax.swing.JButton();
        jTextFieldRate = new javax.swing.JTextField();
        jLabelhint = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1178, 850));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPumpQtyField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPumpQtyField.setText("10");
        jPumpQtyField.setToolTipText("");
        jPumpQtyField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPumpQtyFieldMouseClicked(evt);
            }
        });
        jPumpQtyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPumpQtyFieldActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setText("Pump unit qty");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1178, 700));

        jPanelPump2.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump2.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump2Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump2Name.setText("Насос1");
        jLabelPump2Name.setToolTipText("");

        jButtonPump2Set.setText("SET");
        jButtonPump2Set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump2SetActionPerformed(evt);
            }
        });

        jLabel2RPM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2RPM.setText("Engine RPM");

        jLabel2Gear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2Gear.setText("Trans gear");
        jLabel2Gear.setToolTipText("");

        jLabel2Rate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2Rate.setText("Rate l/min");

        jTextField2RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2RPM.setText("0");

        jTextField2Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2Gear.setText("N");

        jTextField2Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2Rate.setText("0");

        javax.swing.GroupLayout jPanelPump2Layout = new javax.swing.GroupLayout(jPanelPump2);
        jPanelPump2.setLayout(jPanelPump2Layout);
        jPanelPump2Layout.setHorizontalGroup(
            jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump2Layout.createSequentialGroup()
                        .addGroup(jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2Rate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump2Layout.createSequentialGroup()
                                .addComponent(jLabel2Gear, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField2Rate)))
                    .addGroup(jPanelPump2Layout.createSequentialGroup()
                        .addComponent(jLabel2RPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump2Set, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump2Layout.setVerticalGroup(
            jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump2Layout.createSequentialGroup()
                .addComponent(jLabelPump2Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2RPM)
                    .addComponent(jTextField2RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2Gear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2Rate)
                    .addComponent(jTextField2Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump2Set)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump1.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelPump1Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump1Name.setText("Насос1");
        jLabelPump1Name.setToolTipText("");

        jButtonPump1Set.setText("SET");
        jButtonPump1Set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1SetActionPerformed(evt);
            }
        });

        jLabel1RPM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM.setText("Engine RPM");

        jLabel1Gear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear.setText("Trans gear");
        jLabel1Gear.setToolTipText("");

        jLabel1Rate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate.setText("Rate l/min");

        jTextField1RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1RPM.setText("0");

        jTextField1Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1Gear.setText("N");

        jTextField1Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1Rate.setText("0");

        javax.swing.GroupLayout jPanelPump1Layout = new javax.swing.GroupLayout(jPanelPump1);
        jPanelPump1.setLayout(jPanelPump1Layout);
        jPanelPump1Layout.setHorizontalGroup(
            jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump1Layout.createSequentialGroup()
                .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPump1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelPump1Layout.createSequentialGroup()
                                .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1Rate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelPump1Layout.createSequentialGroup()
                                        .addComponent(jLabel1Gear, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(jTextField1Rate)))
                            .addGroup(jPanelPump1Layout.createSequentialGroup()
                                .addComponent(jLabel1RPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelPump1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButtonPump1Set, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelPump1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelPump1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPump1Layout.setVerticalGroup(
            jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump1Layout.createSequentialGroup()
                .addComponent(jLabelPump1Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM)
                    .addComponent(jTextField1RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate)
                    .addComponent(jTextField1Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump3.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump3.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump3Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump3Name.setText("Насос1");
        jLabelPump3Name.setToolTipText("");

        jButtonPump1Set4.setText("SET");
        jButtonPump1Set4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set4ActionPerformed(evt);
            }
        });

        jLabel1RPM4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM4.setText("Engine RPM");

        jLabel1Gear4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear4.setText("Trans gear");
        jLabel1Gear4.setToolTipText("");

        jLabel1Rate4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate4.setText("Rate l/min");

        jTextField3RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField3RPM.setText("0");

        jTextField3Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField3Gear.setText("N");

        jTextField3Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField3Rate.setText("0");

        javax.swing.GroupLayout jPanelPump3Layout = new javax.swing.GroupLayout(jPanelPump3);
        jPanelPump3.setLayout(jPanelPump3Layout);
        jPanelPump3Layout.setHorizontalGroup(
            jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump3Layout.createSequentialGroup()
                        .addGroup(jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump3Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField3Rate)))
                    .addGroup(jPanelPump3Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump3Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump3Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump3Layout.setVerticalGroup(
            jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump3Layout.createSequentialGroup()
                .addComponent(jLabelPump3Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM4)
                    .addComponent(jTextField3RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate4)
                    .addComponent(jTextField3Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump4.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump4.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump4Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump4Name.setText("Насос1");
        jLabelPump4Name.setToolTipText("");

        jButtonPump1Set5.setText("SET");
        jButtonPump1Set5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set5ActionPerformed(evt);
            }
        });

        jLabel1RPM5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM5.setText("Engine RPM");

        jLabel1Gear5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear5.setText("Trans gear");
        jLabel1Gear5.setToolTipText("");

        jLabel1Rate5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate5.setText("Rate l/min");

        jTextField4RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField4RPM.setText("0");

        jTextField4Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField4Gear.setText("N");

        jTextField4Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField4Rate.setText("0");

        javax.swing.GroupLayout jPanelPump4Layout = new javax.swing.GroupLayout(jPanelPump4);
        jPanelPump4.setLayout(jPanelPump4Layout);
        jPanelPump4Layout.setHorizontalGroup(
            jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump4Layout.createSequentialGroup()
                        .addGroup(jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump4Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField4Rate)))
                    .addGroup(jPanelPump4Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump4Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump4Layout.setVerticalGroup(
            jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump4Layout.createSequentialGroup()
                .addComponent(jLabelPump4Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM5)
                    .addComponent(jTextField4RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate5)
                    .addComponent(jTextField4Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump8.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump8.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump8Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump8Name.setText("Насос1");
        jLabelPump8Name.setToolTipText("");

        jButtonPump1Set6.setText("SET");
        jButtonPump1Set6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set6ActionPerformed(evt);
            }
        });

        jLabel1RPM6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM6.setText("Engine RPM");

        jLabel1Gear6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear6.setText("Trans gear");
        jLabel1Gear6.setToolTipText("");

        jLabel1Rate6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate6.setText("Rate l/min");

        jTextField8RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField8RPM.setText("0");

        jTextField8Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField8Gear.setText("N");

        jTextField8Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField8Rate.setText("0");

        javax.swing.GroupLayout jPanelPump8Layout = new javax.swing.GroupLayout(jPanelPump8);
        jPanelPump8.setLayout(jPanelPump8Layout);
        jPanelPump8Layout.setHorizontalGroup(
            jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump8Layout.createSequentialGroup()
                        .addGroup(jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump8Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField8Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField8Rate)))
                    .addGroup(jPanelPump8Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField8RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump8Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump8Layout.setVerticalGroup(
            jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump8Layout.createSequentialGroup()
                .addComponent(jLabelPump8Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM6)
                    .addComponent(jTextField8RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate6)
                    .addComponent(jTextField8Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump7.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump7.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump7Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump7Name.setText("Насос1");
        jLabelPump7Name.setToolTipText("");

        jButtonPump1Set7.setText("SET");
        jButtonPump1Set7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set7ActionPerformed(evt);
            }
        });

        jLabel1RPM7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM7.setText("Engine RPM");

        jLabel1Gear7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear7.setText("Trans gear");
        jLabel1Gear7.setToolTipText("");

        jLabel1Rate7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate7.setText("Rate l/min");

        jTextField7RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField7RPM.setText("0");

        jTextField7Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField7Gear.setText("N");

        jTextField7Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField7Rate.setText("0");

        javax.swing.GroupLayout jPanelPump7Layout = new javax.swing.GroupLayout(jPanelPump7);
        jPanelPump7.setLayout(jPanelPump7Layout);
        jPanelPump7Layout.setHorizontalGroup(
            jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump7Layout.createSequentialGroup()
                        .addGroup(jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump7Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField7Rate)))
                    .addGroup(jPanelPump7Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField7RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump7Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump7Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump7Layout.setVerticalGroup(
            jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump7Layout.createSequentialGroup()
                .addComponent(jLabelPump7Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM7)
                    .addComponent(jTextField7RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate7)
                    .addComponent(jTextField7Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump9.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump9.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump9Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump9Name.setText("Насос1");
        jLabelPump9Name.setToolTipText("");

        jButtonPump1Set8.setText("SET");
        jButtonPump1Set8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set8ActionPerformed(evt);
            }
        });

        jLabel1RPM8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM8.setText("Engine RPM");

        jLabel1Gear8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear8.setText("Trans gear");
        jLabel1Gear8.setToolTipText("");

        jLabel1Rate8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate8.setText("Rate l/min");

        jTextField9RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField9RPM.setText("0");

        jTextField9Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField9Gear.setText("N");

        jTextField9Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField9Rate.setText("0");

        javax.swing.GroupLayout jPanelPump9Layout = new javax.swing.GroupLayout(jPanelPump9);
        jPanelPump9.setLayout(jPanelPump9Layout);
        jPanelPump9Layout.setHorizontalGroup(
            jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump9Layout.createSequentialGroup()
                        .addGroup(jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump9Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField9Rate)))
                    .addGroup(jPanelPump9Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump9Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump9Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump9Layout.setVerticalGroup(
            jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump9Layout.createSequentialGroup()
                .addComponent(jLabelPump9Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM8)
                    .addComponent(jTextField9RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate8)
                    .addComponent(jTextField9Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump10.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump10.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump10Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump10Name.setText("Насос1");
        jLabelPump10Name.setToolTipText("");

        jButtonPump1Set9.setText("SET");
        jButtonPump1Set9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set9ActionPerformed(evt);
            }
        });

        jLabel1RPM9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM9.setText("Engine RPM");

        jLabel1Gear9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear9.setText("Trans gear");
        jLabel1Gear9.setToolTipText("");

        jLabel1Rate9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate9.setText("Rate l/min");

        jTextField10RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField10RPM.setText("0");

        jTextField10Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField10Gear.setText("N");

        jTextField10Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField10Rate.setText("0");

        javax.swing.GroupLayout jPanelPump10Layout = new javax.swing.GroupLayout(jPanelPump10);
        jPanelPump10.setLayout(jPanelPump10Layout);
        jPanelPump10Layout.setHorizontalGroup(
            jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump10Layout.createSequentialGroup()
                        .addGroup(jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump10Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField10Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField10Rate)))
                    .addGroup(jPanelPump10Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField10RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump10Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump10Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump10Layout.setVerticalGroup(
            jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump10Layout.createSequentialGroup()
                .addComponent(jLabelPump10Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM9)
                    .addComponent(jTextField10RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate9)
                    .addComponent(jTextField10Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump12.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump12.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump12Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump12Name.setText("Насос1");
        jLabelPump12Name.setToolTipText("");

        jButtonPump1Set10.setText("SET");
        jButtonPump1Set10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set10ActionPerformed(evt);
            }
        });

        jLabel1RPM10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM10.setText("Engine RPM");

        jLabel1Gear10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear10.setText("Trans gear");
        jLabel1Gear10.setToolTipText("");

        jLabel1Rate10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate10.setText("Rate l/min");

        jTextField12RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField12RPM.setText("0");

        jTextField12Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField12Gear.setText("N");

        jTextField12Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField12Rate.setText("0");

        javax.swing.GroupLayout jPanelPump12Layout = new javax.swing.GroupLayout(jPanelPump12);
        jPanelPump12.setLayout(jPanelPump12Layout);
        jPanelPump12Layout.setHorizontalGroup(
            jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump12Layout.createSequentialGroup()
                        .addGroup(jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump12Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField12Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField12Rate)))
                    .addGroup(jPanelPump12Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField12RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump12Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump12Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump12Layout.setVerticalGroup(
            jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump12Layout.createSequentialGroup()
                .addComponent(jLabelPump12Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM10)
                    .addComponent(jTextField12RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate10)
                    .addComponent(jTextField12Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump11.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump11.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump11Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump11Name.setText("Насос1");
        jLabelPump11Name.setToolTipText("");

        jButtonPump1Set11.setText("SET");
        jButtonPump1Set11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set11ActionPerformed(evt);
            }
        });

        jLabel1RPM11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM11.setText("Engine RPM");

        jLabel1Gear11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear11.setText("Trans gear");
        jLabel1Gear11.setToolTipText("");

        jLabel1Rate11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate11.setText("Rate l/min");

        jTextField11RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField11RPM.setText("0");

        jTextField11Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField11Gear.setText("N");

        jTextField11Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField11Rate.setText("0");

        javax.swing.GroupLayout jPanelPump11Layout = new javax.swing.GroupLayout(jPanelPump11);
        jPanelPump11.setLayout(jPanelPump11Layout);
        jPanelPump11Layout.setHorizontalGroup(
            jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump11Layout.createSequentialGroup()
                        .addGroup(jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump11Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField11Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField11Rate)))
                    .addGroup(jPanelPump11Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField11RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump11Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButtonPump1Set11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump11Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump11Layout.setVerticalGroup(
            jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump11Layout.createSequentialGroup()
                .addComponent(jLabelPump11Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM11)
                    .addComponent(jTextField11RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate11)
                    .addComponent(jTextField11Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonPump1Set11)
                .addContainerGap())
        );

        jPanelPump6.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump6.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump6Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump6Name.setText("Насос1");
        jLabelPump6Name.setToolTipText("");

        jButtonPump1Set12.setText("SET");
        jButtonPump1Set12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set12ActionPerformed(evt);
            }
        });

        jLabel1RPM12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM12.setText("Engine RPM");

        jLabel1Gear12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear12.setText("Trans gear");
        jLabel1Gear12.setToolTipText("");

        jLabel1Rate12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate12.setText("Rate l/min");

        jTextField6RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField6RPM.setText("0");

        jTextField6Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField6Gear.setText("N");

        jTextField6Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField6Rate.setText("0");

        javax.swing.GroupLayout jPanelPump6Layout = new javax.swing.GroupLayout(jPanelPump6);
        jPanelPump6.setLayout(jPanelPump6Layout);
        jPanelPump6Layout.setHorizontalGroup(
            jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump6Layout.createSequentialGroup()
                        .addGroup(jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump6Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField6Rate)))
                    .addGroup(jPanelPump6Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump6Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump6Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump6Layout.setVerticalGroup(
            jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump6Layout.createSequentialGroup()
                .addComponent(jLabelPump6Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM12)
                    .addComponent(jTextField6RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate12)
                    .addComponent(jTextField6Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump13.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump13.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump13Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump13Name.setText("Насос1");
        jLabelPump13Name.setToolTipText("");

        jButtonPump1Set14.setText("SET");
        jButtonPump1Set14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set14ActionPerformed(evt);
            }
        });

        jLabel1RPM14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM14.setText("Engine RPM");

        jLabel1Gear14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear14.setText("Trans gear");
        jLabel1Gear14.setToolTipText("");

        jLabel1Rate14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate14.setText("Rate l/min");

        jTextField13RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField13RPM.setText("0");

        jTextField13Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField13Gear.setText("N");

        jTextField13Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField13Rate.setText("0");

        javax.swing.GroupLayout jPanelPump13Layout = new javax.swing.GroupLayout(jPanelPump13);
        jPanelPump13.setLayout(jPanelPump13Layout);
        jPanelPump13Layout.setHorizontalGroup(
            jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump13Layout.createSequentialGroup()
                        .addGroup(jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump13Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField13Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField13Rate)))
                    .addGroup(jPanelPump13Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField13RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump13Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump13Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump13Layout.setVerticalGroup(
            jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump13Layout.createSequentialGroup()
                .addComponent(jLabelPump13Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM14)
                    .addComponent(jTextField13RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate14)
                    .addComponent(jTextField13Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump5.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump5.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump5Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump5Name.setText("Насос1");
        jLabelPump5Name.setToolTipText("");

        jButtonPump1Set13.setText("SET");
        jButtonPump1Set13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set13ActionPerformed(evt);
            }
        });

        jLabel1RPM13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM13.setText("Engine RPM");

        jLabel1Gear13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear13.setText("Trans gear");
        jLabel1Gear13.setToolTipText("");

        jLabel1Rate13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate13.setText("Rate l/min");

        jTextField5RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField5RPM.setText("0");

        jTextField5Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField5Gear.setText("N");

        jTextField5Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField5Rate.setText("0");

        javax.swing.GroupLayout jPanelPump5Layout = new javax.swing.GroupLayout(jPanelPump5);
        jPanelPump5.setLayout(jPanelPump5Layout);
        jPanelPump5Layout.setHorizontalGroup(
            jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump5Layout.createSequentialGroup()
                        .addGroup(jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump5Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear13, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField5Rate)))
                    .addGroup(jPanelPump5Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set13, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump5Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump5Layout.setVerticalGroup(
            jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump5Layout.createSequentialGroup()
                .addComponent(jLabelPump5Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM13)
                    .addComponent(jTextField5RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate13)
                    .addComponent(jTextField5Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump14.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump14.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump14Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump14Name.setText("Насос1");
        jLabelPump14Name.setToolTipText("");

        jButtonPump1Set15.setText("SET");
        jButtonPump1Set15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set15ActionPerformed(evt);
            }
        });

        jLabel1RPM15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM15.setText("Engine RPM");

        jLabel1Gear15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear15.setText("Trans gear");
        jLabel1Gear15.setToolTipText("");

        jLabel1Rate15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate15.setText("Rate l/min");

        jTextField14RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField14RPM.setText("0");

        jTextField14Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField14Gear.setText("N");

        jTextField14Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField14Rate.setText("0");

        javax.swing.GroupLayout jPanelPump14Layout = new javax.swing.GroupLayout(jPanelPump14);
        jPanelPump14.setLayout(jPanelPump14Layout);
        jPanelPump14Layout.setHorizontalGroup(
            jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump14Layout.createSequentialGroup()
                        .addGroup(jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump14Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear15, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField14Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField14Rate)))
                    .addGroup(jPanelPump14Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField14RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump14Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump14Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump14Layout.setVerticalGroup(
            jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump14Layout.createSequentialGroup()
                .addComponent(jLabelPump14Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM15)
                    .addComponent(jTextField14RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate15)
                    .addComponent(jTextField14Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump15.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump15.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump15Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump15Name.setText("Насос1");
        jLabelPump15Name.setToolTipText("");

        jButtonPump1Set16.setText("SET");
        jButtonPump1Set16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set16ActionPerformed(evt);
            }
        });

        jLabel1RPM16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM16.setText("Engine RPM");

        jLabel1Gear16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear16.setText("Trans gear");
        jLabel1Gear16.setToolTipText("");

        jLabel1Rate16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate16.setText("Rate l/min");

        jTextField15RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField15RPM.setText("0");

        jTextField15Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField15Gear.setText("N");

        jTextField15Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField15Rate.setText("0");

        javax.swing.GroupLayout jPanelPump15Layout = new javax.swing.GroupLayout(jPanelPump15);
        jPanelPump15.setLayout(jPanelPump15Layout);
        jPanelPump15Layout.setHorizontalGroup(
            jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump15Layout.createSequentialGroup()
                        .addGroup(jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump15Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField15Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField15Rate)))
                    .addGroup(jPanelPump15Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField15RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump15Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump15Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump15Layout.setVerticalGroup(
            jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump15Layout.createSequentialGroup()
                .addComponent(jLabelPump15Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM16)
                    .addComponent(jTextField15RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate16)
                    .addComponent(jTextField15Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPump16.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPump16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPump16.setPreferredSize(new java.awt.Dimension(188, 155));

        jLabelPump16Name.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelPump16Name.setText("Насос1");
        jLabelPump16Name.setToolTipText("");

        jButtonPump1Set17.setText("SET");
        jButtonPump1Set17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPump1Set17ActionPerformed(evt);
            }
        });

        jLabel1RPM17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1RPM17.setText("Engine RPM");

        jLabel1Gear17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Gear17.setText("Trans gear");
        jLabel1Gear17.setToolTipText("");

        jLabel1Rate17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Rate17.setText("Rate l/min");

        jTextField16RPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField16RPM.setText("0");

        jTextField16Gear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField16Gear.setText("N");

        jTextField16Rate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField16Rate.setText("0");

        javax.swing.GroupLayout jPanelPump16Layout = new javax.swing.GroupLayout(jPanelPump16);
        jPanelPump16.setLayout(jPanelPump16Layout);
        jPanelPump16Layout.setHorizontalGroup(
            jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPump16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPump16Layout.createSequentialGroup()
                        .addGroup(jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1Rate17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPump16Layout.createSequentialGroup()
                                .addComponent(jLabel1Gear17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField16Gear, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jTextField16Rate)))
                    .addGroup(jPanelPump16Layout.createSequentialGroup()
                        .addComponent(jLabel1RPM17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField16RPM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelPump16Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButtonPump1Set17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPump16Name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelPump16Layout.setVerticalGroup(
            jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPump16Layout.createSequentialGroup()
                .addComponent(jLabelPump16Name)
                .addGap(2, 2, 2)
                .addGroup(jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1RPM17)
                    .addComponent(jTextField16RPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16Gear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Gear17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPump16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Rate17)
                    .addComponent(jTextField16Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPump1Set17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPump7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelPump2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanelPump13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPump3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelPump9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelPump14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelPump4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelPump5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelPump6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelPump10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelPump11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelPump12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelPump15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelPump16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelPump5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPump12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelPump10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelPump11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPump15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelPump16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(236, 236, 236))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelPump3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPump7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPump13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPump14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        SetButton.setText("Set");
        SetButton.setToolTipText("");
        SetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetButtonActionPerformed(evt);
            }
        });

        jLabelRate.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabelRate.setText("Rate");

        jButtonRateSet.setText("Set");
        jButtonRateSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRateSetActionPerformed(evt);
            }
        });

        jTextFieldRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRate.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPumpQtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(216, 216, 216)
                .addComponent(jLabelhint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRateSet, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelRate, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonRateSet)
                        .addComponent(jTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelhint))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPumpQtyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SetButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetButtonActionPerformed
        qcounter1++;
        //jPanel1.removeAll();
        //jPanel1.setVisible(false);
        //jLabel1.setText(tempName);
        for(int i=0;i<Integer.valueOf(jPumpQtyField.getText());i++){
            
            pumpPanel.get(i).setVisible(true);
        }
        for(int i=Integer.valueOf(jPumpQtyField.getText());i<16;i++){
            pumpPanel.get(i).setVisible(false);
        }
        jPanel1.repaint();
       
//        ClassPathXmlApplicationContext context=
//            new ClassPathXmlApplicationContext("applicationContext.xml");
//            Pump pump1=context.getBean("SPM2250_4.5_inches",Pump.class );
//            
//    context.close();
        //jPanel1.setBackground(Color.red);
        //Font font1=new java.awt.Font("Arial Black", 0, 12);
        //ArrayList<JPanel> pump_list=new ArrayList<JPanel>();
        //ArrayList<JLabel> pumpNameList=new ArrayList<>();
       // ArrayList<JButton> pumpSetButton=new ArrayList<>();
        
                      
               
    }//GEN-LAST:event_SetButtonActionPerformed

    private void jButtonPump1SetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1SetActionPerformed
        pumpnumber=0;
        tempName=pumpUnit[0].getPumpUnitName();
        transtype=pumpUnit[0].transmission.transtype;
        engineType=pumpUnit[0].engineType;
        pumpEfficiency=pumpUnit[0].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1SetActionPerformed

    private void jPumpQtyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPumpQtyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPumpQtyFieldActionPerformed

    private void jPumpQtyFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPumpQtyFieldMouseClicked
           
            
           
        // TODO add your handling code here:
    }//GEN-LAST:event_jPumpQtyFieldMouseClicked

    private void jButtonPump2SetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump2SetActionPerformed
        pumpnumber=1;
        tempName=pumpUnit[1].getPumpUnitName();
        transtype=pumpUnit[1].transmission.transtype;
        engineType=pumpUnit[1].engineType;
        pumpEfficiency=pumpUnit[1].pump.getPumpEfficiency();
        Settings.main();
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump2SetActionPerformed

    private void jButtonPump1Set4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set4ActionPerformed
        pumpnumber=2;
        tempName=pumpUnit[2].getPumpUnitName();
        transtype=pumpUnit[2].transmission.transtype;
        engineType=pumpUnit[2].engineType;
        pumpEfficiency=pumpUnit[2].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1Set4ActionPerformed

    private void jButtonPump1Set5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set5ActionPerformed
        pumpnumber=3;
        tempName=pumpUnit[3].getPumpUnitName();
        transtype=pumpUnit[3].transmission.transtype;
        engineType=pumpUnit[3].engineType;
        pumpEfficiency=pumpUnit[3].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1Set5ActionPerformed

    private void jButtonPump1Set13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set13ActionPerformed
        pumpnumber=4;
        tempName=pumpUnit[4].getPumpUnitName();
        transtype=pumpUnit[4].transmission.transtype;
        engineType=pumpUnit[4].engineType;
        pumpEfficiency=pumpUnit[4].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1Set13ActionPerformed

    private void jButtonPump1Set12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set12ActionPerformed
        pumpnumber=5;
        tempName=pumpUnit[5].getPumpUnitName();
        transtype=pumpUnit[5].transmission.transtype;
        engineType=pumpUnit[5].engineType;
        pumpEfficiency=pumpUnit[5].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1Set12ActionPerformed

    private void jButtonPump1Set7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set7ActionPerformed
        pumpnumber=6;
        tempName=pumpUnit[6].getPumpUnitName();
        transtype=pumpUnit[6].transmission.transtype;
        engineType=pumpUnit[6].engineType;
        pumpEfficiency=pumpUnit[6].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set7ActionPerformed

    private void jButtonPump1Set6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set6ActionPerformed
        pumpnumber=7;
        tempName=pumpUnit[7].getPumpUnitName();
        transtype=pumpUnit[7].transmission.transtype;
        engineType=pumpUnit[7].engineType;
        pumpEfficiency=pumpUnit[7].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1Set6ActionPerformed

    private void jButtonPump1Set8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set8ActionPerformed
        pumpnumber=8;
        tempName=pumpUnit[8].getPumpUnitName();
        transtype=pumpUnit[8].transmission.transtype;
        engineType=pumpUnit[8].engineType;
        pumpEfficiency=pumpUnit[8].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set8ActionPerformed

    private void jButtonPump1Set9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set9ActionPerformed
        pumpnumber=9;
        tempName=pumpUnit[9].getPumpUnitName();
        transtype=pumpUnit[9].transmission.transtype;
        engineType=pumpUnit[9].engineType;
        pumpEfficiency=pumpUnit[9].pump.getPumpEfficiency();
        Settings.main();
    }//GEN-LAST:event_jButtonPump1Set9ActionPerformed

    private void jButtonPump1Set11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set11ActionPerformed
        pumpnumber=10;
        tempName=pumpUnit[10].getPumpUnitName();
        transtype=pumpUnit[10].transmission.transtype;
        engineType=pumpUnit[10].engineType;
        pumpEfficiency=pumpUnit[10].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set11ActionPerformed

    private void jButtonPump1Set10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set10ActionPerformed
        pumpnumber=11;
        tempName=pumpUnit[11].getPumpUnitName();
        transtype=pumpUnit[11].transmission.transtype;
        engineType=pumpUnit[11].engineType;
        pumpEfficiency=pumpUnit[11].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set10ActionPerformed

    private void jButtonPump1Set14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set14ActionPerformed
        pumpnumber=12;
        tempName=pumpUnit[12].getPumpUnitName();
        transtype=pumpUnit[12].transmission.transtype;
        engineType=pumpUnit[12].engineType;
        pumpEfficiency=pumpUnit[12].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set14ActionPerformed

    private void jButtonPump1Set15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set15ActionPerformed
        pumpnumber=13;
        tempName=pumpUnit[13].getPumpUnitName();
        transtype=pumpUnit[13].transmission.transtype;
        engineType=pumpUnit[13].engineType;
        pumpEfficiency=pumpUnit[13].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set15ActionPerformed

    private void jButtonPump1Set16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set16ActionPerformed
        pumpnumber=14;
        tempName=pumpUnit[14].getPumpUnitName();
        transtype=pumpUnit[14].transmission.transtype;
        engineType=pumpUnit[14].engineType;
        pumpEfficiency=pumpUnit[14].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set16ActionPerformed

    private void jButtonPump1Set17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPump1Set17ActionPerformed
        pumpnumber=15;
        tempName=pumpUnit[15].getPumpUnitName();
        transtype=pumpUnit[15].transmission.transtype;
        engineType=pumpUnit[15].engineType;
        pumpEfficiency=pumpUnit[15].pump.getPumpEfficiency();
        Settings.main();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPump1Set17ActionPerformed

    private void jButtonRateSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRateSetActionPerformed
        //создаем набор прошлого состояния
        for(int i=0;i<10;i++){
            System.out.println("Peredaha "+transGear[i].getText());
        }
        oldSet=getOldSet();
        if (wasWorking(oldSet)){pumpingOn=true;}
        else {pumpingOn=false;}
        double tempRate=0;
        double shifting=0;//смещение расхода для нахождения других наборов
        double tempMaxRate=0;
        rateCalculation();
        int pumpActive=0;
        int numberOfVariant=0; 
        List<SetVariant[]> allSetVariant=new ArrayList<>();
        //определяем формат вывода 0.00
        DecimalFormat df=new DecimalFormat("0.00");
        System.out.println("Min="+globalMinRate+" Max="+globalMaxRate);
        jLabelRate.setText("Rate "+df.format(globalMinRate)+"..."+df.format(globalMaxRate)+" л/мин");
        //проверить почему не работает
        //if(Integer.getInteger(jTextFieldRate.getText())<globalMinRate){
          //  jTextFieldRate.setText(String.valueOf(df.format(globalMinRate)));
        //}
//        if(Integer.getInteger(jTextFieldRate.getText())>globalMaxRate){ 
//            jTextFieldRate.setText(String.valueOf(globalMaxRate));
//        }
//        
       jLabelhint.setText("Min rate is "+String.valueOf(df.format(globalMinRate)));
       //1. Определить минимальное количество насосок.
       // Их число должно быть максимальным из доступного.
       pumpUnitList=new ArrayList<>();
       for(int i=0;i<Integer.valueOf(jPumpQtyField.getText());i++){
           pumpUnitList.add(pumpUnit[i]);
       }
       //сортируем по мере возрастания наименьшего расхода
       //System.out.println(pumpUnitList);
       Collections.sort(pumpUnitList);
       //System.out.println(".........."+pumpUnitList);
       for(PumpUnit pumpunit:pumpUnitList){pumpunit.state=false;}
       //Создаем колекцию активных установок
       List<PumpUnit> activePumpUnit=new ArrayList<>();
       for(PumpUnit pumpunit:pumpUnitList){
           System.out.println("poka "+tempRate+" menshe "+Double.parseDouble(jTextFieldRate.getText()));
           tempRate=tempRate+pumpunit.minRate();
           if(tempRate>Double.parseDouble(jTextFieldRate.getText())){
               break;
           }
           pumpunit.state=true;
           activePumpUnit.add(pumpunit);
           pumpActive++;
       }
       System.out.println("kolichestvo nasosov= "+pumpActive);
       //включение активных насосак
       for(int i=0;i<Integer.parseInt(jPumpQtyField.getText());i++){
           if(pumpUnit[i].state){
               System.out.println("Pump "+i+" activated");
               engineSpeed[i].setText("Calcul.");
           }
       }
       for(PumpUnit pumpunit:activePumpUnit){
           System.out.print(pumpunit.state+" ");
       }
       System.out.println();
       for(PumpUnit pumpunit:pumpUnitList){
           System.out.print(pumpunit.state+" ");
       }
       //****************************************************
       // определяем сколько на каждую активную pumpLevel
       shareRating();
       
       // находим что получается в данном случае
       allSetVariant.add(setNewSet(activePumpUnit, shifting));
      
       //проверяем набор на подходящие варианты скорости и передачи
           
           for(PumpUnit pumpunit:activePumpUnit){
               int tempGear=0;
               int tempSpeed=0;
               //System.out.println(pumpunit.pump.oneTurnRate()+" odin oborot");
               SpeedSet[] tempSet=pumpunit.ratecalcul(pumpunit.pumpLevel*Double.valueOf(jTextFieldRate.getText()));
               for(int k=0;k<tempSet.length;k++){
                    if (tempSet[k].state==2){
                        tempGear=tempSet[k].gear;
                        tempSpeed=(int)tempSet[k].speed;
                        break;
                    }
                    if (tempSet[k].state==1){
                        tempGear=tempSet[k].gear;
                        tempSpeed=(int)tempSet[k].speed;
                    }
               }
               //engineSpeed[pumpunit.windowNumber].setText(String.valueOf(tempSpeed));
               //transGear[pumpunit.windowNumber].setText(String.valueOf(tempGear));
           }
        //делаем наборы на соседних передачах
        boolean firstGearset=false;
        boolean otherGearset=false;
        for(int i=0;i<pumpActive/2-1;i++){
            if ((allSetVariant.get(i)[activePumpUnit.get(i).windowNumber].gear==1 || firstGearset)&&otherGearset==false){
                firstGearset=true;
                activePumpUnit.get(i).state=false;
                //activePumpUnit.remove(i);
                shareRating();
                allSetVariant.add(setNewSet(activePumpUnit, shifting));
                System.out.println("tessssssssssssss");
            }
            else{
                activePumpUnit.get(i).state=false;
                shareRating();
                otherGearset=true;
               double tempshifting=activePumpUnit.get(i).pump.oneTurnRate()
                    *activePumpUnit.get(i).getMiddleSpeed()/activePumpUnit.get(i).pump.getPumpGearRatio()
                    /activePumpUnit.get(i).transmission.getGearsRatio()[oldSet[activePumpUnit.get(i).windowNumber].gear];//(allSetVariant.get(0)[activePumpUnit.get(i).windowNumber].gear)-1]
               shifting=tempshifting+shifting;
               allSetVariant.add(setNewSet(activePumpUnit, shifting));
              
                System.out.println("Shifting na peredache"+shifting +"  "+activePumpUnit.get(i).transmission.getGearsRatio()[oldSet[activePumpUnit.get(i).windowNumber].gear]);
                allSetVariant.get(i+1)[activePumpUnit.get(i).windowNumber].gear=allSetVariant.get(0)[activePumpUnit.get(i).windowNumber].gear-1;
                allSetVariant.get(i+1)[activePumpUnit.get(i).windowNumber].RPM=activePumpUnit.get(0).getMiddleSpeed();
                allSetVariant.get(i+1)[activePumpUnit.get(i).windowNumber].rate=tempshifting;
               //скопировать предыдущие состояния
               if(i>1){
                   for(int j=0;j<i-1;j++){
                   allSetVariant.get(i)[activePumpUnit.get(j).windowNumber].gear=
                           allSetVariant.get(i-1)[activePumpUnit.get(j).windowNumber].gear;
                   allSetVariant.get(i)[activePumpUnit.get(j).windowNumber].RPM=
                           allSetVariant.get(i-1)[activePumpUnit.get(j).windowNumber].RPM;
                   allSetVariant.get(i)[activePumpUnit.get(j).windowNumber].rate=
                           allSetVariant.get(i-1)[activePumpUnit.get(j).windowNumber].rate;
               }
               }
               
            }
            //оставшийся расход делим на оставшиеся насоски
            shareRating();
            pumpsSetForRequirRate(activePumpUnit);
        }
        firstGearset=false;
        otherGearset=false;
        for(SetVariant[] sets:allSetVariant){
            infoSet(sets);
        }
        //выводим на экран
        windowSet(bestSetchoosen(allSetVariant));
       shifting=0;
       
       jPanel1.repaint();
    }//GEN-LAST:event_jButtonRateSetActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main mainWindow=new Main();
                mainWindow.setVisible(true);
                        //new Main().setVisible(true);
                
                
                
                }
            
        });
    }
    void pumpUnitconfigure(){
        if (setAllstate){
            jLabel1.setText("It works");
            for(int i=0;i<pumpUnit.length;i++){
                pumpUnit[i].copypump(pumpUnit[pumpnumber]);
                
            }
            
        }
        for (int i=0;i<pumpName.length;i++){
            pumpName[i].setText("Pump "+(i+1)+" "+pumpUnit[i].getPumpUnitName());
            engineSpeed[i].setText(String.valueOf(pumpUnit[i].getMinAllowSpeed()));
            rateField[i].setText(String.valueOf(pumpUnit[i].getMiddleSpeed()));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SetButton;
    private javax.swing.JButton jButtonPump1Set;
    private javax.swing.JButton jButtonPump1Set10;
    private javax.swing.JButton jButtonPump1Set11;
    private javax.swing.JButton jButtonPump1Set12;
    private javax.swing.JButton jButtonPump1Set13;
    private javax.swing.JButton jButtonPump1Set14;
    private javax.swing.JButton jButtonPump1Set15;
    private javax.swing.JButton jButtonPump1Set16;
    private javax.swing.JButton jButtonPump1Set17;
    private javax.swing.JButton jButtonPump1Set4;
    private javax.swing.JButton jButtonPump1Set5;
    private javax.swing.JButton jButtonPump1Set6;
    private javax.swing.JButton jButtonPump1Set7;
    private javax.swing.JButton jButtonPump1Set8;
    private javax.swing.JButton jButtonPump1Set9;
    private javax.swing.JButton jButtonPump2Set;
    private javax.swing.JButton jButtonRateSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1Gear;
    private javax.swing.JLabel jLabel1Gear10;
    private javax.swing.JLabel jLabel1Gear11;
    private javax.swing.JLabel jLabel1Gear12;
    private javax.swing.JLabel jLabel1Gear13;
    private javax.swing.JLabel jLabel1Gear14;
    private javax.swing.JLabel jLabel1Gear15;
    private javax.swing.JLabel jLabel1Gear16;
    private javax.swing.JLabel jLabel1Gear17;
    private javax.swing.JLabel jLabel1Gear4;
    private javax.swing.JLabel jLabel1Gear5;
    private javax.swing.JLabel jLabel1Gear6;
    private javax.swing.JLabel jLabel1Gear7;
    private javax.swing.JLabel jLabel1Gear8;
    private javax.swing.JLabel jLabel1Gear9;
    private javax.swing.JLabel jLabel1RPM;
    private javax.swing.JLabel jLabel1RPM10;
    private javax.swing.JLabel jLabel1RPM11;
    private javax.swing.JLabel jLabel1RPM12;
    private javax.swing.JLabel jLabel1RPM13;
    private javax.swing.JLabel jLabel1RPM14;
    private javax.swing.JLabel jLabel1RPM15;
    private javax.swing.JLabel jLabel1RPM16;
    private javax.swing.JLabel jLabel1RPM17;
    private javax.swing.JLabel jLabel1RPM4;
    private javax.swing.JLabel jLabel1RPM5;
    private javax.swing.JLabel jLabel1RPM6;
    private javax.swing.JLabel jLabel1RPM7;
    private javax.swing.JLabel jLabel1RPM8;
    private javax.swing.JLabel jLabel1RPM9;
    private javax.swing.JLabel jLabel1Rate;
    private javax.swing.JLabel jLabel1Rate10;
    private javax.swing.JLabel jLabel1Rate11;
    private javax.swing.JLabel jLabel1Rate12;
    private javax.swing.JLabel jLabel1Rate13;
    private javax.swing.JLabel jLabel1Rate14;
    private javax.swing.JLabel jLabel1Rate15;
    private javax.swing.JLabel jLabel1Rate16;
    private javax.swing.JLabel jLabel1Rate17;
    private javax.swing.JLabel jLabel1Rate4;
    private javax.swing.JLabel jLabel1Rate5;
    private javax.swing.JLabel jLabel1Rate6;
    private javax.swing.JLabel jLabel1Rate7;
    private javax.swing.JLabel jLabel1Rate8;
    private javax.swing.JLabel jLabel1Rate9;
    private javax.swing.JLabel jLabel2Gear;
    private javax.swing.JLabel jLabel2RPM;
    private javax.swing.JLabel jLabel2Rate;
    private javax.swing.JLabel jLabelPump10Name;
    private javax.swing.JLabel jLabelPump11Name;
    private javax.swing.JLabel jLabelPump12Name;
    private javax.swing.JLabel jLabelPump13Name;
    private javax.swing.JLabel jLabelPump14Name;
    private javax.swing.JLabel jLabelPump15Name;
    private javax.swing.JLabel jLabelPump16Name;
    private javax.swing.JLabel jLabelPump1Name;
    private javax.swing.JLabel jLabelPump2Name;
    private javax.swing.JLabel jLabelPump3Name;
    private javax.swing.JLabel jLabelPump4Name;
    private javax.swing.JLabel jLabelPump5Name;
    private javax.swing.JLabel jLabelPump6Name;
    private javax.swing.JLabel jLabelPump7Name;
    private javax.swing.JLabel jLabelPump8Name;
    private javax.swing.JLabel jLabelPump9Name;
    private javax.swing.JLabel jLabelRate;
    private javax.swing.JLabel jLabelhint;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPump1;
    private javax.swing.JPanel jPanelPump10;
    private javax.swing.JPanel jPanelPump11;
    private javax.swing.JPanel jPanelPump12;
    private javax.swing.JPanel jPanelPump13;
    private javax.swing.JPanel jPanelPump14;
    private javax.swing.JPanel jPanelPump15;
    private javax.swing.JPanel jPanelPump16;
    private javax.swing.JPanel jPanelPump2;
    private javax.swing.JPanel jPanelPump3;
    private javax.swing.JPanel jPanelPump4;
    private javax.swing.JPanel jPanelPump5;
    private javax.swing.JPanel jPanelPump6;
    private javax.swing.JPanel jPanelPump7;
    private javax.swing.JPanel jPanelPump8;
    private javax.swing.JPanel jPanelPump9;
    private javax.swing.JTextField jPumpQtyField;
    private javax.swing.JTextField jTextField10Gear;
    private javax.swing.JTextField jTextField10RPM;
    private javax.swing.JTextField jTextField10Rate;
    private javax.swing.JTextField jTextField11Gear;
    private javax.swing.JTextField jTextField11RPM;
    private javax.swing.JTextField jTextField11Rate;
    private javax.swing.JTextField jTextField12Gear;
    private javax.swing.JTextField jTextField12RPM;
    private javax.swing.JTextField jTextField12Rate;
    private javax.swing.JTextField jTextField13Gear;
    private javax.swing.JTextField jTextField13RPM;
    private javax.swing.JTextField jTextField13Rate;
    private javax.swing.JTextField jTextField14Gear;
    private javax.swing.JTextField jTextField14RPM;
    private javax.swing.JTextField jTextField14Rate;
    private javax.swing.JTextField jTextField15Gear;
    private javax.swing.JTextField jTextField15RPM;
    private javax.swing.JTextField jTextField15Rate;
    private javax.swing.JTextField jTextField16Gear;
    private javax.swing.JTextField jTextField16RPM;
    private javax.swing.JTextField jTextField16Rate;
    private javax.swing.JTextField jTextField1Gear;
    private javax.swing.JTextField jTextField1RPM;
    private javax.swing.JTextField jTextField1Rate;
    private javax.swing.JTextField jTextField2Gear;
    private javax.swing.JTextField jTextField2RPM;
    private javax.swing.JTextField jTextField2Rate;
    private javax.swing.JTextField jTextField3Gear;
    private javax.swing.JTextField jTextField3RPM;
    private javax.swing.JTextField jTextField3Rate;
    private javax.swing.JTextField jTextField4Gear;
    private javax.swing.JTextField jTextField4RPM;
    private javax.swing.JTextField jTextField4Rate;
    private javax.swing.JTextField jTextField5Gear;
    private javax.swing.JTextField jTextField5RPM;
    private javax.swing.JTextField jTextField5Rate;
    private javax.swing.JTextField jTextField6Gear;
    private javax.swing.JTextField jTextField6RPM;
    private javax.swing.JTextField jTextField6Rate;
    private javax.swing.JTextField jTextField7Gear;
    private javax.swing.JTextField jTextField7RPM;
    private javax.swing.JTextField jTextField7Rate;
    private javax.swing.JTextField jTextField8Gear;
    private javax.swing.JTextField jTextField8RPM;
    private javax.swing.JTextField jTextField8Rate;
    private javax.swing.JTextField jTextField9Gear;
    private javax.swing.JTextField jTextField9RPM;
    private javax.swing.JTextField jTextField9Rate;
    private javax.swing.JTextField jTextFieldRate;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch (pumpnumber){
            case(0):
                pumpUnit[0].setPumpUnitName(tempName);
                pumpUnit[0].setPump(tempName);
                pumpUnit[0].setEngine(engineType);
                pumpUnit[0].setTransmission(transtype);
                pumpUnit[0].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(1):
                pumpUnit[1].setPumpUnitName(tempName);
                pumpUnit[1].setPump(tempName);
                pumpUnit[1].setEngine(engineType);
                pumpUnit[1].setTransmission(transtype);
                pumpUnit[1].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(2):
                pumpUnit[2].setPumpUnitName(tempName);
                pumpUnit[2].setPump(tempName);
                pumpUnit[2].setEngine(engineType);
                pumpUnit[2].setTransmission(transtype);
                pumpUnit[2].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(3):
                pumpUnit[3].setPumpUnitName(tempName);
                pumpUnit[3].setPump(tempName);
                pumpUnit[3].setEngine(engineType);
                pumpUnit[3].setTransmission(transtype);
                pumpUnit[3].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(4):
                pumpUnit[4].setPumpUnitName(tempName);
                pumpUnit[4].setPump(tempName);
                pumpUnit[4].setEngine(engineType);
                pumpUnit[4].setTransmission(transtype);
                pumpUnit[4].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(5):
                pumpUnit[5].setPumpUnitName(tempName);
                pumpUnit[5].setPump(tempName);
                pumpUnit[5].setEngine(engineType);
                pumpUnit[5].setTransmission(transtype);
                pumpUnit[5].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(6):
                pumpUnit[6].setPumpUnitName(tempName);
                pumpUnit[6].setPump(tempName);
                pumpUnit[6].setEngine(engineType);
                pumpUnit[6].setTransmission(transtype);
                pumpUnit[6].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(7):
                pumpUnit[7].setPumpUnitName(tempName);
                pumpUnit[7].setPump(tempName);
                pumpUnit[7].setEngine(engineType);
                pumpUnit[7].setTransmission(transtype);
                pumpUnit[7].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(8):
                pumpUnit[8].setPumpUnitName(tempName);
                pumpUnit[8].setPump(tempName);
                pumpUnit[8].setEngine(engineType);
                pumpUnit[8].setTransmission(transtype);
                pumpUnit[8].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(9):
                pumpUnit[9].setPumpUnitName(tempName);
                pumpUnit[9].setPump(tempName);
                pumpUnit[9].setEngine(engineType);
                pumpUnit[9].setTransmission(transtype);
                pumpUnit[9].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(10):
                pumpUnit[10].setPumpUnitName(tempName);
                pumpUnit[10].setPump(tempName);
                pumpUnit[10].setEngine(engineType);
                pumpUnit[10].setTransmission(transtype);
                pumpUnit[10].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(11):
                pumpUnit[11].setPumpUnitName(tempName);
                pumpUnit[11].setPump(tempName);
                pumpUnit[11].setEngine(engineType);
                pumpUnit[11].setTransmission(transtype);
                pumpUnit[11].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(12):
                pumpUnit[12].setPumpUnitName(tempName);
                pumpUnit[12].setPump(tempName);
                pumpUnit[12].setEngine(engineType);
                pumpUnit[12].setTransmission(transtype);
                pumpUnit[12].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(13):
                pumpUnit[13].setPumpUnitName(tempName);
                pumpUnit[13].setPump(tempName);
                pumpUnit[13].setEngine(engineType);
                pumpUnit[13].setTransmission(transtype);
                pumpUnit[13].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(14):
                pumpUnit[14].setPumpUnitName(tempName);
                pumpUnit[14].setPump(tempName);
                pumpUnit[14].setEngine(engineType);
                pumpUnit[14].setTransmission(transtype);
                pumpUnit[14].pump.setPumpEfficiency(pumpEfficiency);
                break;
            case(15):
                pumpUnit[15].setPumpUnitName(tempName);
                pumpUnit[15].setPump(tempName);
                pumpUnit[15].setEngine(engineType);
                pumpUnit[15].setTransmission(transtype);
                pumpUnit[15].pump.setPumpEfficiency(pumpEfficiency);
                break;
        }        
        
        pumpUnitconfigure();
        //jLabel1.setText("activated");
        jPanel1.repaint();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jLabel1.setText("deactivated");
        jPanel1.repaint();
    }
    //Расчет доли активных насосок на введенном общем расходе
    public void shareRating(){
        double tempMaxRate=0;
        DecimalFormat df=new DecimalFormat("0.00");
         //расчет максимального расхода активных насосок 
       for(int i=0;i<Integer.parseInt(jPumpQtyField.getText());i++){
           if(pumpUnit[i].state){
               tempMaxRate=tempMaxRate+pumpUnit[i].maxRate();
           }
       }
       //расчет доли насосок в операции
       for(int i=0;i<Integer.parseInt(jPumpQtyField.getText());i++){
           if(pumpUnit[i].state){
               pumpUnit[i].pumpLevel=pumpUnit[i].maxRate()/tempMaxRate;
           }
           else{pumpUnit[i].pumpLevel=0;}
//           rateField[i].setText(String.valueOf(df.format(pumpUnit[i].pumpLevel
//                   *Double.valueOf(jTextFieldRate.getText()))));
       }
    }
    //Расчет сетов
    public void pumpsSetForRequirRate(List<PumpUnit> activePumpUnit)
    {
        
    for(int i=0;i<activePumpUnit.size();i++){
               int tempGear=0;
               int tempSpeed=0;
               //System.out.println();
               activePumpUnit.get(i).speedSet=activePumpUnit.get(i).ratecalcul(activePumpUnit.get(i).pumpLevel*Double.valueOf(jTextFieldRate.getText()));
              // System.out.println("Unit "+activePumpUnit.get(i).windowNumber);
               for (int j=0;j<activePumpUnit.get(i).speedSet.length;j++){
                   // System.out.print(" "+activePumpUnit.get(i).speedSet[j].gear+" "+
                    //activePumpUnit.get(i).speedSet[j].speed+" "+activePumpUnit.get(i).speedSet[j].state);
               }
           }
}
    //Заполняем массив показаниями 
    public SetVariant[] getOldSet(){
        
        SetVariant[] setVariant=new SetVariant[Integer.valueOf(jPumpQtyField.getText())];
        System.out.println("Old state is");
        for(int i=0;i<setVariant.length;i++){
            setVariant[i]=new SetVariant();
            setVariant[i].windowNumber=pumpUnit[i].windowNumber;
            setVariant[i].RPM=Double.valueOf(engineSpeed[i].getText());
            if (transGear[i].getText().equals("N")){
                setVariant[i].gear=0;
            }
            else {setVariant[i].gear=Integer.valueOf(transGear[i].getText());}
            System.out.println(setVariant[i].gear);
            setVariant[i].rate=Integer.valueOf(rateField[i].getText());
        }
        return setVariant;
    }
    public SetVariant[] setNewSet(List<PumpUnit> activePumpUnit, double shifting){
        SetVariant[] newSetvariant=new SetVariant[16];
        for(int i=0;i<16;i++){
            newSetvariant[i]=new SetVariant();
        }
        for(PumpUnit pumpunit:activePumpUnit){
               int tempGear=0;
               int tempSpeed=0;
               int tempState=0;
               System.out.println("Shifting=====     "+shifting);
                    System.out.println("Level=     "+pumpunit.pumpLevel);
                    double tempRate=pumpunit.pumpLevel*(Double.valueOf(jTextFieldRate.getText())-shifting);
                    System.out.println("Rashod=   ==== "+tempRate);
                    SpeedSet[] tempSet=pumpunit.ratecalcul(pumpunit.pumpLevel*Double.valueOf(jTextFieldRate.getText()));
                    for(int k=0;k<tempSet.length;k++){
                        if (tempSet[k].state==2){
                            tempGear=tempSet[k].gear;
                            tempSpeed=(int)tempSet[k].speed;
                            tempState=tempSet[k].state;
                            break;
                        }
                        if (tempSet[k].state==1 && k==0){
                            tempGear=tempSet[k].gear;
                            tempSpeed=(int)tempSet[k].speed;
                            tempState=tempSet[k].state;
                        }
                        else{
                                if ((tempSet[k].state==1 && tempSet[k-1].state!=1))
                                    {
                                    tempGear=tempSet[k].gear;
                                    tempSpeed=(int)tempSet[k].speed;
                                    tempState=tempSet[k].state;
                                    }
                            }
                
                    }
               
               newSetvariant[pumpunit.windowNumber].gear=tempGear;
               newSetvariant[pumpunit.windowNumber].RPM=tempSpeed;
               newSetvariant[pumpunit.windowNumber].rate=tempRate;
               newSetvariant[pumpunit.windowNumber].state=tempState;
               newSetvariant[pumpunit.windowNumber].windowNumber=pumpunit.windowNumber;
               System.out.println("pumpunit.windowNumber="+pumpunit.windowNumber+"   "+newSetvariant[pumpunit.windowNumber].gear+"  "+newSetvariant[pumpunit.windowNumber].RPM);
           }
       return newSetvariant;
    }
    public void infoSet(SetVariant[] setVariant){
        System.out.println();
        for (int i=0;i<setVariant.length;i++){
            System.out.print("Unit "+setVariant[i].windowNumber+", Rate="+setVariant[i].rate+", RPM="+setVariant[i].RPM+", Gear="+setVariant[i].gear+", state="+setVariant[i].state+" --");
        }
        
    }
    public boolean wasWorking(SetVariant[] setVariant){
        int summGear=0;
        for(SetVariant setVar:setVariant){
            summGear=summGear+setVar.gear;
            
        }
        System.out.println("All gears="+summGear);
        if(summGear==0){return false;}
        else {return true;}
    }
    public SetVariant[] bestSetchoosen(List<SetVariant[]> allVar){
        //определяем работали ли установки
        int charact=0;
        int index=0;
        SetValuate setValuate[]=new SetValuate[allVar.size()];
        for(int i=0;i<allVar.size();i++){
            setValuate[i]=new SetValuate();
        }
        int k=0;
        
        System.out.println();
        System.out.println("System was working?"+pumpingOn);
        if (pumpingOn==false){
            for(SetVariant[] setVar:allVar){
                
                for(int i=0;i<setVar.length;i++){
                    
                    if(setVar[i].gear!=0){
                        setValuate[k].stateProperty=setVar[i].state;
                        setValuate[k].unitQty++;
                    }
                }
                
                setValuate[k].charachter=setValuate[k].unitQty*setValuate[k].stateProperty;
                System.out.println("set "+setValuate[k].charachter+" bolshe "+charact);
                if(setValuate[k].charachter>charact){
                    charact=setValuate[k].charachter;
                    index=k;
                }
                
                k++;
            }
            k=0;
        }
        else {
            int realNumber;
            int realGear;
            int h=0;
            int tepmperekl=100;
            for(SetVariant[] setVar:allVar){
                for(int l=0;l<setVar.length;l++)
                    {
                        for (int i=0;i<Integer.parseInt(jPumpQtyField.getText());i++)
                        {
                            if (setVar[l].windowNumber!=20){
                                if(setVar[l].windowNumber==i)
                                    //номер в сете будет соответствовать realNumber номеру насоски в окне
                                {   realNumber=i;
                                    if (transGear[realNumber].getText().equals("N")){
                                        realGear=0;
                                    }
                                    else{realGear=Integer.parseInt(transGear[realNumber].getText());}

                                    setValuate[h].gearDifer=setValuate[h].gearDifer+Math.abs(setVar[l].gear-realGear);
                                }
                            }
                        }
                        System.out.println("Dlia seta "+h+" kol perekluchenii="+setValuate[h].gearDifer);
                    }
                
                if (setValuate[h].gearDifer<tepmperekl){
                    index=h;
                    tepmperekl=setValuate[h].gearDifer;
                }
                h++;
            }
            h=0;
        }
        System.out.println("Set "+index+" rabochii");
        return allVar.get(index); 
    }
    public void windowSet(SetVariant[] workSet){
        int index=0;
        System.out.println("dlinna "+workSet.length);
        DecimalFormat df=new DecimalFormat("0.0");
        for(int i=0;i<Integer.valueOf(jPumpQtyField.getText());i++){
            
            System.out.println("i="+i+" unit "+workSet[i].windowNumber);
            if (workSet[i].windowNumber==20){
                engineSpeed[i].setText("600");
                rateField[i].setText("0");
                transGear[i].setText("N");
            }
            else{
                for(int l=0;l<workSet.length;l++)
                    {
                        if(workSet[i].windowNumber==l)
                        {index=l;
                            System.out.println("Index "+i+" ystanovki="+l);
                        }
                    }
                engineSpeed[i].setText(String.valueOf(workSet[index].RPM));
                rateField[i].setText(String.valueOf((int)(workSet[index].rate)));
                if(workSet[index].gear==0){transGear[i].setText("N");}
                else
                    {transGear[i].setText(String.valueOf(workSet[index].gear));}
                }
        }
    }
}
