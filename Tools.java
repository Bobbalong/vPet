package vPetRes;

import java.util.*;

import javax.swing.ImageIcon;

public class Tools {
	
	static String oldLog = "";
	static boolean logX = false;
	
	static int loaves = 10;
	static double breadTick = 0;
	static double  breadValue;
	static int breadLimit = 0;
	
	public static void msgGen() {
		
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void messages(String message) {																		//This method provides msgs for a given situation
																														//	message()
		
		switch (message) {
		
		case "breadLimitDown":																							//Bread Cooldown (UI output and Log)
			vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" bubbles. (messages)");
			log("Bread Digested");
			break;
			
		case "breadEaten":																								//Eaten Bread (UI output and Log)
			vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" enjoyed some bread. (messages)");
			log("Bread Eaten:"+loaves+"/10 "+Math.round((vPetGui.vPetStomach * 100.0) / 100.0));
			break;
			
		case "bloated":																									//Bloated (UI output and Log)
			vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" is over filled and bloated. (messages)");
			log("Bloated");
			break;
			
		case "breadLimit":																								//Bread Limiter (UI output and Log)
			vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" has eaten too much bread recently... (messages)");
			log("Bread Limit");
			break;
			
		case "noBread":																									//No Bread (UI output and Log)
			vPetGui.lblvPetInsight.setText("No more bread left! (messages)");
			log("No Bread");
			break;
			
		case "Mess":																									//Pet Mess (UI output and Log)
			vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" made a mess... (messages)");
			log("Poo'd");
			break;
			
		case "cleanPoo":																								//Clean Mess (UI output and Log)
			if (vPetGui.vPetPood>0) {
				vPetGui.lblvPetInsight.setText("You clean... But there's still mess. (messages)");	
				log("Cleaned Poo");} 
			else if (vPetGui.vPetPood<1) {
				vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" will feel better no there's no mess. (messages)");	
				log("Cleaned Poo");}
			break;
			
		case "Hunger":																									//Hunger Tick (System Output)
			System.out.println("Hunger Tick");		
			break;

		case "msg":																									//
			if (Tools.rnJesus(100)>=50) {
				vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" makes a sighing noise. (messages)");
			} else if (Tools.rnJesus(100)<50) {
					vPetGui.lblvPetInsight.setText(vPetGui.vPetName+" squelches around. (messages)");
				}
			break;
		
		case "breadCD":	
			vPetGui.lblvPetInsight.setText("A Loaf cooled down... mmm (messages)");	
			log("BreadCD "+loaves+"/10"); 
			break;

		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static double rnJesus(double prayer) {
		double judgement = Math.random() * prayer + 1;
		return judgement;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void vPetStomachGaugeChk() {
		
		String[] stomachStates = {"Emaciated" , "Starving", "Famished", "Raveneous", "Hungry", "Peckish",
									"Content", "Satiated", "Well-Fed", "Over-Fed", "Stuffed"};
		ArrayList<Double> iRank = new ArrayList<Double>();
		double iRankChunk;
		boolean caught = false;
		
		
		for (int i = 0 ; i<stomachStates.length ; i++ ) {
			
			iRankChunk = (100/stomachStates.length+1) * i;
		
			iRank.add(iRankChunk);
		}
		
		for (int i = 0 ; i<iRank.size() ; i++ ) {
			//System.out.print( "(" + iRank.get(i) + " " + stomachStates[i] + ") ");
			
			for ( int n = 1; n<iRank.size()-1 ; n++) {
				
				if ( vPetGui.vPetStomach >= iRank.get(n) && vPetGui.vPetStomach <= iRank.get(n+1) ) {
				
					if(!caught) {
						vPetGui.vPetStomachGauge = stomachStates[n];
						//System.out.println(" Current rank is "+stomachStates[n]+" "+iRank.get(n));
						caught = true;
					}		
				} else if ( vPetGui.vPetStomach <= iRank.get(n) ) {
				
					if(!caught) {
						vPetGui.vPetStomachGauge = stomachStates[n-1];
						//System.out.println(" Current rank is "+stomachStates[n-1]+" "+iRank.get(n-1));
						caught = true;
					}		
				} else if ( vPetGui.vPetStomach >= iRank.get(iRank.size()-1) ) {
				
					if(!caught) {
						vPetGui.vPetStomachGauge = stomachStates[iRank.size()-1];					
						//System.out.println(" Current rank is "+stomachStates[iRank.size()-1]+" "+iRank.get(iRank.size()-1));
						caught = true;
					}		
				}
			}
		//System.out.println(" Done ("+stomachStates.length+"/"+iRank.size()+")");
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String vPetAgeStamp() {												/**/
		int seconds = (int) (vPetGui.vPetMillisAlive / 1000) % 60 ;
		int minutes = (int) ((vPetGui.vPetMillisAlive / (1000*60)) % 60);
		int hours   = (int) ((vPetGui.vPetMillisAlive / (1000*60*60)) % 24);
		String timestamp = "("+hours+":"+minutes+":"+seconds+")";
		return timestamp;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void toiletCheck() {
		
		if (vPetGui.vPetPoop>9) {
			vPetGui.vPetPood++;
			vPetGui.vPetPoop -= vPetGui.vPetPoop;
			Tools.messages("Mess");
			vPetGui.lblIconlower.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/slimePoo.png")));
			vPetGui.vPetStink=true;
			System.out.println("Bowels filled "+vPetGui.vPetPood);
		} else if (vPetGui.vPetPoop>4 && vPetGui.vPetPoop<10 && vPetGui.vPetRng>95) {
				vPetGui.vPetPood++;
				vPetGui.vPetPoop -= vPetGui.vPetPoop;
				Tools.messages("Mess");
				vPetGui.lblIconlower.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/slimePoo.png")));
				vPetGui.vPetStink=true;
				System.out.println("Bowel movement "+vPetGui.vPetPood);
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void breadCheck() {
		
		breadValue = 10 * vPetGui.vPetStomachMod;
		
		if (loaves==10) {
			breadTick=0;}
		if (loaves<10 && breadTick >= breadValue) {
			loaves++;
			breadTick -= breadValue;
			messages("breadCD");
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void log(String args) {
    	
    	saving.eventLog += args+vPetAgeStamp()+", ";
		
		/*
    	if (args == oldLog) {
    		saving.eventLog = saving.eventLog.substring(0, saving.eventLog.length()-2);
    		saving.eventLog += "x  ";
    		logX=true;
    	} else {
    		if (logX) {
        		saving.eventLog = saving.eventLog.substring(0, saving.eventLog.length()-2);
    			saving.eventLog += ", ";
    			saving.eventLog += args+vPetAgeStamp();
        		logX=false;
    		} else {saving.eventLog += args+vPetAgeStamp()+", ";}
    	}
    	oldLog = args;
    	*/
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static String[] vPetCal() {					/**/

		String[] currentCal = {"0", "0"};					
		
		Calendar vPetCal = new GregorianCalendar();			
		int day = vPetCal.get(Calendar.DAY_OF_MONTH);		
		int month = vPetCal.get(Calendar.MONTH);			
		int year = vPetCal.get(Calendar.YEAR);				

		int second = vPetCal.get(Calendar.SECOND);			
		int minute = vPetCal.get(Calendar.MINUTE);			
		int hour = vPetCal.get(Calendar.HOUR);				
		
		currentCal[0] = ""+day+":"+month+":"+year;			
		currentCal[1] = ""+hour+":"+minute+":"+second;		
		
		return currentCal;									
		
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void feed(String food) {													//feed() This method is used when the pet is given food
		
			switch (food) {																	//Food Choice
			
			case "Bread":																	//Bread
				
				if (breadLimit==2) {														//Check if stomach available 
					Tools.messages("breadLimit");											//msg
				}
				else if (breadLimit<2 && vPetGui.vPetStomach<99 && loaves<11 && loaves>0) {			//Checks bread stock and stomach cooldown
					vPetGui.vPetStomach += (10 * vPetGui.vPetStomachMod);					//Stomach contents increase
					vPetGui.vPetStomach = Math.max(0, Math.min(101, vPetGui.vPetStomach));	//Constrains stomach (0-100)
					loaves--;																//Bread Stock decreases
					breadLimit++;															//Stomach cooldown increase
					Tools.messages("breadEaten");											//Msg
				} 
				else if (breadLimit<2 && vPetGui.vPetStomach>99) {							//Check if stocmach full
					Tools.messages("bloated");												//Msg
				} 
				else if (loaves == 0) {														//Check Loaf stock
					Tools.messages("noBread");
				}
					break;
					
				case "Cake":																	//Bread
			
					break;
				}																				
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void uiInit() {
		
		vPetGui.frmVpet.setTitle("vPet ("+vPetGui.ownerName+") main");
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.lblImagemain);
		vPetGui.lblDob.setText("D.O.B. "+vPetGui.vPetDOBd);

		vPetGui.frmVpet.getContentPane().add(vPetGui.lblBanner);
		vPetGui.lblBanner.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Environment/bannerHome.png")));
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.lblPetName);
		vPetGui.lblPetName.setText(vPetGui.vPetName);
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.btnClean);
		vPetGui.btnClean.repaint();		
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.lblAge);
		vPetGui.lblAge.repaint();
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.lblDob);
		vPetGui.lblDob.setText("D.O.B. "+vPetGui.vPetDOBd);
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.lblbreadCount);
		vPetGui.lblbreadCount.setText("x"+Tools.loaves);
		
		vPetGui.lblH.setText("Hunger: ");		
		vPetGui.frmVpet.getContentPane().add(vPetGui.lblH);
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.btnSave);
		
		vPetGui.frmVpet.getContentPane().add(vPetGui.btnFeed);
		vPetGui.btnFeed.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Buttons/breadButton.png")));
		

		vPetGui.frmVpet.getContentPane().add(vPetGui.lblWeight);
		vPetGui.lblWeight.repaint();
		if (vPetGui.vPetStomachMod<0.75) {	vPetGui.lblWeight.setText("Wgt: D");					
		} else if (vPetGui.vPetStomachMod>1.25) {vPetGui.lblWeight.setText("Wgt: U");				
		} else if (vPetGui.vPetStomachMod>0.75 && vPetGui.vPetStomachMod<1.25) {vPetGui.lblWeight.setText("Wgt: N");				
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String logReader(String log) {
		

		String leftoverLog = log;
		
		if (leftoverLog.indexOf(",")>-1) {
			leftoverLog = log.substring(0, log.lastIndexOf(","));
		}

		ArrayList<String> cutLogs = new ArrayList<String>();
		

		ArrayList<String> pooLog = new ArrayList<String>();
		
		while (leftoverLog.indexOf(",")>-1) {
			int logBreak = leftoverLog.lastIndexOf(",");
			cutLogs.add(leftoverLog.substring(logBreak, leftoverLog.length()));
			leftoverLog = leftoverLog.substring(0, logBreak);

			//System.out.println("Log Cut, cutLogs:"+cutLogs.size());
		}
		

		for (int i=0; i<cutLogs.size(); i++ ) {
			
			String searchHere = cutLogs.get(i);
			
			if (searchHere.contains("Poo")) {
				pooLog.add(cutLogs.get(i));
			}
		}
		
		System.out.println("Poo Logs:"+pooLog.size());
		
		/*for (int i=0; i<cutLogs.size(); i++ ) {
			System.out.print(cutLogs.get(i));
		}*/
		
		for (int i=0; i<pooLog.size(); i++ ) {
			System.out.print(pooLog.get(i));
		}
		
		System.out.println(leftoverLog);
		System.out.println("CutLogs:"+cutLogs.size());
		
		return leftoverLog;
	}
}
