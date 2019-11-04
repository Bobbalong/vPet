package vPetRes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class saving {
	
	static String saveFile = "resource/save.txt";
	static String saveFileLine = null;
	static ArrayList<String> saveDataIn = new ArrayList<String>();
	static String eventLog = "";
    
    public static void save() {

			System.out.println("Saving...");
    		prep();
    		for (int i = 0; i<vPetGui.vPetSaveStates.length ; i++) {
    			writeState(vPetGui.vPetValue.get(i));
    		}
    		System.out.println("Saved");
    }
	
    public static void readSave(char args) {
    	
        System.out.println("Reading save...");
    	
    	if (args=='C') {
    		try {

    			File fileOne = new File(saveFile);
    	            
    	        Scanner input = new Scanner(fileOne);


    	        while (input.hasNextLine()) {
    	            saveFileLine = input.nextLine();
    	            saveDataIn.add(saveFileLine);
    	            System.out.println(saveFileLine);
    	        }
    	        input.close();

    	        }
    		catch (Exception ex) {
    	            ex.printStackTrace();
    	    }
            System.out.println("Save checked");
    	}
    	
    	if (args=='L') {
    		
    		vPetGui.vPetActive = Boolean.parseBoolean(saveDataIn.get(0));
    		vPetGui.vPetName = saveDataIn.get(1);
    		long t1 = Long.parseLong(saveDataIn.get(2));
    		vPetGui.vPetMillisBorn = t1;
    		vPetGui.vPetDOBt = saveDataIn.get(3);
    		vPetGui.vPetDOBd = saveDataIn.get(4);
    		double t2 = Double.valueOf(saveDataIn.get(5));
    		vPetGui.vPetStomach = t2;
    		double t3 = Double.valueOf(saveDataIn.get(6));
    		vPetGui.vPetStomachMod = t3;
    		vPetGui.ownerName = saveDataIn.get(7);
    		int t4 = Integer.parseInt(saveDataIn.get(8));
    		Tools.loaves = t4;
    		double t5 = Double.valueOf(saveDataIn.get(9));
    		Tools.breadTick = t5;
    		int t6 = Integer.parseInt(saveDataIn.get(10));
    		Tools.breadLimit = t6;
    		int t7 = Integer.parseInt(saveDataIn.get(11));
			vPetGui.vPetPoop = t7;
			eventLog = saveDataIn.get(12);
			
            System.out.println("Loaded");
            
			//vPetGui.frmVpet.setTitle("vPet ("+vPetGui.ownerName+") main");
			//vPetGui.lblPetName.setText(vPetGui.vPetName);
			//vPetGui.frmVpet.getContentPane().add(vPetGui.lblImagemain);
			//vPetGui.lblDob.setText("D.O.B. "+vPetGui.vPetDOBd);
			
			Tools.uiInit();
	    }
    }

    public static void writeState(String args) {
    	try {
    		
    		FileWriter fileWriter = new FileWriter(saveFile, true);

    		BufferedWriter saveWriter = new BufferedWriter(fileWriter);
    		
    		saveWriter.write(args);
    		saveWriter.newLine();
    		saveWriter.flush();
    		saveWriter.close();
    	}
    	catch(IOException ex) {
    		System.out.println("Error writing to file '"+saveFile+"'");
    	}
    }
    
    public static void prep () {
    	
		vPetGui.vPetValue.set(0, ""+vPetGui.vPetActive);
		vPetGui.vPetValue.set(1, ""+vPetGui.vPetName);
		vPetGui.vPetValue.set(2, ""+vPetGui.vPetMillisBorn);
		vPetGui.vPetValue.set(3, ""+vPetGui.vPetDOBt);
		vPetGui.vPetValue.set(4, ""+vPetGui.vPetDOBd);
		vPetGui.vPetValue.set(5, ""+vPetGui.vPetStomach);
		vPetGui.vPetValue.set(6, ""+vPetGui.vPetStomachMod); 
		vPetGui.vPetValue.set(7, ""+vPetGui.ownerName);    	
		vPetGui.vPetValue.set(8, ""+Tools.loaves);    
		vPetGui.vPetValue.set(9, ""+Tools.breadTick);  
		vPetGui.vPetValue.set(10, ""+Tools.breadLimit);  
		vPetGui.vPetValue.set(11, ""+vPetGui.vPetPoop);    
		vPetGui.vPetValue.set(12, ""+eventLog);    		
    	
		for (int i = 0 ; i<vPetGui.vPetSaveStates.length ; i++) {
			System.out.println(vPetGui.vPetValue.get(i));
		}
		
    	try {    		
		
    		File file = new File(saveFile); 

    		if(file.delete()) { 
    			System.out.println("File deleted successfully"); 
    		} else { 
            System.out.println("Failed to delete the file"); 
    		}
    		
    		if (file.createNewFile()) {
    			System.out.println("File is created!");
    		} else {
    			System.out.println("File already exists.");
    		}
    	}
    	catch(IOException ex) {
    		System.out.println("Error writing to file '"+saveFile+"'");
    	}
    }
    	
}
