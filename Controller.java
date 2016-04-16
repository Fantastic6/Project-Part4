package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.URL;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.logging.Handler;

public class Controller implements Initializable {

    @FXML
    CheckBox R0_B1, R0_B2, R0_B4, R0_B8, R0_B16, R0_B32, R0_B64, R0_B128, R0_B256, R0_B512, R0_B1024, R0_B2048, R0_B4096, R0_B8192, R0_B16384, R0_B32768;
    @FXML
    CheckBox R1_B1, R1_B2, R1_B4, R1_B8, R1_B16, R1_B32, R1_B64, R1_B128, R1_B256, R1_B512, R1_B1024, R1_B2048, R1_B4096, R1_B8192, R1_B16384, R1_B32768;
    @FXML
    CheckBox R2_B1, R2_B2, R2_B4, R2_B8, R2_B16, R2_B32, R2_B64, R2_B128, R2_B256, R2_B512, R2_B1024, R2_B2048, R2_B4096, R2_B8192, R2_B16384, R2_B32768;
    @FXML
    CheckBox R3_B1, R3_B2, R3_B4, R3_B8, R3_B16, R3_B32, R3_B64, R3_B128, R3_B256, R3_B512, R3_B1024, R3_B2048, R3_B4096, R3_B8192, R3_B16384, R3_B32768;
    @FXML
    CheckBox Inst_B1, Inst_B2, Inst_B4, Inst_B8, Inst_B16, Inst_B32, Inst_B64, Inst_B128, Inst_B256, Inst_B512, Inst_B1024, Inst_B2048, Inst_B4096, Inst_B8192, Inst_B16384, Inst_B32768;
    @FXML
    Button setR0Btn, setR1Btn, setR2Btn, setR3Btn, setBtnInst, btnDisplay, setPCBtn, btnSSS, setI1btn, setI2btn, setI3btn;

    @FXML
    Button setCC0btn, setCC1btn, setCC2btn, setCC3btn, btnRUN, btnLoad, btnSIT;

    @FXML
    ToggleButton btnPower;

    @FXML
    TextField TxtValInst, TxtAddress, txtI1Val, txtI2Val, txtI3Val, LabelValR0, LabelValR1, LabelValR2, LabelValR3, terminalTF;

    @FXML
    TextField LabelValPC, txtCC0Val, txtCC1Val, txtCC2Val, txtCC3Val;

    @FXML
    HBox R0HBox, R1HBox, R2HBox, R3HBox;

    @FXML
    Label resultLabel;

    private int totalSumR0 = 0, totalSumR1 = 0, totalSumR2 = 0, totalSumR3 = 0, totalSumInst = 0;

    private String memoryAddress;
    private short instNumber;
    private short result;
    static int resultIN = 0;
    private boolean isRunClicked = false;
    private String lbResult = "Result: ";
    
