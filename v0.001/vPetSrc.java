package vPetRes;

import java.util.*;

public class vPetSrc {

	String vName;						
	String vDOBt;						
	String[] vDOB = {"0", "0"};			
	long millisBorn;					
	boolean isActive;					
	double vStomach;
	float vStomachMod;
	float vCleanliness;

	
	public vPetSrc (String name, boolean active, String DOBt, String DOBd, long millis) {	
				
		vName = name;			
		isActive = active;					
		vDOB[0] = DOBt;						
		vDOB[1] = DOBd;						
		millisBorn = millis;				
		vStomach = Tools.rnJesus(100+1);			
		vStomachMod = 1;
		vCleanliness = (float)Tools.rnJesus(50)+49;
		
	}
}
