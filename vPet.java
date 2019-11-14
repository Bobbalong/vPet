package vPetSrc;

import java.util.*;

public class vPet {
	
	public static String[] speciesList = {"Snek","Slime"};
	
	public static String owner, species, name;
	public static boolean isActive;
	
	public static String[] DOB = {"0", "0"};
	public static long millisBorn, millisAlive;
		
	public static double stomach, stomachTol, stomachMax;
	public static String stomachGauge;
	public static double stomachMod;
	public static int stomachCap, stomachLoad;	
	public static double stomachBowel;
	
	public static int poos;
	
	public static double hygiene, hygieneMod = 0.1, hygieneTol;
	public static String hygieneGuage;	
	
	public static double energy, energyTol, energyMod;
		

	public static ArrayList<String> selections = new ArrayList<String>();
	
	public vPet (String newOwner,String newName, boolean active, String DOBt, String DOBd, long millis, String spec) {
				
		owner = newOwner;
		name = newName;			
		isActive = active;					
		DOB[0] = DOBt;						
		DOB[1] = DOBd;						
		millisBorn = millis;				
		stomach = Tools.rnJesus(80)+10;	
		stomachTol = Tools.rnJesus(50)+10;;
		stomachMax = 100;
		stomachMod = 1;
		stomachLoad = 0;
		stomachCap = 3;
		hygiene = Tools.rnJesus(50)+49;
		hygieneTol = Tools.rnJesus(10)+89;
		
		if (spec.equalsIgnoreCase(speciesList[0])) {
			species = speciesList[0];
		}
		else if (spec.equalsIgnoreCase(speciesList[1])) {
			species = speciesList[1];
		}
		
		energy = Tools.rnJesus(10)+40;
		energyTol = Tools.rnJesus(10)+89;
		energyMod = 1;

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
			
			vPet virtualPet = new vPet (selections.get(0),selections.get(2), true, Tools.currentCalendar[0], Tools.currentCalendar[1], Tools.currentMillisTag, selections.get(1));
			
			petSim.cycle();

			Tools.labeler(vPet.owner+" spawned "+vPet.name+" on "+vPet.DOB[1]+" at "+vPet.DOB[0]+".");
			Tools.labeler("It was a "+petSim.stomachGaugeChk()+" and "+petSim.hygieneGaugeChk()+" "+vPet.species+".");
			Tools.log(vPet.owner+" spawned "+vPet.name+" on "+vPet.DOB[1]+" at "+vPet.DOB[0]+". It was a "+petSim.stomachGaugeChk()+" and "+petSim.hygieneGaugeChk()+" "+vPet.species+".");
		
		} 
	}
}
