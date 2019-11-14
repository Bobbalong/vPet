package vPetSrc;

import java.util.*;

import javax.swing.ImageIcon;

public class intro {
	
	Scanner introScn;

	public static boolean intro = false;
	public static String userName, petName, petType;
	
	public static void introQuiz() {
		
		while (!vPet.isActive) {			
			
			loadCapture();
			
			petSim.cycle();
			
			intro = true;
			
			}
		}
	
	public static void loadCapture() {
		
		Scanner introScn = new Scanner(System.in);
    
		Tools.labeler("Enter 'load' or a new username.");
		System.out.println("Enter 'load' or a new username."); 
		
		userName = introScn.nextLine();	 
	    
	    loadCheck();
	    
		//introScn.close();
	}
	
	public static void loadCheck() {
		
		if (userName.equalsIgnoreCase("load")) {
		    System.out.println("Loading...");
			Saving.readSave('C');
			Saving.readSave('L');
			intro = true;
		}
		else {
			Tools.labeler("Username is: " + userName);
			System.out.println("Username is: " + userName);
		    vPet.creation('E', userName);
		    specCapture();
		}
	}	
	
	public static void specCapture() {
		Scanner introScn = new Scanner(System.in);

	    Tools.labeler("Choose pet type. \"snek\" or \"slime\"");
	    System.out.println("Choose pet type. \"snek\" or \"slime\"");
	    
	    petType = introScn.nextLine();
	    
	    specCheck();

		eggSet();		

		//introScn.close();
	}
	
	public static void specCheck() {
		
		boolean safe = false;
		
		for (int i = 0 ; i<vPet.speciesList.length ; i++) {
			if (petType.equalsIgnoreCase(vPet.speciesList[i])) {
				safe = true;
			}
		}
		if (!safe) {	  
		    Tools.labeler("Unknown Type: " + petType);
		    System.out.println("Unknown Type: " + petType);
	    	specCapture();	
		}
		else if (safe) {
		    vPet.creation('E', petType);
			Tools.labeler("Pet type chosen: " + petType);
			System.out.println("Pet type chosen: " + petType);
		}
	}
	
	public static void eggSet() {
		
		if (petType.equalsIgnoreCase(vPet.speciesList[1])) {
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/slimeEgg.png")));
			nameCapture();
		}
		else if (petType.equalsIgnoreCase(vPet.speciesList[0])) {
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/snekEgg.png")));
			nameCapture();
		}
	}

	public static void nameCapture() {
		Scanner introScn = new Scanner(System.in);
		
		Tools.labeler("Enter pet name");
		System.out.println("Enter pet name");
		
		petName = introScn.nextLine();
		vPet.creation('E', petName);
		
		Tools.labeler("Pet name is: " + petName);
		System.out.println("Pet name is: " + petName);
		
		vPet.creation('C', "");
				
		introScn.close();
	}
}
