/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Models.Instruction;
import Models.Memory;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jimon
 */
public class CPUController {
    
    private FileContentHandler FileContent = new FileContentHandler();
    private int currentInstructionPosition=0;
    
    private ArrayList<Instruction> instructionList;
    private Memory memory;
    private ArrayList<JTextField> textFieldList;
    private JTable contentTable;
    public CPUController(ArrayList<JTextField> pTextFieldList){
        setTextFieldList(pTextFieldList);
    }
    
    
    public boolean loadInstructions(String pSelectedFileRoute, JList pContentGuiList, JTable pContentTable) throws IOException{
        
        instructionList = FileContent.getFileContent(pSelectedFileRoute, pContentGuiList);
        contentTable = pContentTable;
        if (this.memory.getMemorySize()>=instructionList.size()){
            return true;
        }else{return false;}
    }
    
    public void executeInstruction(){
        
        if(memory.getAvailableInstruction()>0 && currentInstructionPosition < instructionList.size() && memory.getMemoryPosition()<= memory.getMemorySize()+9){
            Instruction instruction = instructionList.get(currentInstructionPosition);
            
            switch(instruction.getInstructionOperator()){
                case "LOAD":
                    fillRegistersUI(memory.executeLoad(instruction), instruction.getInstructionName());
                    break;
                case "STORE":
                    fillRegistersUI(memory.executeStore(instruction), instruction.getInstructionName());
                    break;
                case "MOV":
                    fillRegistersUI(memory.executeMov(instruction), instruction.getInstructionName());
                    break;
                case "SUB":
                    fillRegistersUI(memory.executeSub(instruction), instruction.getInstructionName());
                    break;
                case "ADD":
                    fillRegistersUI(memory.executeAdd(instruction), instruction.getInstructionName());
                    break;
            }
        }
       
    }
    
    public void fillRegistersUI(int[] pRegistersValue, String pInstructionBeingExecuted){
        textFieldList.get(0).setText(String.valueOf(pRegistersValue[0]));
        textFieldList.get(1).setText(String.valueOf(pRegistersValue[1]));
        textFieldList.get(2).setText(pInstructionBeingExecuted);
        textFieldList.get(3).setText(String.valueOf(pRegistersValue[5]));
        textFieldList.get(4).setText(String.valueOf(pRegistersValue[4]));
        textFieldList.get(5).setText(String.valueOf(pRegistersValue[3]));
        textFieldList.get(6).setText(String.valueOf(pRegistersValue[2]));
        

        String data[] = {String.valueOf(pRegistersValue[0]), instructionList.get(currentInstructionPosition).getBinaryCode()};
        DefaultTableModel tblModel = (DefaultTableModel) contentTable.getModel();
        tblModel.addRow(data);
        currentInstructionPosition++;
    }
    
    public void setMemorySize(int pMemorySize){
        memory = new Memory(pMemorySize);
    }
    
    public void setTextFieldList(ArrayList<JTextField> pTextFieldList){
        this.textFieldList = pTextFieldList;
    }
    
    
}
