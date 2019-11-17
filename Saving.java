package vPetSrc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Saving {
	
	static String[] states = {"eventLog", 
							"Owner Name", "Active", "Species", 
							"Pet Name", "PetMillisBorn", "DOB time", "DOB", 
							"Stomach", "Stomach Tol", "Stomach Max", "Stomach Mod", "Stomach Cap", "Stomach Load", "Bowel", 
							"Bread", "Bread Tick", 
							"Hygiene", "Hygiene Tol", 
							"Energy","Energy Tol", "Energy Mod" };

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

			eventLog = saveDataIn.get(								0);
			
    		vPet.owner = saveDataIn.get(							1);			
    		vPet.isActive = Boolean.parseBoolean(saveDataIn.get(	2));
    		vPet.species = saveDataIn.get(							3);    		
    		vPet.name = saveDataIn.get(								4);
    		vPet.millisBorn = Long.parseLong(saveDataIn.get(		5));
    		vPet.DOB[0] = saveDataIn.get(							6);
    		vPet.DOB[1] = saveDataIn.get(							7);
    		vPet.stomach = Double.valueOf(saveDataIn.get(			8));
    		vPet.stomachTol = Double.valueOf(saveDataIn.get(		9));    		
    		vPet.stomachMax = Double.valueOf(saveDataIn.get(		10));
    		vPet.stomachMod = Double.valueOf(saveDataIn.get(		11));
    		vPet.stomachCap = Integer.valueOf(saveDataIn.get(		12));
    		vPet.stomachLoad = Integer.parseInt(saveDataIn.get(		13));
			vPet.stomachBowel = Double.valueOf(saveDataIn.get(		14));
    		Menu.bread = Integer.parseInt(saveDataIn.get(			15));
    		Menu.breadTick = Double.valueOf(saveDataIn.get(			16));
    		vPet.hygiene = Double.valueOf(saveDataIn.get(			17));
    		vPet.hygieneTol = Double.valueOf(saveDataIn.get(		18));
    		vPet.energy = Double.valueOf(saveDataIn.get(			19));
    		vPet.energyTol = Double.valueOf(saveDataIn.get(			20));
    		vPet.energyMod = Double.valueOf(saveDataIn.get(			21));
			
			petSim.cycle();
			imgCycle.vPetAnim();
			
			Tools.labeler("Loaded");
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
		
		vPetValue.set(0, ""+eventLog);   

		vPetValue.set(1, ""+vPet.owner);   
		vPetValue.set(2, ""+vPet.isActive);
		vPetValue.set(3, ""+vPet.species);
		vPetValue.set(4, ""+vPet.name);
		vPetValue.set(5, ""+vPet.millisBorn);
		vPetValue.set(6, ""+vPet.DOB[0]);
		vPetValue.set(7, ""+vPet.DOB[1]);
		vPetValue.set(8, ""+vPet.stomach);
		vPetValue.set(9, ""+vPet.stomachTol);
		vPetValue.set(10, ""+vPet.stomachMax);
		vPetValue.set(11, ""+vPet.stomachMod);  
		vPetValue.set(12, ""+vPet.stomachCap);
		vPetValue.set(13, ""+vPet.stomachLoad); 	
		vPetValue.set(14, ""+vPet.stomachBowel);
		vPetValue.set(15, ""+Menu.bread);    
		vPetValue.set(16, ""+Menu.breadTick);  
		vPetValue.set(17, ""+vPet.hygiene);   
		vPetValue.set(18, ""+vPet.hygieneTol);  
		vPetValue.set(19, ""+vPet.energy);   
		vPetValue.set(20, ""+vPet.energyTol);   
		vPetValue.set(21, ""+vPet.energyMod);   
		 		
    	
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
