package vPetSrc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {

	static ArrayList<String> cutLogs = new ArrayList<String>();								//Initial pass of log string puts each element in a single array
	
	static ArrayList<String> pooLog = new ArrayList<String>();									//The Cut logs are searched for and added to their own array
	static ArrayList<String> stomachCDLog = new ArrayList<String>();							//
	static ArrayList<String> stomachCapLog = new ArrayList<String>();							//	
	static ArrayList<String> starvedLog = new ArrayList<String>();								//
	static ArrayList<String> stuffedLog = new ArrayList<String>();								//
	static ArrayList<String> breadEatenLog = new ArrayList<String>();							//
	static ArrayList<String> cleanedPooLog = new ArrayList<String>();							//
	static ArrayList<String> cleanedPetLog = new ArrayList<String>();							//
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	public static void log(String args) {
	
		Saving.eventLog += args+" "+Tools.ageStamp()+", ";
	
	}


//-------------------------------------------------------------------------------------------------------------------------------------------------
	public static void logReader(String log) {
	
		String leftoverLog = log;															//log remainder, after extract

		if (leftoverLog.indexOf(",")>-1) {													//Each event in the log is divided by a ',', this checks thats in the leftoverLog
			leftoverLog = log.substring(0, log.lastIndexOf(","));								
		}
		
		while (leftoverLog.indexOf(",")>-1) {												//Checks for an entry in log string
			
			int logBreak = leftoverLog.lastIndexOf(",");									//Checks where the last entry starts
			
			cutLogs.add(leftoverLog.substring(logBreak, leftoverLog.length()));				//Adds entry to array
			
			leftoverLog = leftoverLog.substring(0, logBreak);								//Stores leftoverLog
		}
//	------------------------------------------------------------------------------------------------------------------//    Poo'd
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array
			
			String searchHere = cutLogs.get(i);												//entry to be searched
			
			if (searchHere.contains("Poo'd")) {												//check if log relevant
				pooLog.add(cutLogs.get(i));													//Stores entry in correct array
			}
		}				
		for (int i=0; i<pooLog.size(); i++ ) {												//Loop new array
//	System.out.print(pooLog.get(i));														//Print new array 
		}
		System.out.println(" - Poo Logs:"+pooLog.size());									//Print new array size
//	------------------------------------------------------------------------------------------------------------------//    stomachCD
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
			
			String searchHere = cutLogs.get(i);												//entry to be searched
			
			if (searchHere.contains("stomachCD")) {											//check if log relevant
				stomachCDLog.add(cutLogs.get(i));											//Stores entry in correct array
			}
		}				
		for (int i=0; i<stomachCDLog.size(); i++ ) {										//Loop new array
//	System.out.print(stomachCDLog.get(i));													//print new array 
		}
		System.out.println(" - stomachCD Logs:"+stomachCDLog.size());						//Display new array size
//	------------------------------------------------------------------------------------------------------------------//    Bread Eaten
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
			
			String searchHere = cutLogs.get(i);												//entry to be searched
			
			if (searchHere.contains("Bread Eaten")) {										//check if log relevant
				breadEatenLog.add(cutLogs.get(i));											//Stores entry in correct array
			}
		}				
		for (int i=0; i<breadEatenLog.size(); i++ ) {										//Loop new array
//	System.out.print(breadEatenLog.get(i));													//Print new array 
		}
		System.out.println(" - Bread Eaten Logs:"+breadEatenLog.size());					//Print new array size
//	------------------------------------------------------------------------------------------------------------------//    Cleaned Poo
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
			
			String searchHere = cutLogs.get(i);												//entry to be searched
			
			if (searchHere.contains("Cleaned Poo")) {										//check if log relevant
				cleanedPooLog.add(cutLogs.get(i));											//Stores entry in correct array
			}
		}				
		for (int i=0; i<cleanedPooLog.size(); i++ ) {										//Loop new array
//	System.out.print(cleanedPooLog.get(i));													//print new array 
		}
		System.out.println(" - Cleaned Poo Logs:"+cleanedPooLog.size());					//Display new array size
//	------------------------------------------------------------------------------------------------------------------//    Stuffed
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
			
			String searchHere = cutLogs.get(i);												//entry to be searched
			
			if (searchHere.contains("Stuffed")) {											//check if log relevant
				stuffedLog.add(cutLogs.get(i));												//Stores entry in correct array
			}
		}				
		for (int i=0; i<stuffedLog.size(); i++ ) {											//Loop new array
//	System.out.print(stuffedLog.get(i));													//print new array 
		}
		System.out.println(" - Stuffed Logs:"+stuffedLog.size());							//Display new array size		
//	------------------------------------------------------------------------------------------------------------------//    Starved
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
			
			String searchHere = cutLogs.get(i);												//entry to be searched
			
			if (searchHere.contains("Starved")) {											//check if log relevant
				starvedLog.add(cutLogs.get(i));												//Stores entry in correct array
			}
		}				
			for (int i=0; i<starvedLog.size(); i++ ) {										//Loop new array
//	System.out.print(starvedLog.get(i));													//print new array 
			}
		System.out.println(" - Starved Logs:"+starvedLog.size());							//Display new array size		
