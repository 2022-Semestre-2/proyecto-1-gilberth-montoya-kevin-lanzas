/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import Models.Instruction;
/**
 *
 * @author jimon
 */
public class FileContentHandler {
    
    public FileContentHandler(){
    }
    
    @SuppressWarnings("unchecked") //In this case that warinig is shown because I am parsing directly the result of new FileReader(pSelectedFileRoute)
    public ArrayList<Instruction> getFileContent(String pSelectedFileRoute, JList pContentGuiList) throws FileNotFoundException, IOException{
         DefaultListModel<String> listModel = new DefaultListModel<>();
         ArrayList<Instruction> instructionList= new ArrayList<Instruction>();
        try {
            BufferedReader objReader = new BufferedReader(new FileReader(pSelectedFileRoute));
            String currentLine;
           
            while((currentLine = objReader.readLine() ) !=null){
                if(!currentLine.equals("")){
                    String [] instructionArray = currentLine.split(" ");
                    if(instructionArray.length == 1){
                        String operationName = instructionArray[0];
                        //String operationRegister = instructionArray[1].substring(0, 2);
                        int operationValue = (operationName.equals("MOV")) ?  Integer.parseInt(instructionArray[2]) : 0;
                        Instruction test = new Instruction(currentLine,operationName,"0",operationValue);
                        instructionList.add(test);   
                        listModel.addElement(currentLine);
                    }else{
                        if(instructionArray.length == 2){
                            System.out.println("AAAAAA");
                            String operationName = instructionArray[0];
                            String operationRegister = instructionArray[1].substring(0, 2);
                            int operationValue = (operationName.equals("MOV")) ?  Integer.parseInt(instructionArray[2]) : 0;
                            Instruction test = new Instruction(currentLine,operationName,operationRegister,operationValue);
                            instructionList.add(test);   
                            listModel.addElement(currentLine);
                        }else{
                            String comparative = instructionArray[2];
                            System.out.println(comparative);
                            if("AX".equals(comparative) || "BX".equals(comparative) ||"CX".equals(comparative) || "DX".equals(comparative)){
                                System.out.println("bbbbbbb");
                                String operationName = instructionArray[0];
                                String operationRegister = instructionArray[1].substring(0, 2);
                                String operationValue = instructionArray[2];
                                Instruction test = new Instruction(currentLine,operationName,operationRegister,operationValue);
                                instructionList.add(test);   
                                listModel.addElement(currentLine);
                            }else{
                                System.out.println("AAAAAA");
                                String operationName = instructionArray[0];
                                String operationRegister = instructionArray[1].substring(0, 2);
                                int operationValue = (operationName.equals("MOV")) ?  Integer.parseInt(instructionArray[2]) : 0;
                                Instruction test = new Instruction(currentLine,operationName,operationRegister,operationValue);
                                instructionList.add(test);   
                                listModel.addElement(currentLine);
                            }
                        }
                    }
                 
                    //System.out.println(test.getBinaryCode());
                }
            }
            objReader.close();
            pContentGuiList.setModel(listModel);
            return instructionList;
            
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
        return instructionList;
    }
}
