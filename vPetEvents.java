package vPetRes;

import java.util.*;

import javax.swing.ImageIcon;

public class vPetEvents {
	
	
	static String newSelection;
	
	static int clockTick = 0;
	static int eventCycle = 0;
	static int setup = 0;
	
	
	static boolean waitForResponse = false;

	public static ArrayList<String> selections = new ArrayList<String>();
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void eventCalc (long age) {
		milestone(eventCycle);
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void milestone (int cycle) {
		if (setup>0) {
			switch (setup) {
			case 1:
				vPetGui.ownerName = vPetGui.FieldOneSelection;
				vPetGui.frmVpet.setTitle("vPet ("+vPetGui.ownerName+") main");
				waitForResponse = false;
				setup++;
				System.out.println("Setup 2");
				break;
			case 3:
				creation('E', vPetGui.FieldOneSelection);
				waitForResponse = false;
				setup++;
				System.out.println("Setup 3");
				break;
			}
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------lblvPetInsight
	public static void intro () {
		
		switch (setup) {
		case 0:
			vPetGui.lblvPetInsight.setText("Enter User Name or load vPet.");
			waitForResponse = true;
			System.out.println("Setup 0");
			break;
		case 2:
			vPetGui.lblvPetInsight.setText("What will you name your pet?");
			waitForResponse = true;
			System.out.println("Setup 2");
			break;
		case 4:
			vPetGui.frmVpet.getContentPane().add(vPetGui.lblImagemain);
			vPetEvents.creation('C', "");
			System.out.println("Setup 4");
			setup = 6;
			waitForResponse = false;
			break;
		case 5:
			vPetGui.lblvPetInsight.setText("Pet loaded, have fun :)");
			setup++;
			waitForResponse = false;
			break;
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void creation(char args, String selection) {
	
		if(args == 'E') {
			selections.add(selection);
			for (int i=0;i<selections.size();i++) {System.out.println(selections.get(i));};
			vPetGui.lblvPetInsight.setText("Thanks!");
			System.out.println(selections.size());
		}
	
		else if (args == 'C') {
			String[] tempDOBt = Tools.vPetCal();
			vPetSrc teaserPet = new vPetSrc (vPetEvents.selections.get(0), true, tempDOBt[1], tempDOBt[0], vPetGui.currentMillisTag);
			
		
		vPetGui.vPetActive = teaserPet.isActive;			
		vPetGui.vPetName = teaserPet.vName;		
		vPetGui.vPetMillisBorn = teaserPet.millisBorn;		
		vPetGui.vPetDOBt = teaserPet.vDOB[0];
		vPetGui.vPetDOBd = teaserPet.vDOB[1];
		vPetGui.vPetStomach = teaserPet.vStomach;
		vPetGui.vPetStomachMod = teaserPet.vStomachMod;
		vPetGui.lblDob.setText("D.O.B. "+vPetGui.vPetDOBd);
		
		Tools.vPetStomachGaugeChk();
		
		Tools.log(vPetGui.ownerName+" spawned "+vPetGui.vPetName+" on "+vPetGui.vPetDOBd+". It was a "+vPetGui.vPetStomachGauge+" Slime.");
		

		Tools.uiInit();
		} 
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void hungerTick () {														//hungerTick() This method is used to perform calculations on and reevaluate the vPet stomach
		
		if (vPetGui.vPetStomach==0) {														//Checks for empty energy
			vPetGui.vPetStomachMod *= 1.01;													//Adjusts stomach mod' if stomach found empty
			System.out.println("Stomach Empty "+vPetGui.vPetStomachMod);						//Msg
			Tools.log("Starved "+Math.round((vPetGui.vPetStomachMod) * 100.0) / 100.0);} 	//Log
		
		else if (vPetGui.vPetStomach>97) {													//Checks for full energy
			vPetGui.vPetStomachMod /= 1.01;													//Adjusts stomach mod' if stomach found full
			System.out.println("Stomach Full "+vPetGui.vPetStomachMod);						//Msg
			Tools.log("Stuffed "+Math.round((vPetGui.vPetStomachMod) * 100.0) / 100.0);} 	//Log
		
		vPetGui.vPetStomach -= vPetGui.vPetStomachMod;										//Hunger tick, stomach energy decreases
		Tools.breadTick += vPetGui.vPetStomachMod;												//Bread cooldown increments
		
		if (Tools.breadLimit>0) {																	//Check if stomach on cooldown		
			Tools.breadLimit--;																	//Stomach cooldown increments
			Tools.messages("breadLimitDown");												//Msg
		}
				
		vPetGui.vPetStomach = Math.max(0, Math.min(101, vPetGui.vPetStomach));				//Constrain stomach to (0 - 100)
		vPetGui.vPetStomachMod = Math.max(0.5, Math.min(1.50, vPetGui.vPetStomachMod));		//Constrain stomach mod to (0.5 - 1.5)
		
		if (vPetGui.vPetRng>40) {															
			if (vPetGui.vPetStomachMod<0) {vPetGui.vPetPoop+=3;} 							//poop stack...		
			else if (vPetGui.vPetStomachMod>0) {vPetGui.vPetPoop+=2;}						//poop stack...
			Tools.messages("msg");	//Msg
		}
		Tools.messages("Hunger");															//Msg
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void clean() {															//Clean() used to remove unhygienic things from environment or pet
		
		if (vPetGui.vPetPood>0) {															//Check for Poo
			vPetGui.vPetPood--;																//Poo stack decreases
			Tools.messages("cleanPoo");	}													//msg
		
		if (vPetGui.vPetPood==0) {
			vPetGui.lblIconlower.setIcon(null);
			vPetGui.lblIconMid.setIcon(null);
			vPetGui.vPetStink=false;}														//Remove poo from UI
		
		System.out.println(vPetGui.vPetPood);
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String dirtTick() {
		
		double dirtMod=1.1;
		String hygieneState = "unChecked";
		
		if (vPetGui.vPetPood==0) {vPetGui.vPetStink=false;vPetGui.lblIconMid.setIcon(null);}
		
		if (vPetGui.vPetPood>0) {dirtMod *= vPetGui.vPetPood+1;}
		
		//teaserPet.vCleanliness
		return hygieneState;
	}
}



