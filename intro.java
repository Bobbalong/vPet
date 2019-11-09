package vPetSrc;

import java.util.*;

public class intro {

	public static void introQuiz() {
		
	    Scanner introScn = new Scanner(System.in);
	    String userName;
	    String petName;
	    
	    System.out.println("Enter username"); 
	    userName = introScn.nextLine();   
	       
	    vPet.creation('E', userName);
	    System.out.println("Username is: " + userName);  
	    	    
	    System.out.println("Enter pet name"); 
	    petName = introScn.nextLine();   

	    vPet.creation('E', petName);
	    System.out.println("Pet name is: " + petName);  
	    
	    introScn.close();

	    vPet.creation('C', "");
	    Saving.save();	   
	    petSim.cycle();
	}
}
