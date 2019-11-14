package vPetSrc;
import java.text.DecimalFormat;
import java.util.*;


public class Tools {
	
	public static String[] currentCalendar = {"0", "0"};
	public static long currentMillisTag;
	
	public static int labelerCycle = 0;
	public static String[] insightlist = {"","",""};
	public static void vPetTime() {					
				
		Calendar vPetCal = new GregorianCalendar();			
		int day = vPetCal.get(Calendar.DAY_OF_MONTH);		
		int month = vPetCal.get(Calendar.MONTH);			
		int year = vPetCal.get(Calendar.YEAR);				

		int second = vPetCal.get(Calendar.SECOND);			
		int minute = vPetCal.get(Calendar.MINUTE);			
		int hour = vPetCal.get(Calendar.HOUR);				
		
		currentCalendar[0] = ""+hour+":"+minute+":"+second;	
		currentCalendar[1] = ""+day+":"+month+":"+year;			
								

		currentMillisTag = System.currentTimeMillis();											//Set Current Millis
	}	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static double rnJesus(double prayer) {
		double judgement = Math.random() * prayer + 1;
		return judgement;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void log(String args) {
    	
		Saving.eventLog += args+" "+ageStamp()+", ";
		
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String ageStamp() {	
		vPet.millisAlive = System.currentTimeMillis() -  vPet.millisBorn;
		long millis = vPet.millisAlive % 1000;
		long second = (vPet.millisAlive / 1000) % 60;
		long minute = (vPet.millisAlive / (1000 * 60)) % 60;
		long hour = (vPet.millisAlive / (1000 * 60 * 60)) % 24;

		String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
		return time;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void messages(String message) {												//This method provides msgs for a given situation
																								//	message()
		double RngMsg = Tools.rnJesus(100);
		
		switch (message) {
	//---------------------------------------------------------------------------------------------------------------------------
			case "prompt":																			//Hunger Tick (System Output)
				
				DecimalFormat df = new DecimalFormat("###.###");
				
				System.out.println(""+
								vPet.stomachGauge+":"+vPet.hygieneGuage+" ( "+
								"s:"+df.format(vPet.stomach)+
								" sM:"+df.format(vPet.stomachMod)+" Tol:"+df.format(vPet.stomachTol)+" sLoad: "+vPet.stomachLoad+" ) ( "+
								"Bread: "+Menu.bread+"/10 "+df.format(Menu.breadTick)+"/"+df.format(Menu.breadValue)+" ) ( "+
								"Poo:"+vPet.poos+" Bowel: "+vPet.stomachBowel+" ) ( "+
								"Hygiene:"+df.format(vPet.hygiene)+" Tol:"+df.format(vPet.hygieneTol)+" Dmg:"+df.format(petSim.dirtDmg)+" ) "+
								ageStamp()
								);
				System.out.println(""+
								"( E:"+df.format(vPet.energy)+" eMod:"+vPet.energyMod+" Tol:"+df.format(vPet.energyTol)+") "+GUI.frameSlide
						);
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "stomachCD":																		//Bread Cooldown (UI output and Log)
				System.out.println("stomachCD");			
				log("stomachCD");
				labeler(vPet.name+" belches crudely.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "breadEaten":																		//Eaten Bread (UI output and Log)
				System.out.println("breadEaten");
				log("Bread Eaten:"+Menu.bread+"/10 "+Math.round((vPet.stomach * 100.0) / 100.0)+" ");
				labeler(vPet.name+" enjoys some bread.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "bloated":																			//Bloated (UI output and Log)
				System.out.println("bloated");
				log("Bloated");
				labeler(vPet.name+" is too bloated to eat anymore.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "stomachCap":																		//Bread Limiter (UI output and Log)
				System.out.println("stomachCap");
				log("Stomach Cap");
				labeler(vPet.name+" has eaten too much right now.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------		
			case "noBread":																			//No Bread (UI output and Log)
				System.out.println("noBread");
				log("No Bread");
				labeler("There is no bread left");
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "Mess":																			//Pet Mess (UI output and Log)
				System.out.println("Poo'd");
				log("Poo'd");
				labeler(vPet.name+" made a mess.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "cleanPoo":																		//Clean Mess (UI output and Log)
				if (vPet.poos>0) {																		//Pet Mess (UI output and Log)
					System.out.println("Cleaned Poo");
					log("Cleaned Poo");
					labeler(vPet.name+" wil be happier with less mess");
				} 
				else if (vPet.poos<1) {																	//Pet Mess (UI output and Log)
					System.out.println("Cleaned Poo");
					log("Cleaned Poo");
					labeler(vPet.name+" wil be happier with no mess");
				}
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "breadCD":																			//breadCD
				System.out.println("breadCD");	
				log("BreadCD "+Menu.bread+"/10"); 
				labeler("A loaf cools down");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "bowelMove":																		//bowelMove
				if (RngMsg>=90) {
					//labeler(vPet.name+" twitches.");
				} 
				else if (RngMsg<90) {
					labeler(vPet.name+"'s stomach rumbles");
				}
				break;

			//---------------------------------------------------------------------------------------------------------------------------				
			default:																				//RngMsg
				if (RngMsg>=50) {
					labeler(vPet.name+" makes a sighing noise.");
				} 
				else if (RngMsg<=49) {
					labeler(vPet.name+" squelches around.");
				} 
				else if (RngMsg<1.5) {
					labeler(vPet.name+" lets out a squeeky fart.");
				}
				Tools.messages("prompt");
				break;
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void logReader(String log) {
		
			String leftoverLog = log;															//log remainder, after extract
		
		if (leftoverLog.indexOf(",")>-1) {														//Each event in the log is divided by a ',', this checks thats in the leftoverLog
			leftoverLog = log.substring(0, log.lastIndexOf(","));								
		}
			ArrayList<String> cutLogs = new ArrayList<String>();								//Initial pass of log string puts each element in a single array
			
			ArrayList<String> pooLog = new ArrayList<String>();									//The Cut logs are searched for and added to their own array
			ArrayList<String> stomachCDLog = new ArrayList<String>();							//
			ArrayList<String> stomachCapLog = new ArrayList<String>();							//	
			ArrayList<String> starvedLog = new ArrayList<String>();								//
			ArrayList<String> stuffedLog = new ArrayList<String>();								//
			ArrayList<String> breadEatenLog = new ArrayList<String>();							//
			ArrayList<String> cleanedPooLog = new ArrayList<String>();							//
		
		while (leftoverLog.indexOf(",")>-1) {													//Checks for an entry in log string
			
			int logBreak = leftoverLog.lastIndexOf(",");										//Checks where the last entry starts
			
			cutLogs.add(leftoverLog.substring(logBreak, leftoverLog.length()));					//Adds entry to array
			
			leftoverLog = leftoverLog.substring(0, logBreak);									//Stores leftoverLog
		}
		//------------------------------------------------------------------------------------------------------------------//    Poo'd
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array
			
			String searchHere = cutLogs.get(i);													//entry to be searched
			
			if (searchHere.contains("Poo'd")) {													//check if log relevant
				pooLog.add(cutLogs.get(i));														//Stores entry in correct array
			}
		}				
		for (int i=0; i<pooLog.size(); i++ ) {													//Loop new array
			//System.out.print(pooLog.get(i));													//Print new array 
		}
		System.out.println(" - Poo Logs:"+pooLog.size());										//Print new array size
		//------------------------------------------------------------------------------------------------------------------//    stomachCD
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array	
			
			String searchHere = cutLogs.get(i);													//entry to be searched
			
			if (searchHere.contains("stomachCD")) {												//check if log relevant
				stomachCDLog.add(cutLogs.get(i));												//Stores entry in correct array
			}
		}				
		for (int i=0; i<stomachCDLog.size(); i++ ) {											//Loop new array
			//System.out.print(stomachCDLog.get(i));												//print new array 
		}
		System.out.println(" - stomachCD Logs:"+stomachCDLog.size());									//Display new array size
		//------------------------------------------------------------------------------------------------------------------//    Bread Eaten
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array	
				
			String searchHere = cutLogs.get(i);													//entry to be searched
					
			if (searchHere.contains("Bread Eaten")) {											//check if log relevant
				breadEatenLog.add(cutLogs.get(i));												//Stores entry in correct array
			}
		}				
		for (int i=0; i<breadEatenLog.size(); i++ ) {											//Loop new array
			//System.out.print(breadEatenLog.get(i));											//Print new array 
		}
		System.out.println(" - Bread Eaten Logs:"+breadEatenLog.size());						//Print new array size
		//------------------------------------------------------------------------------------------------------------------//    Cleaned Poo
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array	
				
			String searchHere = cutLogs.get(i);													//entry to be searched
					
			if (searchHere.contains("Cleaned Poo")) {											//check if log relevant
				cleanedPooLog.add(cutLogs.get(i));												//Stores entry in correct array
			}
		}				
		for (int i=0; i<cleanedPooLog.size(); i++ ) {											//Loop new array
			//System.out.print(cleanedPooLog.get(i));												//print new array 
		}
		System.out.println(" - Cleaned Poo Logs:"+cleanedPooLog.size());						//Display new array size
		//------------------------------------------------------------------------------------------------------------------//    Stuffed
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array	
				
			String searchHere = cutLogs.get(i);													//entry to be searched
					
			if (searchHere.contains("Stuffed")) {												//check if log relevant
				stuffedLog.add(cutLogs.get(i));													//Stores entry in correct array
			}
		}				
		for (int i=0; i<stuffedLog.size(); i++ ) {												//Loop new array
			//System.out.print(stuffedLog.get(i));												//print new array 
		}
		System.out.println(" - Stuffed Logs:"+stuffedLog.size());								//Display new array size		
		//------------------------------------------------------------------------------------------------------------------//    Starved
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array	
				
			String searchHere = cutLogs.get(i);													//entry to be searched
					
			if (searchHere.contains("Starved")) {												//check if log relevant
				starvedLog.add(cutLogs.get(i));													//Stores entry in correct array
			}
		}				
		for (int i=0; i<starvedLog.size(); i++ ) {												//Loop new array
			//System.out.print(starvedLog.get(i));												//print new array 
		}
		System.out.println(" - Starved Logs:"+starvedLog.size());								//Display new array size		
		//------------------------------------------------------------------------------------------------------------------//    Stomach Cap
		for (int i=0; i<cutLogs.size(); i++ ) {													//Loop through log array	
				
			String searchHere = cutLogs.get(i);													//entry to be searched
					
			if (searchHere.contains("Stomach Cap")) {											//check if log relevant
				stomachCapLog.add(cutLogs.get(i));												//Stores entry in correct array
			}
		}				
		for (int i=0; i<stomachCapLog.size(); i++ ) {											//Loop new array
			//System.out.print(stomachCapLog.get(i));											//print new array 
		}
		System.out.println(" - Stomach Cap Logs:"+stomachCapLog.size());						//Display new array size		
		//-----------------------------------------------------------------------------------------------------------------------------
		System.out.println(leftoverLog);
		System.out.println("CutLogs:"+cutLogs.size());
		
		//-----------------------------------------------------------------------------------------------------------------------------
		int maxLogSize = Math.max(0, Math.max(pooLog.size(), Math.max(stomachCDLog.size(), Math.max(stomachCapLog.size(), Math.max(starvedLog.size(), Math.max(stuffedLog.size(), Math.max(breadEatenLog.size(), cleanedPooLog.size())))))));
		System.out.println("chk:"+maxLogSize);
		
		if (maxLogSize>breadEatenLog.size()) {
			int dif = maxLogSize-breadEatenLog.size();
			while (dif>0) {
				breadEatenLog.add("              -");
				dif--;
			}
		}
		if (maxLogSize>stomachCDLog.size()) {
			int dif = maxLogSize-stomachCDLog.size();
			while (dif>0) {
				stomachCDLog.add("            -");
				dif--;
			}
		}
		if (maxLogSize>stomachCapLog.size()) {
			int dif = maxLogSize-stomachCapLog.size();
			while (dif>0) {
				stomachCapLog.add("              -");
				dif--;
			}
		}
		if (maxLogSize>stuffedLog.size()) {
			int dif = maxLogSize-stuffedLog.size();
			while (dif>0) {
				stuffedLog.add("          -");
				dif--;
			}
		}
		if (maxLogSize>starvedLog.size()) {
			int dif = maxLogSize-starvedLog.size();
			while (dif>0) {
				starvedLog.add("          -");
				dif--;
			}
		}
		if (maxLogSize>pooLog.size()) {
			int dif = maxLogSize-pooLog.size();
			while (dif>0) {
				pooLog.add("        -");
				dif--;
			}
		}
		if (maxLogSize>cleanedPooLog.size()) {
			int dif = maxLogSize-cleanedPooLog.size();
			while (dif>0) {
				cleanedPooLog.add("              -");
				dif--;
			}
		}
		
		for (int i = 0; i<maxLogSize; i++) {
			if (i==0) {
				System.out.println(String.format("%5s %1s %23s %1s %15s %1s %15s %1s %18s %1s %18s %1s %15s %1s %15s", 
				"#", "|","Bread Eaten", "|", "stomachCD", "|", "Stomach Cap", "|", "Stuffed", "|", "Starved ", "|", "Poo'd ", "|", "Cleaned Poo"));
				for (int l = 0; l < 144 ; l++) {System.out.print("-");}System.out.println("-");
			}
			System.out.println(String.format("%5s %1s %23s %1s %15s %1s %15s %1s %18s %1s %18s %1s %15s %1s %15s", 
				i+1, "|",breadEatenLog.get(i).substring(14), "|", stomachCDLog.get(i).substring(12), "|", stomachCapLog.get(i).substring(14), "|", stuffedLog.get(i).substring(10), "|", starvedLog.get(i).substring(10), "|", pooLog.get(i).substring(8), "|", cleanedPooLog.get(i).substring(14)));			
		}
		
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void labeler(String insight) {
		
		if (labelerCycle==0) {
			insightlist[0] = insight;
			GUI.lblInsightOne.setText(insightlist[0]);
			labelerCycle++;
		}
		else if (labelerCycle==1) {
			insightlist[1] = insightlist[0];
			insightlist[0] = insight;
			GUI.lblInsightOne.setText(insightlist[0]);
			GUI.lblInsightTwo.setText(insightlist[1]);
			labelerCycle++;
		}
		else if (labelerCycle==3) {
			insightlist[2] = insightlist[1];
			insightlist[1] = insightlist[0];
			insightlist[0] = insight;
			GUI.lblInsightOne.setText(insightlist[0]);
			GUI.lblInsightTwo.setText(insightlist[1]);
			GUI.lblInsightThree.setText(insightlist[2]);
			labelerCycle++;			
		}
		else {
			for (int i = 2, x = 1 ; i > 0 ; i--, x--) {
				insightlist[i] = insightlist[x];
			}
			insightlist[0] = insight;
			GUI.lblInsightOne.setText(insightlist[0]);
			GUI.lblInsightTwo.setText(insightlist[1]);
			GUI.lblInsightThree.setText(insightlist[2]);
		}
	}
	
}
