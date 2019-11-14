package vPetSrc;

import java.text.DecimalFormat;
import java.util.*;

import javax.swing.ImageIcon;

public class petSim {
	
	public static int cycleTick;
	public static double dirtDmg;
	public static boolean intro = false;
	public static boolean needsSleep = false, asleep, needsToilet = false;
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------clock
	public static void cycle() {
		
		System.out.println("sim top");
		
		stomachCD();

			Thread cycle = new Thread() {				//This thread is used to cycle the pet checks (250ms)
			public void run() {
				try {
					
					while(true) {

						cycleTick++;						
						vPet.millisAlive = System.currentTimeMillis() - vPet.millisAlive;
						
						stomachGaugeChk();
						hygieneGaugeChk();
						bowelVoid();
						
						if(cycleTick == 40 | cycleTick == 80 | cycleTick == 120) {
							fatigueTick();
							bowelMove();
							Tools.messages("prompt");
						}
						
						if(cycleTick == 40 | cycleTick == 80) {
							dirtTick();
							hungerTick();
						}
						
						if(cycleTick>=120) {
							cycleTick = 0;
							Menu.breadCheck();
						}

						
						sleep(250);																//The Interval, 250ms					
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		cycle.start();
	}	


//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void hungerTick() {												//hungerTick() This method is used to perform calculations on and reevaluate the vPet stomach
		
		DecimalFormat df = new DecimalFormat("###.###");
		
		double i;
		
		if (vPet.stomach==0) {														//Checks for empty energy
			vPet.stomachMod *= 1.1;												//Adjusts stomach mod' if stomach found empty
			System.out.println("Stomach Empty "+vPet.stomachMod);					//Msg
			Tools.log("Starved "+Math.round((vPet.stomachMod) * 100.0) / 100.0);} 	//Log
		
		else if (vPet.stomach>97) {													//Checks for full energy
			vPet.stomachMod /= 1.1;												//Adjusts stomach mod' if stomach found full
			System.out.println("Stomach Full "+vPet.stomachMod);					//Msg
			Tools.log("Stuffed "+Math.round((vPet.stomachMod) * 100.0) / 100.0);} 	//Log
		
		if (vPet.stomachMod>=1) {
			i = (vPet.stomachMod-0.9) + .5;
			vPet.stomach -= (i);										//Hunger tick, stomach energy decreases		
		} 
		else if (vPet.stomachMod<1) {
			i = (1-vPet.stomachMod) + 1;			
			vPet.stomach -= (i);										//Hunger tick, stomach energy decreases	
		}
		Menu.breadTick += vPet.stomachMod;											//Bread cooldown increments
		
		vPet.stomach = Math.max(0, Math.min(101, vPet.stomach));					//Constrain stomach to (0 - 100)
		vPet.stomachMod = Math.max(0.5, Math.min(1.50, vPet.stomachMod));			//Constrain stomach mod to (0.5 - 1.5)
		
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void bowelMove() {
		
		double bowelRng = Tools.rnJesus(100);
		
		if (bowelRng>50) {															
			if (vPet.stomachMod<0) {
				vPet.stomachBowel+=5;
				}
			
			else if (vPet.stomachMod>0) {
				vPet.stomachBowel+=2.5;
				}
			
			Tools.messages("bowelMove");
			}
	}
	
	public static void bowelVoid() {
		
		if (GUI.frameSlide >= Home.toilet[0] && GUI.frameSlide <= Home.toilet[1]) {
			if (vPet.stomachBowel>49) {
				vPet.poos++;
				vPet.stomachBowel -= vPet.stomachBowel;
				Tools.messages("Mess");
				System.out.println("Bowels void: "+vPet.poos);
				}
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void stomachCD() {
		
		Timer stomachCDTick = new Timer();
		stomachCDTick.schedule(new TimerTask() {

		        @Override
		        public void run() {
		        	if (vPet.stomachLoad>0) {													//Check if stomach on cooldown		
		    			vPet.stomachLoad--;														//Stomach cooldown increments
		    			Tools.messages("stomachCD");										//Msg
		    			}
				}
		    }, 0, 10000);
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void dirtTick() {
		
		dirtDmg = vPet.hygieneMod;
		if (vPet.poos>0) {
			dirtDmg =+ vPet.poos;
		}
		if (vPet.hygieneTol<=Tools.rnJesus(100)) {
			dirtDmg *= .25;
		} else {dirtDmg /= .25;}
		
		vPet.hygiene -= dirtDmg;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String stomachGaugeChk() {
			
		String[] stomachStates = {"Emaciated" , "Starving", "Famished", "Raveneous", "Hungry", "Peckish",
										"Content", "Satiated", "Well-Fed", "Over-Fed", "Stuffed"};
		ArrayList<Double> iRank = new ArrayList<Double>();
		double iRankChunk;
		boolean caught = false;
			
			
		for (int i = 0 ; i<stomachStates.length ; i++ ) {
				
			iRankChunk = (vPet.stomachMax/stomachStates.length+1) * i;
		
			iRank.add(iRankChunk);
		}
			
		for (int i = 0 ; i<iRank.size() ; i++ ) {
			//System.out.print( "(" + iRank.get(i) + " " + stomachStates[i] + ") ");
			
			for ( int n = 1; n<iRank.size()-1 ; n++) {
				
				if ( vPet.stomach >= iRank.get(n) && vPet.stomach <= iRank.get(n+1) ) {
				
					if(!caught) {
						vPet.stomachGauge = stomachStates[n];
						//System.out.println(" Current rank is "+stomachStates[n]+" "+iRank.get(n));
						caught = true;
					}		
				} else if ( vPet.stomach <= iRank.get(n) ) {
				
					if(!caught) {
						vPet.stomachGauge = stomachStates[n-1];
						//System.out.println(" Current rank is "+stomachStates[n-1]+" "+iRank.get(n-1));
						caught = true;
					}		
				} else if ( vPet.stomach >= iRank.get(iRank.size()-1) ) {
				
					if(!caught) {
						vPet.stomachGauge = stomachStates[iRank.size()-1];					
						//System.out.println(" Current rank is "+stomachStates[iRank.size()-1]+" "+iRank.get(iRank.size()-1));
						caught = true;
					}		
				}
			}
		//System.out.println(" Done ("+stomachStates.length+"/"+iRank.size()+")");
		}
		GUI.lblStomach.setText("Stomach: "+ vPet.stomachGauge);
		return vPet.stomachGauge;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String hygieneGaugeChk() {
				
			String[] hygieneStates = {"Filthy", "Boggin'","Dirty", "Clean", "Spotless", "Shiny", "Immaculate"};
			ArrayList<Double> iRank = new ArrayList<Double>();
			double iRankChunk;
			boolean caught = false;
				
				
			for (int i = 0 ; i<hygieneStates.length ; i++ ) {
					
				iRankChunk = (100/hygieneStates.length+1) * i;
			
				iRank.add(iRankChunk);
			}
				
			for (int i = 0 ; i<iRank.size() ; i++ ) {
				//System.out.print( "(" + iRank.get(i) + " " + hygieneStates[i] + ") ");
				
				for ( int n = 1; n<iRank.size()-1 ; n++) {
					
					if ( vPet.hygiene >= iRank.get(n) && vPet.hygiene <= iRank.get(n+1) ) {
					
						if(!caught) {
							vPet.hygieneGuage = hygieneStates[n];
							//System.out.println(" Current rank is "+hygieneStates[n]+" "+iRank.get(n));
							caught = true;
						}		
					} else if ( vPet.hygiene <= iRank.get(n) ) {
					
						if(!caught) {
							vPet.hygieneGuage = hygieneStates[n-1];
							//System.out.println(" Current rank is "+hygieneStates[n-1]+" "+iRank.get(n-1));
							caught = true;
							}		
					} else if ( vPet.hygiene >= iRank.get(iRank.size()-1) ) {
					
						if(!caught) {
							vPet.hygieneGuage = hygieneStates[iRank.size()-1];					
							//System.out.println(" Current rank is "+hygieneStates[iRank.size()-1]+" "+iRank.get(iRank.size()-1));
							caught = true;
						}		
					}
				}
			//System.out.println(" Done ("+hygieneStates.length+"/"+iRank.size()+")");
			}
			GUI.lblHygiene.setText("Hygiene: "+ vPet.hygieneGuage);
			return vPet.hygieneGuage;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void fatigueTick() {
		if (vPet.energy>=0) {
			if (vPet.energy >= vPet.energyTol) {
				vPet.energy -= vPet.energyMod;
				System.out.println("t1");
			}
			else if (vPet.energy <= (vPet.energyTol/2)) {
				if (Tools.rnJesus(100)>vPet.energyTol) {
					vPet.energy -= vPet.energyMod + (vPet.energyTol/100);
					System.out.println("t3");	
				} else {
					vPet.energy -= vPet.energyMod + (vPet.energyTol/50);
					System.out.println("t4");
				}
			}
			else if (vPet.energy > (vPet.energyTol/2) && vPet.energy < vPet.energyTol) {
				if (Tools.rnJesus(100)>vPet.energyTol) {
					vPet.energy -= vPet.energyMod + (vPet.energyTol/100);
					System.out.println("t2");
				} else {
					vPet.energy -= (vPet.energyMod / 2);
					System.out.println("t0");
					}
			}
		}
		vPet.energy = Math.max(0, Math.min(100, vPet.energy));					//Constrain energy to (0 - 100)
		if ((!asleep) && vPet.energy<(vPet.energyTol/4)) {
			needsSleep = true;
		}
		if (asleep) {

			System.out.println("sleeping");
			
			vPet.energy += 10*vPet.energyMod;
			
			if (vPet.energy > (vPet.energyTol*.8)) {

				System.out.println("awake");
				GUI.lblImageMain.setVisible(true);
				GUI.lblBed.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Environment/bed.png")));				
				asleep = false;
			}
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------------------------------------------------

}
