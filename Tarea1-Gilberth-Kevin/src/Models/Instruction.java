/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.HashMap;

/**
 *
 * @author jimon
 */
public class Instruction {
    
    static HashMap<String, String> binaryOperator = new HashMap<String, String>(){{
        put("LOAD","2");
        put("STORE","2");
        put("MOV","1");
        put("SUB","3");
        put("ADD","3");
        put("INC","1");
        put("DEC","1");
        put("SWAP","1");
        put("INT 20H","INT");
        put("INT 09H","INT");
        put("INT 10H","INT");
        put("JMP","2");
        put("CMP","2");
        put("JE","2");
        put("JNE","2");
        put("PARAM","3");
        put("PUSH","1");
        put("POP","1");
    }};
    static HashMap<String, String> binaryRegister = new HashMap<String, String>(){{
        put("AX","0001");
        put("BX","0010");
        put("CX","0011");
        put("DX","0100");
    }};
    private String InstructionName;
    private String instructionOperator;
    private String instructionRegister;
    private int instructionNumberValue;

    
    public Instruction(String pInstructionName,String pInstructionOperator,String pInstructionRegister,int pInstructionNumberValue){
        setInstructionName(pInstructionName);
        setInstructionOperator (pInstructionOperator);
        setInstructionRegister(pInstructionRegister);
        setInstructionNumberValue(pInstructionNumberValue);
        
       
    }
    
    public String getInstructionName() {
        return InstructionName;
    }

    public String getInstructionOperator() {
        return instructionOperator;
    }

    public String getInstructionRegister() {
        return instructionRegister;
    }

    public int getInstructionNumberValue() {
        return instructionNumberValue;
    }
    
    public String getBinaryInstructionOperator(){
        return binaryOperator.get(this.instructionOperator);
    }
    
    public String getBinaryInstructionRegister(){
        return binaryRegister.get(this.instructionRegister);
    }
    
    public String getBinaryInstructionNumberValue(){
        if(this.instructionOperator.equalsIgnoreCase("MOV")){
            int instructionValue = this.instructionNumberValue;
            int intFlag = (instructionValue>=2)?0:1;
            String binaryNumber = Integer.toBinaryString((intFlag==1) ? instructionValue*-1 : instructionValue*1);
            return BinaryInstructionNumberValueAux(binaryNumber,intFlag);
        }else{
            return "00000000";
        }
    }
    
    
    public String BinaryInstructionNumberValueAux(String pBinaryNumber, int pIntFlag){
         int requiredZeros = 8 - pBinaryNumber.length();
         String zeros=(pIntFlag==1)? "1":"0";
         for(int zerosAdded = 1; zerosAdded < requiredZeros; zerosAdded++){
             zeros += "0";
         }
         pBinaryNumber= zeros + pBinaryNumber;
         return pBinaryNumber;
    }
    
    public String getBinaryCode(){return getBinaryInstructionOperator()+" "+getBinaryInstructionRegister()+" "+getBinaryInstructionNumberValue();}
    
    private void setInstructionOperator(String pInstructionOperator) {
        this.instructionOperator = pInstructionOperator;
    }
    
    private void  setInstructionName(String pInstructionName) {
        this.InstructionName = pInstructionName;
    }

    private void setInstructionRegister(String pInstructionRegister) {
        this.instructionRegister = pInstructionRegister;
    }

    private void setInstructionNumberValue(int pInstructionNumberValue) {
        this.instructionNumberValue = pInstructionNumberValue;
    }
    
    
    
}