//		------------------------------------------------------------------------------------------------------------------//    Stomach Cap
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
	
			String searchHere = cutLogs.get(i);												//entry to be searched
		
			if (searchHere.contains("Stomach Cap")) {										//check if log relevant
				stomachCapLog.add(cutLogs.get(i));											//Stores entry in correct array
			}
		}				
		for (int i=0; i<stomachCapLog.size(); i++ ) {										//Loop new array
//	System.out.print(stomachCapLog.get(i));													//print new array 
		}
		System.out.println(" - Stomach Cap Logs:"+stomachCapLog.size());					//Display new array size		
//		------------------------------------------------------------------------------------------------------------------//    Pet Cleaned
		for (int i=0; i<cutLogs.size(); i++ ) {												//Loop through log array	
	
			String searchHere = cutLogs.get(i);												//entry to be searched
		
			if (searchHere.contains("Pet Cleaned")) {										//check if log relevant
				cleanedPetLog.add(cutLogs.get(i));											//Stores entry in correct array
			}
		}				
		for (int i=0; i<cleanedPetLog.size(); i++ ) {										//Loop new array
//			System.out.print(cleanedPetLog.get(i));											//print new array 
		}
		System.out.println(" - Pet Cleaned Logs:"+cleanedPetLog.size());					//Display new array size		
//	-----------------------------------------------------------------------------------------------------------------------------
		System.out.println(leftoverLog);
		System.out.println("CutLogs:"+cutLogs.size());
		
//	-----------------------------------------------------------------------------------------------------------------------------
		int maxLogSize = Math.max(0, Math.max(pooLog.size(), Math.max(stomachCDLog.size(), Math.max(stomachCapLog.size(), Math.max(starvedLog.size(), Math.max(stuffedLog.size(), Math.max(breadEatenLog.size(), cleanedPooLog.size())))))));
		System.out.println("chk:"+maxLogSize);
		
		logBuffChk();
		
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
		if (maxLogSize>cleanedPetLog.size()) {
			int dif = maxLogSize-cleanedPetLog.size();
			while (dif>0) {
				cleanedPetLog.add("              -");
				dif--;
			}
		}
		
		for (int i = 0; i<maxLogSize; i++) {
			if (i==0) {
				System.out.println(String.format("%5s %1s %23s %1s %15s %1s %15s %1s %18s %1s %18s %1s %15s %1s %15s %1s %20s", 
						"#", "|","Bread Eaten", "|", "stomachCD", "|", "Stomach Cap", "|", "Stuffed", "|", "Starved ", "|", "Poo'd ", "|", "Cleaned Poo", "|", "Pet Cleaned"));
				for (int l = 0; l < 167 ; l++) {System.out.print("-");}System.out.println("-");
			}
			System.out.println(String.format("%5s %1s %23s %1s %15s %1s %15s %1s %18s %1s %18s %1s %15s %1s %15s %1s %20s", 
					i+1, "|",breadEatenLog.get(i).substring(14), "|", stomachCDLog.get(i).substring(12), "|", stomachCapLog.get(i).substring(14), "|", stuffedLog.get(i).substring(10), "|", starvedLog.get(i).substring(10), "|", pooLog.get(i).substring(8), "|", cleanedPooLog.get(i).substring(14), "|", cleanedPetLog.get(i).substring(14)));			
		}
	}
	
	static void logBuffChk() {
		
		for (int i=0; i<cleanedPetLog.size(); i++ ) {								//Loop through log array			
			String searchHere = cleanedPetLog.get(i);								//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				cleanedPetLog.remove(i);											//Stores entry in correct array
				i=0;
			}
		}
		
		for (int i=0; i<cleanedPooLog.size(); i++ ) {								//Loop through log array			
			String searchHere = cleanedPooLog.get(i);								//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				cleanedPooLog.remove(i);											//Stores entry in correct array
				i=0;
			}
		}
	
		for (int i=0; i<pooLog.size(); i++ ) {										//Loop through log array			
			String searchHere = pooLog.get(i);										//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				pooLog.remove(i);													//Stores entry in correct array
				i=0;
			}
		}
		
		for (int i=0; i<starvedLog.size(); i++ ) {									//Loop through log array			
			String searchHere = starvedLog.get(i);									//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				starvedLog.remove(i);												//Stores entry in correct array
				i=0;
			}
		}
		
		for (int i=0; i<stuffedLog.size(); i++ ) {									//Loop through log array			
			String searchHere = stuffedLog.get(i);									//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				stuffedLog.remove(i);												//Stores entry in correct array
				i=0;
			}
		}
		
		for (int i=0; i<stomachCapLog.size(); i++ ) {								//Loop through log array			
			String searchHere = stomachCapLog.get(i);								//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				stomachCapLog.remove(i);											//Stores entry in correct array
				i=0;
			}
		}
		
		for (int i=0; i<stomachCDLog.size(); i++ ) {								//Loop through log array			
			String searchHere = stomachCDLog.get(i);								//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				stomachCDLog.remove(i);												//Stores entry in correct array
				i=0;
			}
		}
		
		for (int i=0; i<breadEatenLog.size(); i++ ) {								//Loop through log array			
			String searchHere = breadEatenLog.get(i);								//entry to be searched		
			if (searchHere.contains("-")) {											//check if log relevant
				breadEatenLog.remove(i);											//Stores entry in correct array
				i=0;
			}
		}
	}	

}	
