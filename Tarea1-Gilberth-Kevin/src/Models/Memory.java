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
public class Memory {
    private int memorySize=0;
    private int actualMemoryPosition=0;
    private int availableInstruction=0;
    private  HashMap<String, Integer> memoryRegister = new HashMap<String, Integer>(){{
        put("AC",0);
        put("AX",0);
        put("BX",0);
        put("CX",0);
        put("DX",0);
    }};
    
    
     public Memory(int pMemorySize){
         setMemorySize(pMemorySize);
         setInitialMemoryPosition();
         setAvailableInstruction();
         
    }
     
     public int[] executeLoad(Instruction pInstruction){
         memoryRegister.replace("AC",   memoryRegister.get(pInstruction.getInstructionRegister()));
         return getMemoryValues();
     }
     
     public int[] executeStore(Instruction pInstruction){
         memoryRegister.replace(pInstruction.getInstructionRegister(), memoryRegister.get("AC"));
         return getMemoryValues();
     }
     
     public int[] executeMov(Instruction pInstruction){
         memoryRegister.replace(pInstruction.getInstructionRegister(), pInstruction.getInstructionNumberValue());
         
         return getMemoryValues();
     }
     public int[] executeMov2(Instruction pInstruction){
         int register = memoryRegister.get(pInstruction.getInstructionNumberValueR());
         memoryRegister.replace(pInstruction.getInstructionRegister(), register);
         return getMemoryValues();
     }
     
     public int[] executeSwap(Instruction pInstruction){
         System.out.println("Jajaja" + pInstruction.getInstructionNumberValueR());
         System.out.println("Yisus" + pInstruction.getInstructionRegister());
         int number = memoryRegister.get(pInstruction.getInstructionNumberValueR());
         int number2 = memoryRegister.get(pInstruction.getInstructionRegister());
         memoryRegister.replace(pInstruction.getInstructionRegister(), number);
         memoryRegister.replace(pInstruction.getInstructionNumberValueR(), number2 );
         return getMemoryValues();
     }
     
     public int[] executeSub(Instruction pInstruction){
         int acRegisterValue = memoryRegister.get("AC");
         memoryRegister.replace("AC",   acRegisterValue-memoryRegister.get(pInstruction.getInstructionRegister()));
         return getMemoryValues();
     }
     
     public int[] executeAdd(Instruction pInstruction){
         int acRegisterValue = memoryRegister.get("AC");
         memoryRegister.replace("AC",   acRegisterValue+memoryRegister.get(pInstruction.getInstructionRegister()));
         return getMemoryValues();
     }
     
     public int[] executeINC(Instruction pInstruction){
         int acRegisterValue = memoryRegister.get("AC");
         memoryRegister.replace("AC", acRegisterValue + 1);
         return getMemoryValues();
     }
     
     public int[] executeINC2(Instruction pInstruction){
         memoryRegister.replace(pInstruction.getInstructionRegister(),1 + memoryRegister.get(pInstruction.getInstructionRegister()));
         return getMemoryValues();
     }
     
     public int [] executeDEC(Instruction pInstruction){
         int acRegisterValue = memoryRegister.get("AC");
         memoryRegister.replace("AC", acRegisterValue - 1);
         return getMemoryValues();
     }
     
     public int[] executeDEC2(Instruction pInstruction){
         memoryRegister.replace(pInstruction.getInstructionRegister(),memoryRegister.get(pInstruction.getInstructionRegister())-1);
         return getMemoryValues();
     }
     
     public int[] getMemoryValues(){
         int[] intArray = new int[] {this.getMemoryPosition(),memoryRegister.get("AC"),memoryRegister.get("AX"),memoryRegister.get("BX"),
         memoryRegister.get("CX"),memoryRegister.get("DX")};
          System.out.println( this.actualMemoryPosition);
         this.actualMemoryPosition++;
         this.availableInstruction--;
         return   intArray;
     }
    
     
    
    public int getAvailableInstruction(){
        return availableInstruction;
    }
    
    
    public int getMemoryPosition() {
        return this.actualMemoryPosition;
    }
   
    
    
    public int getMemorySize() {
        return memorySize-9;
    }
    
    
    public void setAvailableInstruction(){
        this.availableInstruction=getMemorySize();
    }
    
    
    public void setMemorySize(int pMemorySize) {
        this.memorySize = pMemorySize;
    }
    
     public void setInitialMemoryPosition() {
         if(this.memorySize==10){
             this.actualMemoryPosition=10;
         }else{
             int position=(int)(Math.random()*this.memorySize);
             while(position<10){
                 position=(int)(Math.random()*this.memorySize);
             }
             this.actualMemoryPosition=position;
             
         }
         
    }
    
   
}
