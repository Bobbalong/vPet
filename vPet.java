package vPetSrc;

import java.util.*;

public class vPet {
	
	public static String owner;
	public static String species;
	public static String name;
	
	public static String[] DOB = {"0", "0"};
	public static long millisBorn;
	public static boolean isActive;
	
	public static double stomach;
	public static double stomachMax;
	public static String stomachGauge;
	public static double stomachMod;
	public static int stomachCap;
	public static int stomachLoad;	
	public static double stomachBowel;
	
	public static int poos;
	
	public static double hygiene;
	public static String hygieneGuage;
	
	public static double fatigue;
		

	public static ArrayList<String> selections = new ArrayList<String>();
	
	public vPet (String newOwner,String newName, boolean active, String DOBt, String DOBd, long millis) {
	    System.out.println("vPet ");	
				
		owner = newOwner;
		species = "Slime";
		name = newName;			
		isActive = active;					
		DOB[0] = DOBt;						
		DOB[1] = DOBd;						
		millisBorn = millis;				
		stomach = Tools.rnJesus(80)+10;	
		stomachMax = 100;
		stomachMod = 1;
		stomachLoad = 0;
		stomachCap = 3;
		hygiene = Tools.rnJesus(50)+49;

	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void creation(char args, String selection) {
	
		if(args == 'E') {
			selections.add(selection);
			for (int i=0;i<selections.size();i++) {System.out.println(selections.get(i));};
			System.out.println(selections.size());
		}
	
		else if (args == 'C') {
			
		    System.out.print("creation ");	
			
			Tools.vPetTime();
			
			vPet teaserPet = new vPet (selections.get(0),selections.get(1), true, Tools.currentCalendar[0], Tools.currentCalendar[1], Tools.currentMillisTag);
			
			petSim.spark();
			
			Tools.log(vPet.owner+" spawned "+vPet.name+" on "+vPet.DOB[1]+" at "+vPet.DOB[0]+". It was a "+vPet.stomachGauge+" and "+vPet.hygieneGuage+" "+vPet.species+".");
		
		} 
	}
}
