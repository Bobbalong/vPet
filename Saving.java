package vPetSrc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Saving {
	
	static String[] states = {"Active", "Pet Name", "PetMillisBorn", "DOB time", "DOB", "Stomach", "Stomach Mod", "Owner Name", "Bread", "Bread Tick", "Stomach Load", "Stomach Capacity", "Bowel", "Hygiene", "eventLog"};

	static ArrayList<String> vPetValue = new ArrayList<String>();
	
	static String saveFile = "resource/save.txt";
	static String saveFileLine = null;
	static ArrayList<String> saveDataIn = new ArrayList<String>();
	static String eventLog = "";
    
    public static void save() {

			System.out.println("Saving...");
    		prep();
    		for (int i = 0; i<states.length ; i++) {
    			writeState(vPetValue.get(i));
    		}
    		for (int i = 0; i<states.length ; i++) {
    			System.out.println(states[i]+" - "+vPetValue.get(i));
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
    		
    		vPet.isActive = Boolean.parseBoolean(saveDataIn.get(0));
    		vPet.name = saveDataIn.get(1);
    		long t1 = Long.parseLong(saveDataIn.get(2));
    		vPet.millisBorn = t1;
    		vPet.DOB[0] = saveDataIn.get(3);
    		vPet.DOB[1] = saveDataIn.get(4);
    		double t2 = Double.valueOf(saveDataIn.get(5));
    		vPet.stomach = t2;
    		double t3 = Double.valueOf(saveDataIn.get(6));
    		vPet.stomachMod = t3;
    		vPet.owner = saveDataIn.get(7);
    		int t4 = Integer.parseInt(saveDataIn.get(8));
    		Menu.bread = t4;
    		double t5 = Double.valueOf(saveDataIn.get(9));
    		Menu.breadTick = t5;
    		int t6 = Integer.parseInt(saveDataIn.get(10));
    		vPet.stomachLoad = t6;
    		int t7 = Integer.parseInt(saveDataIn.get(11));
			vPet.stomachBowel = t7;
			eventLog = saveDataIn.get(12);
			
            System.out.println("Loaded");
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
    
    public static void prep() {
    	
		for(int i=0;i<states.length;i++) {vPetValue.add("zero");};
    	
		vPetValue.set(0, ""+vPet.isActive);
		vPetValue.set(1, ""+vPet.name);
		vPetValue.set(2, ""+vPet.millisBorn);
		vPetValue.set(3, ""+vPet.DOB[0]);
		vPetValue.set(4, ""+vPet.DOB[1]);
		vPetValue.set(5, ""+vPet.stomach);
		vPetValue.set(6, ""+vPet.stomachMod); 
		vPetValue.set(7, ""+vPet.owner);    	
		vPetValue.set(8, ""+Menu.bread);    
		vPetValue.set(9, ""+Menu.breadTick);  
		vPetValue.set(10, ""+vPet.stomachLoad);
		vPetValue.set(11, ""+vPet.stomachCap);
		vPetValue.set(12, ""+vPet.stomachBowel);
		vPetValue.set(13, ""+vPet.hygiene);    
		
		vPetValue.set(14, ""+eventLog);    		
    	
		for (int i = 0 ; i<states.length ; i++) {
			System.out.println(vPetValue.get(i));
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
