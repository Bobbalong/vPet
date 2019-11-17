package vPetSrc;

import javax.swing.ImageIcon;

public class intro {
	public static String userName, petName, petType;
	public static int introStep = 0;
	
	public static void introQuiz() {

		System.out.println("intro Q top");
		
		while (introStep < 6) {				
			if (introStep==0 | introStep==2 | introStep==4) {
				introStepper();
				System.out.println("intro Q loop "+introStep);
			}
			
		}		
		System.out.println("intro Q Bottom");
		}
	
	public static void introStepper() {

		System.out.println("introStepper "+introStep);
		
		switch (introStep) {
		
		case 0:
			Tools.labeler("Enter 'load' or a new username.");
			introStep++;
			break;
				
		case 1:
			userName = GUI.userInput;
			GUI.userInputNew = false;
			loadCheck();
			break;
			
		case 2:
		    Tools.labeler("Choose pet type. \"snek\" or \"slime\"");
		    introStep++;
			break;
			
			
		case 3:
			specCheck();
			GUI.userInputNew = false;
			break;
			
		case 4:
			Tools.labeler("Enter pet name");
		    introStep++;
			break;
			
			
		case 5:
			petName = GUI.userInput;
			GUI.userInputNew = false;					
			Tools.labeler("Pet name is: " + petName);
		    vPet.creation('E', petName);
			introStep++;
		    introStepper();
			break;
			
		case 6:		
			GUI.userInputNew = false;
		    introStep++;
			vPet.creation('C', "");
			break;
		}
		
	}
	
	public static void loadCheck() {
		
		if (userName.equalsIgnoreCase("load")) {
			introStep = 6;
			Tools.labeler("Loading...");
			Saving.readSave('C');
			Saving.readSave('L');
		}
		else {
			Tools.labeler("Username is: " + userName);
			System.out.println("Username is: " + userName);
		    vPet.creation('E', userName);
		    introStep++;
		    introStepper();
		}
	}
	
	public static void specCheck() {
		
		petType = GUI.userInput;
		
		boolean safe = false;
		
		for (int i = 0 ; i<vPet.speciesList.length ; i++) {
			if (petType.equalsIgnoreCase(vPet.speciesList[i])) {
				safe = true;
			}
		}
		if (!safe) {	  
		    Tools.labeler("Unknown Type: " + petType);
		    Tools.labeler("Please try \"nek\" or \"slime\"");
		}
		else if (safe) {
		    vPet.creation('E', petType);
			Tools.labeler("Pet type chosen: " + petType);
			eggSet();
		    introStep++;
		    introStepper();
		}
	}
	
	public static void eggSet() {
		
		if (petType.equalsIgnoreCase(vPet.speciesList[1])) {
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/slimeEgg.png")));
		}
		else if (petType.equalsIgnoreCase(vPet.speciesList[0])) {
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/snekEgg.png")));
		}
	}
}