    int firstInst = 0;
    int lastInst = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CheckBox R0ObjArray[] = {R0_B1, R0_B2, R0_B4, R0_B8, R0_B16, R0_B32, R0_B64, R0_B128, R0_B256, R0_B512, R0_B1024, R0_B2048, R0_B4096, R0_B8192, R0_B16384, R0_B32768};
        CheckBox R1ObjArray[] = {R1_B1, R1_B2, R1_B4, R1_B8, R1_B16, R1_B32, R1_B64, R1_B128, R1_B256, R1_B512, R1_B1024, R1_B2048, R1_B4096, R1_B8192, R1_B16384, R1_B32768};
        CheckBox R2ObjArray[] = {R2_B1, R2_B2, R2_B4, R2_B8, R2_B16, R2_B32, R2_B64, R2_B128, R2_B256, R2_B512, R2_B1024, R2_B2048, R2_B4096, R2_B8192, R2_B16384, R2_B32768};
        CheckBox R3ObjArray[] = {R3_B1, R3_B2, R3_B4, R3_B8, R3_B16, R3_B32, R3_B64, R3_B128, R3_B256, R3_B512, R3_B1024, R3_B2048, R3_B4096, R3_B8192, R3_B16384, R3_B32768};

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                terminalTF.requestFocus();
            }
        });
        btnPower.setSelected(true);
        btnPower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!btnPower.isSelected()){
                    btnPower.setText("ON");
                    setCC0btn.setDisable(true);
                    setCC1btn.setDisable(true);
                    setCC2btn.setDisable(true);
                    setCC3btn.setDisable(true);
                    setI1btn.setDisable(true);
                    setI2btn.setDisable(true);
                    setI3btn.setDisable(true);
                    btnDisplay.setDisable(true);
                    btnSSS.setDisable(true);
                    //R1 register Set EventHandler
                    setR0Btn.setDisable(true);
                    //R1 register Set EventHandler
                    setR1Btn.setDisable(true);
                    //R2 register Set EventHandler
                    setR2Btn.setDisable(true);
                    //R3 register Set EventHandler
                    setR3Btn.setDisable(true);
                    //Instruction register Set EventHandler
                    setBtnInst.setDisable(true);
                    //PC register Set EventHandler
                    setPCBtn.setDisable(true);
                    btnSIT.setDisable(true);
                    btnRUN.setDisable(true);
                    btnLoad.setDisable(true);

                    Main.cpu.setCCValue(0,false);
                    Main.cpu.setCCValue(1,false);
                    Main.cpu.setCCValue(2,false);
                    Main.cpu.setCCValue(3,false);
                    Main.cpu.setGPRValue(0,(short)0);
                    Main.cpu.setGPRValue(1,(short)0);
                    Main.cpu.setGPRValue(2,(short)0);
                    Main.cpu.setGPRValue(3,(short)0);
                    Main.cpu.setIRValue(0,(short) 0);
                    Main.cpu.setIRValue(1,(short) 0);
                    Main.cpu.setIRValue(2,(short) 0);
                    Main.cpu.setIRValue(3,(short) 0);
                    Main.cpu.setISRValue((short)0);
                    Main.cpu.setPC(0);
                    for(int i=0; i<2048; i++){
                        Main.myCache.write(i,(short) 0);
                    }
                    LabelValR0.setText(String.valueOf(Main.cpu.getGPRValue(0)));
                    LabelValR1.setText(String.valueOf(Main.cpu.getGPRValue(1)));
                    LabelValR2.setText(String.valueOf(Main.cpu.getGPRValue(2)));
                    LabelValR3.setText(String.valueOf(Main.cpu.getGPRValue(3)));
                    setCheckBox(Main.cpu.getGPRValue(0), R0ObjArray);
                    setCheckBox(Main.cpu.getGPRValue(1), R1ObjArray);
                    setCheckBox(Main.cpu.getGPRValue(2), R2ObjArray);
                    setCheckBox(Main.cpu.getGPRValue(3), R3ObjArray);
                    totalSumR0 = Main.cpu.getGPRValue(0);
                    totalSumR1 = Main.cpu.getGPRValue(1);
                    totalSumR2 = Main.cpu.getGPRValue(2);
                    totalSumR3 = Main.cpu.getGPRValue(3);
                    txtI1Val.setText(String.valueOf(Main.cpu.getIRValue(1)));
                    txtI2Val.setText(String.valueOf(Main.cpu.getIRValue(2)));
                    txtI3Val.setText(String.valueOf(Main.cpu.getIRValue(3)));
                    LabelValPC.setText(String.valueOf(Main.cpu.getPC()));
                    txtCC0Val.setText(String.valueOf(Main.cpu.getCCValue(0)));
                    txtCC1Val.setText(String.valueOf(Main.cpu.getCCValue(1)));
                    txtCC2Val.setText(String.valueOf(Main.cpu.getCCValue(2)));
                    txtCC3Val.setText(String.valueOf(Main.cpu.getCCValue(3)));
                    terminalTF.setText("");
                }else{
                    btnPower.setText("OFF");
                    setCC0btn.setDisable(false);
                    setCC1btn.setDisable(false);
                    setCC2btn.setDisable(false);
                    setCC3btn.setDisable(false);
                    setI1btn.setDisable(false);
                    setI2btn.setDisable(false);
                    setI3btn.setDisable(false);
                    btnDisplay.setDisable(false);
                    btnSSS.setDisable(false);
                    //R1 register Set EventHandler
                    setR0Btn.setDisable(false);
                    //R1 register Set EventHandler
                    setR1Btn.setDisable(false);
                    //R2 register Set EventHandler
                    setR2Btn.setDisable(false);
                    //R3 register Set EventHandler
                    setR3Btn.setDisable(false);
                    //Instruction register Set EventHandler
                    setBtnInst.setDisable(false);
                    //PC register Set EventHandler
                    setPCBtn.setDisable(false);
                    btnSIT.setDisable(false);
                    btnRUN.setDisable(false);
                    btnLoad.setDisable(false);

                }
            }
        });

        EventHandler eventHandlerR0Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setR0Btn.toString())) {
//                    registerResults.add(0, totalSum);
                    totalSumR0 = Integer.parseInt(LabelValR0.getText());
                    setCheckBox(totalSumR0, R0ObjArray);
//                    LabelValR0.setText(null);
                    Main.cpu.setGPRValue(0, (short) totalSumR0);
                    //totalSumR0 = 0;
//                    registerResArr[0] = (int) totalSumR0;
//                    totalSumR0 = 0;
                    /*for (int i = 0; i < registerResArr.length; i++)
                        System.out.println(registerResArr[i]);*/
                } else if (event.getSource().toString().equals(R0_B1.toString())) {
                    if (R0_B1.isSelected()) {
                        totalSumR0 += 1;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 1;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B2.toString())) {
                    if (R0_B2.isSelected()) {
                        totalSumR0 += 2;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        if (totalSumR0 != 0) {
                            totalSumR0 -= 2;
                            LabelValR0.setText(String.valueOf(totalSumR0));
                        }
                    }
                } else if (event.getSource().toString().equals(R0_B4.toString())) {
                    if (R0_B4.isSelected()) {
                        totalSumR0 += 4;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        if (totalSumR0 != 0) {
                            totalSumR0 -= 4;
                            LabelValR0.setText(String.valueOf(totalSumR0));
                        }
                    }
                } else if (event.getSource().toString().equals(R0_B8.toString())) {
                    if (R0_B8.isSelected()) {
                        totalSumR0 += 8;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 8;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B16.toString())) {
                    if (R0_B16.isSelected()) {
                        totalSumR0 += 16;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 16;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B32.toString())) {
                    if (R0_B32.isSelected()) {
                        totalSumR0 += 32;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 32;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B64.toString())) {
                    if (R0_B64.isSelected()) {
                        totalSumR0 += 64;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 64;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B128.toString())) {
                    if (R0_B128.isSelected()) {
                        totalSumR0 += 128;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 128;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B256.toString())) {
                    if (R0_B256.isSelected()) {
                        totalSumR0 += 256;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 256;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B512.toString())) {
                    if (R0_B512.isSelected()) {
                        totalSumR0 += 512;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 512;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B1024.toString())) {
                    if (R0_B1024.isSelected()) {
                        totalSumR0 += 1024;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 1024;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B2048.toString())) {
                    if (R0_B2048.isSelected()) {
                        totalSumR0 += 2048;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 2048;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B4096.toString())) {
                    if (R0_B4096.isSelected()) {
                        totalSumR0 += 4096;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 4096;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B8192.toString())) {
                    if (R0_B8192.isSelected()) {
                        totalSumR0 += 8192;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 8192;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B16384.toString())) {
                    if (R0_B16384.isSelected()) {
                        totalSumR0 += 16384;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 -= 16384;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else if (event.getSource().toString().equals(R0_B32768.toString())) {
                    if (R0_B32768.isSelected()) {
                        totalSumR0 -= 32768;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    } else {
                        totalSumR0 += 32768;
                        LabelValR0.setText(String.valueOf(totalSumR0));
                    }
                } else {

                }
            }
        };
        EventHandler eventHandlerR1Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setR1Btn.toString())) {
//                    registerResults.add(1, totalSum);
                    totalSumR1 = Integer.parseInt(LabelValR1.getText());
//                    LabelValR1.setText(null);
                    setCheckBox(totalSumR1, R1ObjArray);
                    Main.cpu.setGPRValue(1, (short) totalSumR1);
//                    registerResArr[1] = (int) totalSumR1;
                    /*for (int i = 0; i < registerResArr.length; i++)
                        System.out.println(registerResArr[i]);*/
                } else if (event.getSource().toString().equals(R1_B1.toString())) {
                    if (R1_B1.isSelected()) {
                        totalSumR1 += 1;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 1;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B2.toString())) {
                    if (R1_B2.isSelected()) {
                        totalSumR1 += 2;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        if (totalSumR1 != 0) {
                            totalSumR1 -= 2;
                            LabelValR1.setText(String.valueOf(totalSumR1));
                        }
                    }
                } else if (event.getSource().toString().equals(R1_B4.toString())) {
                    if (R1_B4.isSelected()) {
                        totalSumR1 += 4;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        if (totalSumR1 != 0) {
                            totalSumR1 -= 4;
                            LabelValR1.setText(String.valueOf(totalSumR1));
                        }
                    }
                } else if (event.getSource().toString().equals(R1_B8.toString())) {
                    if (R1_B8.isSelected()) {
                        totalSumR1 += 8;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 8;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B16.toString())) {
                    if (R1_B16.isSelected()) {
                        totalSumR1 += 16;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 16;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B32.toString())) {
                    if (R1_B32.isSelected()) {
                        totalSumR1 += 32;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 32;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B64.toString())) {
                    if (R1_B64.isSelected()) {
                        totalSumR1 += 64;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 64;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B128.toString())) {
                    if (R1_B128.isSelected()) {
                        totalSumR1 += 128;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 128;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B256.toString())) {
                    if (R1_B256.isSelected()) {
                        totalSumR1 += 256;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 256;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B512.toString())) {
                    if (R1_B512.isSelected()) {
                        totalSumR1 += 512;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 512;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B1024.toString())) {
                    if (R1_B1024.isSelected()) {
                        totalSumR1 += 1024;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 1024;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B2048.toString())) {
                    if (R1_B2048.isSelected()) {
                        totalSumR1 += 2048;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 2048;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B4096.toString())) {
                    if (R1_B4096.isSelected()) {
                        totalSumR1 += 4096;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 4096;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B8192.toString())) {
                    if (R1_B8192.isSelected()) {
                        totalSumR1 += 8192;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 8192;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B16384.toString())) {
                    if (R1_B16384.isSelected()) {
                        totalSumR1 += 16384;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 -= 16384;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else if (event.getSource().toString().equals(R1_B32768.toString())) {
                    if (R1_B32768.isSelected()) {
                        totalSumR1 -= 32768;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    } else {
                        totalSumR1 += 32768;
                        LabelValR1.setText(String.valueOf(totalSumR1));
                    }
                } else {

                }
            }
        };
        EventHandler eventHandlerR2Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setR2Btn.toString())) {
//                    registerResults.add(2, totalSum);
                    totalSumR2 = Integer.parseInt(LabelValR2.getText());
//                    LabelValR2.setText(null);
                    setCheckBox(totalSumR2, R2ObjArray);
                    Main.cpu.setGPRValue(2, (short) totalSumR2);
                    /*for (int i = 0; i < registerResArr.length; i++)
                        System.out.println(registerResArr[i]);*/
                } else if (event.getSource().toString().equals(R2_B1.toString())) {
                    if (R2_B1.isSelected()) {
                        totalSumR2 += 1;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 1;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B2.toString())) {
                    if (R2_B2.isSelected()) {
                        totalSumR2 += 2;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        if (totalSumR2 != 0) {
                            totalSumR2 -= 2;
                            LabelValR2.setText(String.valueOf(totalSumR2));
                        }
                    }
                } else if (event.getSource().toString().equals(R2_B4.toString())) {
                    if (R2_B4.isSelected()) {
                        totalSumR2 += 4;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        if (totalSumR2 != 0) {
                            totalSumR2 -= 4;
                            LabelValR2.setText(String.valueOf(totalSumR2));
                        }
                    }
                } else if (event.getSource().toString().equals(R2_B8.toString())) {
                    if (R2_B8.isSelected()) {
                        totalSumR2 += 8;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 8;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B16.toString())) {
                    if (R2_B16.isSelected()) {
                        totalSumR2 += 16;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 16;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B32.toString())) {
                    if (R2_B32.isSelected()) {
                        totalSumR2 += 32;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 32;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B64.toString())) {
                    if (R2_B64.isSelected()) {
                        totalSumR2 += 64;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 64;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B128.toString())) {
                    if (R2_B128.isSelected()) {
                        totalSumR2 += 128;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 128;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B256.toString())) {
                    if (R2_B256.isSelected()) {
                        totalSumR2 += 256;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 256;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B512.toString())) {
                    if (R2_B512.isSelected()) {
                        totalSumR2 += 512;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 512;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B1024.toString())) {
                    if (R2_B1024.isSelected()) {
                        totalSumR2 += 1024;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 1024;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B2048.toString())) {
                    if (R2_B2048.isSelected()) {
                        totalSumR2 += 2048;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 2048;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B4096.toString())) {
                    if (R2_B4096.isSelected()) {
                        totalSumR2 += 4096;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 4096;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B8192.toString())) {
                    if (R2_B8192.isSelected()) {
                        totalSumR2 += 8192;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 8192;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B16384.toString())) {
                    if (R2_B16384.isSelected()) {
                        totalSumR2 += 16384;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 -= 16384;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else if (event.getSource().toString().equals(R2_B32768.toString())) {
                    if (R2_B32768.isSelected()) {
                        totalSumR2 -= 32768;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    } else {
                        totalSumR2 += 32768;
                        LabelValR2.setText(String.valueOf(totalSumR2));
                    }
                } else {

                }
            }
        };
        EventHandler eventHandlerR3Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setR3Btn.toString())) {
//                    registerResults.add(3, totalSum);
                    totalSumR3 = Integer.parseInt(LabelValR3.getText());
//                    LabelValR3.setText(null);
                    setCheckBox(totalSumR3, R3ObjArray);
                    Main.cpu.setGPRValue(3, (short) totalSumR3);
                    /*for (int i = 0; i < registerResArr.length; i++)
                        System.out.println(registerResArr[i]);*/
                } else if (event.getSource().toString().equals(R3_B1.toString())) {
                    if (R3_B1.isSelected()) {
                        totalSumR3 += 1;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 1;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B2.toString())) {
                    if (R3_B2.isSelected()) {
                        totalSumR3 += 2;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        if (totalSumR3 != 0) {
                            totalSumR3 -= 2;
                            LabelValR3.setText(String.valueOf(totalSumR3));
                        }
                    }
                } else if (event.getSource().toString().equals(R3_B4.toString())) {
                    if (R3_B4.isSelected()) {
                        totalSumR3 += 4;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        if (totalSumR3 != 0) {
                            totalSumR3 -= 4;
                            LabelValR3.setText(String.valueOf(totalSumR3));
                        }
                    }
                } else if (event.getSource().toString().equals(R3_B8.toString())) {
                    if (R3_B8.isSelected()) {
                        totalSumR3 += 8;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 8;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B16.toString())) {
                    if (R3_B16.isSelected()) {
                        totalSumR3 += 16;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 16;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B32.toString())) {
                    if (R3_B32.isSelected()) {
                        totalSumR3 += 32;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 32;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B64.toString())) {
                    if (R3_B64.isSelected()) {
                        totalSumR3 += 64;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 64;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B128.toString())) {
                    if (R3_B128.isSelected()) {
                        totalSumR3 += 128;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 128;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B256.toString())) {
                    if (R3_B256.isSelected()) {
                        totalSumR3 += 256;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 256;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B512.toString())) {
                    if (R3_B512.isSelected()) {
                        totalSumR3 += 512;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 512;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B1024.toString())) {
                    if (R3_B1024.isSelected()) {
                        totalSumR3 += 1024;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 1024;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B2048.toString())) {
                    if (R3_B2048.isSelected()) {
                        totalSumR3 += 2048;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 2048;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B4096.toString())) {
                    if (R3_B4096.isSelected()) {
                        totalSumR3 += 4096;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 4096;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B8192.toString())) {
                    if (R3_B8192.isSelected()) {
                        totalSumR3 += 8192;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 8192;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B16384.toString())) {
                    if (R3_B16384.isSelected()) {
                        totalSumR3 += 16384;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 -= 16384;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(R3_B32768.toString())) {
                    if (R3_B32768.isSelected()) {
                        totalSumR3 -= 32768;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumR3 += 32768;
                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else {

                }
            }
        };
        EventHandler eventHandlerInstLine = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setBtnInst.toString())) {
                    btnDisplay.setDisable(false);
                    totalSumInst = Integer.parseInt(TxtValInst.getText().trim());
                    memoryAddress = TxtAddress.getText().trim();
                    Main.myCache.write(Integer.parseInt(memoryAddress), (short) totalSumInst);
                    TxtValInst.setText(null);
//                    System.out.println(Main.memory.get(Integer.parseInt(memoryAddress)));
                } else if (event.getSource().toString().equals(Inst_B1.toString())) {
                    if (Inst_B1.isSelected()) {
                        totalSumInst += 1;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 1;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B2.toString())) {
                    if (Inst_B2.isSelected()) {
                        totalSumInst += 2;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        if (totalSumInst != 0) {
                            totalSumInst -= 2;
                            TxtValInst.setText(String.valueOf(totalSumInst));
                            //LabelValR3.setText(String.valueOf(totalSumR3));
                        }
                    }
                } else if (event.getSource().toString().equals(Inst_B4.toString())) {
                    if (Inst_B4.isSelected()) {
                        totalSumInst += 4;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        if (totalSumInst != 0) {
                            totalSumInst -= 4;
                            TxtValInst.setText(String.valueOf(totalSumInst));
                            //LabelValR3.setText(String.valueOf(totalSumR3));
                        }
                    }
                } else if (event.getSource().toString().equals(Inst_B8.toString())) {
                    if (Inst_B8.isSelected()) {
                        totalSumInst += 8;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 8;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B16.toString())) {
                    if (Inst_B16.isSelected()) {
                        totalSumInst += 16;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 16;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B32.toString())) {
                    if (Inst_B32.isSelected()) {
                        totalSumInst += 32;
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                        TxtValInst.setText(String.valueOf(totalSumInst));
                    } else {
                        totalSumInst -= 32;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B64.toString())) {
                    if (Inst_B64.isSelected()) {
                        totalSumInst += 64;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 64;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B128.toString())) {
                    if (Inst_B128.isSelected()) {
                        totalSumInst += 128;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 128;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B256.toString())) {
                    if (Inst_B256.isSelected()) {
                        totalSumInst += 256;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 256;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B512.toString())) {
                    if (Inst_B512.isSelected()) {
                        totalSumInst += 512;
                        TxtValInst.setText(String.valueOf(totalSumInst));
                        //LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 512;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B1024.toString())) {
                    if (Inst_B1024.isSelected()) {
                        totalSumInst += 1024;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 1024;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B2048.toString())) {
                    if (Inst_B2048.isSelected()) {
                        totalSumInst += 2048;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 2048;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B4096.toString())) {
                    if (Inst_B4096.isSelected()) {
                        totalSumInst += 4096;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 4096;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B8192.toString())) {
                    if (Inst_B8192.isSelected()) {
                        totalSumInst += 8192;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 8192;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B16384.toString())) {
                    if (Inst_B16384.isSelected()) {
                        totalSumInst += 16384;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst -= 16384;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                } else if (event.getSource().toString().equals(Inst_B32768.toString())) {
                    if (Inst_B32768.isSelected()) {
                        totalSumInst -= 32768;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    } else {
                        totalSumInst += 32768;
                        TxtValInst.setText(String.valueOf(totalSumInst));
//                        LabelValR3.setText(String.valueOf(totalSumR3));
                    }
                }
            }
        };
        EventHandler eventHandlerI1Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setI1btn.toString())) {
                    short value = Short.parseShort(txtI1Val.getText().trim());
//                    System.out.println("IR1 Value: " + value);
                    Main.cpu.setIRValue(1, value);
                }
            }
        };
        EventHandler eventHandlerI2Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setI2btn.toString())) {
                    short value = Short.parseShort(txtI2Val.getText().trim());
                    Main.cpu.setIRValue(2, value);
                }
            }
        };
        EventHandler eventHandlerI3Line = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setI3btn.toString())) {
                    short value = Short.parseShort(txtI3Val.getText().trim());
                    Main.cpu.setIRValue(3, value);
                }
            }
        };

        EventHandler eventHandlerPC = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setPCBtn.toString())) {
//                    btnDisplay.setDisable(false);
                    Main.cpu.setPC(Integer.parseInt(LabelValPC.getText().trim()));
                    //btnSSS.setDisable(false);
                }
            }
        };
        EventHandler eventHandlerDisplay = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(btnDisplay.toString())) {
                    TxtValInst.setText(String.valueOf(Main.myCache.read(Integer.parseInt(TxtAddress.getText().trim()))));
                    System.out.println(Main.myCache.read(Integer.parseInt(TxtAddress.getText().trim())));
                }
            }
        };
        EventHandler eventHandlerSSS = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(btnSSS.toString())) {
//                    Main.cpu.setIRValue(1, (short) 1);
                    if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 61) {
                        terminalTF.setText("");
                        terminalTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                if (event.getCode().equals(KeyCode.ENTER)) {
                                    String temp = terminalTF.getText();
                                    if(temp.charAt(0) >= 97 & temp.charAt(0) <= 122) {
                                		resultIN = (int)temp.charAt(0);
                                	}
                                	else {
                                		resultIN = Integer.parseInt(temp);
                                	}
                                    Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                                    Main.cpu.setPC(Main.cpu.getPC() + 1);
                                    System.out.println("RESULT: " + result);
                                    if(isRunClicked) {
                                        isRunClicked=false;
                                        btnRUN.fire();
                                    }
                                }
                            }
                        });
                        LabelValR0.setText(String.valueOf(Main.cpu.getGPRValue(0)));
                        LabelValR1.setText(String.valueOf(Main.cpu.getGPRValue(1)));
                        LabelValR2.setText(String.valueOf(Main.cpu.getGPRValue(2)));
                        LabelValR3.setText(String.valueOf(Main.cpu.getGPRValue(3)));
                        setCheckBox(Main.cpu.getGPRValue(0), R0ObjArray);
                        setCheckBox(Main.cpu.getGPRValue(1), R1ObjArray);
                        setCheckBox(Main.cpu.getGPRValue(2), R2ObjArray);
                        setCheckBox(Main.cpu.getGPRValue(3), R3ObjArray);
                        totalSumR0 = Main.cpu.getGPRValue(0);
                        totalSumR1 = Main.cpu.getGPRValue(1);
                        totalSumR2 = Main.cpu.getGPRValue(2);
                        totalSumR3 = Main.cpu.getGPRValue(3);
                        txtI1Val.setText(String.valueOf(Main.cpu.getIRValue(1)));
                        txtI2Val.setText(String.valueOf(Main.cpu.getIRValue(2)));
                        txtI3Val.setText(String.valueOf(Main.cpu.getIRValue(3)));
                        LabelValPC.setText(String.valueOf(Main.cpu.getPC()));
                        txtCC0Val.setText(String.valueOf(Main.cpu.getCCValue(0)));
                        txtCC1Val.setText(String.valueOf(Main.cpu.getCCValue(1)));
                        txtCC2Val.setText(String.valueOf(Main.cpu.getCCValue(2)));
                        txtCC3Val.setText(String.valueOf(Main.cpu.getCCValue(3)));
                        //btnRUN.fire();
                    } else {
                        if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 16 || (Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 17) {
                            result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                        } else {
                            if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 62) {
                                result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                                System.out.println("!!! innner OUT !!! " + result);
                                //lbResult.concat(String.valueOf(result));
                                if(result >= 97 & result <= 122) {
                                	char result2 = (char)result;
                                	lbResult += String.valueOf(result2);
                                }
                                else {
                                	lbResult += String.valueOf(result);
                                }
                                resultLabel.setText(lbResult);
                                terminalTF.setText(String.valueOf(result));
                            } else {
                                System.out.println("!!! NOT OUT Instruction !!!");
                            }
                            result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                            System.out.println("P_I returns: " + result);
                            Main.cpu.setPC(Main.cpu.getPC() + 1);
                        }
                        LabelValR0.setText(String.valueOf(Main.cpu.getGPRValue(0)));
                        LabelValR1.setText(String.valueOf(Main.cpu.getGPRValue(1)));
                        LabelValR2.setText(String.valueOf(Main.cpu.getGPRValue(2)));
                        LabelValR3.setText(String.valueOf(Main.cpu.getGPRValue(3)));
                        setCheckBox(Main.cpu.getGPRValue(0), R0ObjArray);
                        setCheckBox(Main.cpu.getGPRValue(1), R1ObjArray);
                        setCheckBox(Main.cpu.getGPRValue(2), R2ObjArray);
                        setCheckBox(Main.cpu.getGPRValue(3), R3ObjArray);
                        totalSumR0 = Main.cpu.getGPRValue(0);
                        totalSumR1 = Main.cpu.getGPRValue(1);
                        totalSumR2 = Main.cpu.getGPRValue(2);
                        totalSumR3 = Main.cpu.getGPRValue(3);
                        txtI1Val.setText(String.valueOf(Main.cpu.getIRValue(1)));
                        txtI2Val.setText(String.valueOf(Main.cpu.getIRValue(2)));
                        txtI3Val.setText(String.valueOf(Main.cpu.getIRValue(3)));
                        LabelValPC.setText(String.valueOf(Main.cpu.getPC()));
                        txtCC0Val.setText(String.valueOf(Main.cpu.getCCValue(0)));
                        txtCC1Val.setText(String.valueOf(Main.cpu.getCCValue(1)));
                        txtCC2Val.setText(String.valueOf(Main.cpu.getCCValue(2)));
                        txtCC3Val.setText(String.valueOf(Main.cpu.getCCValue(3)));
                    }
                }
            }
        };

        EventHandler<ActionEvent> eventHandlerCC0 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setCC0btn.toString())) {
                    Main.cpu.setCCValue(0, Boolean.parseBoolean(txtCC0Val.getText().trim()));
                }
            }
        };
        EventHandler<ActionEvent> eventHandlerCC1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setCC1btn.toString())) {
                    Main.cpu.setCCValue(1, Boolean.parseBoolean(txtCC1Val.getText().trim()));
                }
            }
        };
        EventHandler<ActionEvent> eventHandlerCC2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setCC2btn.toString())) {
                    Main.cpu.setCCValue(2, Boolean.parseBoolean(txtCC2Val.getText().trim()));
                }
            }
        };
        EventHandler<ActionEvent> eventHandlerCC3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource().toString().equals(setCC3btn.toString())) {
                    Main.cpu.setCCValue(3, Boolean.parseBoolean(txtCC3Val.getText().trim()));
                }
            }
        };

        btnLoad.setOnAction(new EventHandler<ActionEvent>() {
            Translate translate;
            @Override
            public void handle(ActionEvent event) {
            	if(terminalTF.getText().equals("Program2.txt")){
            		firstInst = 492;
            		lastInst = 492;
            		try (BufferedReader br = new BufferedReader(new FileReader(new File("" + System.getProperty("user.dir").trim()
                        + "//" + terminalTF.getText().trim())))) {
            			String currLine = "";
            			while ((currLine = br.readLine()) != null) {
            				translate = new Translate(currLine);
            				instNumber = translate.stringBuilder();
            				Main.myCache.write(lastInst, instNumber);
            				lastInst++;
            			}
            			lastInst--;
            			Main.cpu.setPC(492);
            			System.out.println("the last instruction is at " + lastInst);
            		} catch (FileNotFoundException e) {
            			e.printStackTrace();
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
            		try {
						String content = new Scanner(new File("sentence.txt")).useDelimiter("\\Z").next();
						for(int i = 0; i < content.length(); i++) {
							Main.myCache.write(1024 + i, (short)content.charAt(i));
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	else{
            		firstInst = 503;
            		lastInst = 503;
            		try (BufferedReader br = new BufferedReader(new FileReader(new File("" + System.getProperty("user.dir").trim()
                        + "//" + terminalTF.getText().trim())))) {
            			String currLine = "";
            			while ((currLine = br.readLine()) != null) {
            				translate = new Translate(currLine);
            				instNumber = translate.stringBuilder();
            				Main.myCache.write(lastInst, instNumber);
            				lastInst++;
            			}
            			lastInst--;
            			Main.cpu.setPC(503);
            		} catch (FileNotFoundException e) {
            			e.printStackTrace();
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
            	}
            }
        });
        btnRUN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = firstInst; i <= 700; i++) {
                    //if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) >= 10 & (Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) <= 17) {
                      //  result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                    //} else {
                        if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 61) {
                            isRunClicked = true;
                            btnSSS.fire();
                            break;
                        } else if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 62) {
                            result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                            if(result >= 97 & result <= 122) {
                            	char result2 = (char)result;
                            	lbResult += String.valueOf(result2);
                            }
                            else {
                            	lbResult += String.valueOf(result);
                            }
                            resultLabel.setText(lbResult);
                            terminalTF.setText(String.valueOf(result));
                        } else {
                            result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                        }
                    //}
                    Main.cpu.setPC(Main.cpu.getPC() + 1);
                    LabelValR0.setText(String.valueOf(Main.cpu.getGPRValue(0)));
                    LabelValR1.setText(String.valueOf(Main.cpu.getGPRValue(1)));
                    LabelValR2.setText(String.valueOf(Main.cpu.getGPRValue(2)));
                    LabelValR3.setText(String.valueOf(Main.cpu.getGPRValue(3)));
                    setCheckBox(Main.cpu.getGPRValue(0), R0ObjArray);
                    setCheckBox(Main.cpu.getGPRValue(1), R1ObjArray);
                    setCheckBox(Main.cpu.getGPRValue(2), R2ObjArray);
                    setCheckBox(Main.cpu.getGPRValue(3), R3ObjArray);
                    totalSumR0 = Main.cpu.getGPRValue(0);
                    totalSumR1 = Main.cpu.getGPRValue(1);
                    totalSumR2 = Main.cpu.getGPRValue(2);
                    totalSumR3 = Main.cpu.getGPRValue(3);
                    txtI1Val.setText(String.valueOf(Main.cpu.getIRValue(1)));
                    txtI2Val.setText(String.valueOf(Main.cpu.getIRValue(2)));
                    txtI3Val.setText(String.valueOf(Main.cpu.getIRValue(3)));
                    LabelValPC.setText(String.valueOf(Main.cpu.getPC()));
                    txtCC0Val.setText(String.valueOf(Main.cpu.getCCValue(0)));
                    txtCC1Val.setText(String.valueOf(Main.cpu.getCCValue(1)));
                    txtCC2Val.setText(String.valueOf(Main.cpu.getCCValue(2)));
                    txtCC3Val.setText(String.valueOf(Main.cpu.getCCValue(3)));

                }
            }
        });
        btnSIT.setOnAction(new EventHandler<ActionEvent>() {
            Translate translate;

            @Override
            public void handle(ActionEvent event) {
                translate = new Translate(terminalTF.getText().trim());
                instNumber = translate.stringBuilder();
                Main.cpu.setPC(1);
                Main.myCache.write(1, instNumber);
                if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 61) {
                    terminalTF.setText("");
                    terminalTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode().equals(KeyCode.ENTER)) {
                            	String temp = terminalTF.getText();
                            	if(temp.charAt(0) >= 97 & temp.charAt(0) <= 122) {
                            		resultIN = (int)temp.charAt(0);
                            	}
                            	else {
                            		resultIN = Integer.parseInt(temp);
                            	}
                                Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                                System.out.println("RESULT NEW: " + result);
                            }
                            LabelValR0.setText(String.valueOf(Main.cpu.getGPRValue(0)));
                            LabelValR1.setText(String.valueOf(Main.cpu.getGPRValue(1)));
                            LabelValR2.setText(String.valueOf(Main.cpu.getGPRValue(2)));
                            LabelValR3.setText(String.valueOf(Main.cpu.getGPRValue(3)));
                            setCheckBox(Main.cpu.getGPRValue(0), R0ObjArray);
                            setCheckBox(Main.cpu.getGPRValue(1), R1ObjArray);
                            setCheckBox(Main.cpu.getGPRValue(2), R2ObjArray);
                            setCheckBox(Main.cpu.getGPRValue(3), R3ObjArray);
                            totalSumR0 = Main.cpu.getGPRValue(0);
                            totalSumR1 = Main.cpu.getGPRValue(1);
                            totalSumR2 = Main.cpu.getGPRValue(2);
                            totalSumR3 = Main.cpu.getGPRValue(3);
                            txtI1Val.setText(String.valueOf(Main.cpu.getIRValue(1)));
                            txtI2Val.setText(String.valueOf(Main.cpu.getIRValue(2)));
                            txtI3Val.setText(String.valueOf(Main.cpu.getIRValue(3)));
                            LabelValPC.setText(String.valueOf(Main.cpu.getPC()));
                            txtCC0Val.setText(String.valueOf(Main.cpu.getCCValue(0)));
                            txtCC1Val.setText(String.valueOf(Main.cpu.getCCValue(1)));
                            txtCC2Val.setText(String.valueOf(Main.cpu.getCCValue(2)));
                            txtCC3Val.setText(String.valueOf(Main.cpu.getCCValue(3)));
                        }
                    });
                } else if ((Main.myCache.read(Main.cpu.getPC()) >> 10 & 0x3F) == 62) {
                    result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                    //lbResult.concat(String.valueOf(result));
                    if(result >= 97 & result <= 122) {
                    	char result2 = (char)result;
                    	lbResult += String.valueOf(result2);
                    }
                    else{
                    	lbResult += String.valueOf(result);
                    }
                    resultLabel.setText(lbResult);
                    terminalTF.setText(String.valueOf(result));
                } else {
                    result = Main.cpu.process_instruction(Main.cpu.getPC(), Main.myCache);
                }
                LabelValR0.setText(String.valueOf(Main.cpu.getGPRValue(0)));
                LabelValR1.setText(String.valueOf(Main.cpu.getGPRValue(1)));
                LabelValR2.setText(String.valueOf(Main.cpu.getGPRValue(2)));
                LabelValR3.setText(String.valueOf(Main.cpu.getGPRValue(3)));
                setCheckBox(Main.cpu.getGPRValue(0), R0ObjArray);
                setCheckBox(Main.cpu.getGPRValue(1), R1ObjArray);
                setCheckBox(Main.cpu.getGPRValue(2), R2ObjArray);
                setCheckBox(Main.cpu.getGPRValue(3), R3ObjArray);
                totalSumR0 = Main.cpu.getGPRValue(0);
                totalSumR1 = Main.cpu.getGPRValue(1);
                totalSumR2 = Main.cpu.getGPRValue(2);
                totalSumR3 = Main.cpu.getGPRValue(3);
                txtI1Val.setText(String.valueOf(Main.cpu.getIRValue(1)));
                txtI2Val.setText(String.valueOf(Main.cpu.getIRValue(2)));
                txtI3Val.setText(String.valueOf(Main.cpu.getIRValue(3)));
                LabelValPC.setText(String.valueOf(Main.cpu.getPC()));
                txtCC0Val.setText(String.valueOf(Main.cpu.getCCValue(0)));
                txtCC1Val.setText(String.valueOf(Main.cpu.getCCValue(1)));
                txtCC2Val.setText(String.valueOf(Main.cpu.getCCValue(2)));
                txtCC3Val.setText(String.valueOf(Main.cpu.getCCValue(3)));
            }
        });

        setCC0btn.setOnAction(eventHandlerCC0);
        setCC1btn.setOnAction(eventHandlerCC1);
        setCC2btn.setOnAction(eventHandlerCC2);
        setCC3btn.setOnAction(eventHandlerCC3);



        setI1btn.setOnAction(eventHandlerI1Line);
        setI2btn.setOnAction(eventHandlerI2Line);
        setI3btn.setOnAction(eventHandlerI3Line);
        btnDisplay.setOnAction(eventHandlerDisplay);
        btnSSS.setOnAction(eventHandlerSSS);
        //R1 register Set EventHandler
        setR0Btn.setOnAction(eventHandlerR0Line);
        R0_B1.setOnAction(eventHandlerR0Line);
        R0_B2.setOnAction(eventHandlerR0Line);
        R0_B4.setOnAction(eventHandlerR0Line);
        R0_B8.setOnAction(eventHandlerR0Line);
        R0_B16.setOnAction(eventHandlerR0Line);
        R0_B32.setOnAction(eventHandlerR0Line);
        R0_B64.setOnAction(eventHandlerR0Line);
        R0_B128.setOnAction(eventHandlerR0Line);
        R0_B256.setOnAction(eventHandlerR0Line);
        R0_B512.setOnAction(eventHandlerR0Line);
        R0_B1024.setOnAction(eventHandlerR0Line);
        R0_B2048.setOnAction(eventHandlerR0Line);
        R0_B4096.setOnAction(eventHandlerR0Line);
        R0_B8192.setOnAction(eventHandlerR0Line);
        R0_B16384.setOnAction(eventHandlerR0Line);
        R0_B32768.setOnAction(eventHandlerR0Line);
        //R1 register Set EventHandler
        setR1Btn.setOnAction(eventHandlerR1Line);
        R1_B1.setOnAction(eventHandlerR1Line);
        R1_B2.setOnAction(eventHandlerR1Line);
        R1_B4.setOnAction(eventHandlerR1Line);
        R1_B8.setOnAction(eventHandlerR1Line);
        R1_B16.setOnAction(eventHandlerR1Line);
        R1_B32.setOnAction(eventHandlerR1Line);
        R1_B64.setOnAction(eventHandlerR1Line);
        R1_B128.setOnAction(eventHandlerR1Line);
        R1_B256.setOnAction(eventHandlerR1Line);
        R1_B512.setOnAction(eventHandlerR1Line);
        R1_B1024.setOnAction(eventHandlerR1Line);
        R1_B2048.setOnAction(eventHandlerR1Line);
        R1_B4096.setOnAction(eventHandlerR1Line);
        R1_B8192.setOnAction(eventHandlerR1Line);
        R1_B16384.setOnAction(eventHandlerR1Line);
        R1_B32768.setOnAction(eventHandlerR1Line);
        //R2 register Set EventHandler
        setR2Btn.setOnAction(eventHandlerR2Line);
        R2_B1.setOnAction(eventHandlerR2Line);
        R2_B2.setOnAction(eventHandlerR2Line);
        R2_B4.setOnAction(eventHandlerR2Line);
        R2_B8.setOnAction(eventHandlerR2Line);
        R2_B16.setOnAction(eventHandlerR2Line);
        R2_B32.setOnAction(eventHandlerR2Line);
        R2_B64.setOnAction(eventHandlerR2Line);
        R2_B128.setOnAction(eventHandlerR2Line);
        R2_B256.setOnAction(eventHandlerR2Line);
        R2_B512.setOnAction(eventHandlerR2Line);
        R2_B1024.setOnAction(eventHandlerR2Line);
        R2_B2048.setOnAction(eventHandlerR2Line);
        R2_B4096.setOnAction(eventHandlerR2Line);
        R2_B8192.setOnAction(eventHandlerR2Line);
        R2_B16384.setOnAction(eventHandlerR2Line);
        R2_B32768.setOnAction(eventHandlerR2Line);
        //R3 register Set EventHandler
        setR3Btn.setOnAction(eventHandlerR3Line);
        R3_B1.setOnAction(eventHandlerR3Line);
        R3_B2.setOnAction(eventHandlerR3Line);
        R3_B4.setOnAction(eventHandlerR3Line);
        R3_B8.setOnAction(eventHandlerR3Line);
        R3_B16.setOnAction(eventHandlerR3Line);
        R3_B32.setOnAction(eventHandlerR3Line);
        R3_B64.setOnAction(eventHandlerR3Line);
        R3_B128.setOnAction(eventHandlerR3Line);
        R3_B256.setOnAction(eventHandlerR3Line);
        R3_B512.setOnAction(eventHandlerR3Line);
        R3_B1024.setOnAction(eventHandlerR3Line);
        R3_B2048.setOnAction(eventHandlerR3Line);
        R3_B4096.setOnAction(eventHandlerR3Line);
        R3_B8192.setOnAction(eventHandlerR3Line);
        R3_B16384.setOnAction(eventHandlerR3Line);
        R3_B32768.setOnAction(eventHandlerR3Line);
        //Instruction register Set EventHandler
        setBtnInst.setOnAction(eventHandlerInstLine);
        Inst_B1.setOnAction(eventHandlerInstLine);
        Inst_B2.setOnAction(eventHandlerInstLine);
        Inst_B4.setOnAction(eventHandlerInstLine);
        Inst_B8.setOnAction(eventHandlerInstLine);
        Inst_B16.setOnAction(eventHandlerInstLine);
        Inst_B32.setOnAction(eventHandlerInstLine);
        Inst_B64.setOnAction(eventHandlerInstLine);
        Inst_B128.setOnAction(eventHandlerInstLine);
        Inst_B256.setOnAction(eventHandlerInstLine);
        Inst_B512.setOnAction(eventHandlerInstLine);
        Inst_B1024.setOnAction(eventHandlerInstLine);
        Inst_B2048.setOnAction(eventHandlerInstLine);
        Inst_B4096.setOnAction(eventHandlerInstLine);
        Inst_B8192.setOnAction(eventHandlerInstLine);
        Inst_B16384.setOnAction(eventHandlerInstLine);
        Inst_B32768.setOnAction(eventHandlerInstLine);
        //PC register Set EventHandler
        setPCBtn.setOnAction(eventHandlerPC);
    }

    private void setCheckBox(int value, CheckBox[] checkBoxes) {
        for (int i = 15; i >= 0; i--) {
            if (((value >> i) & 0x1) == 0) {
                checkBoxes[i].setSelected(false);
            } else {
                checkBoxes[i].setSelected(true);
            }
        }
    }
}
