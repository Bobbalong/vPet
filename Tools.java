package vPetSrc;
import java.util.*;


public class Tools {
	
	public static String[] currentCalendar = {"0", "0"};
	public static long currentMillisTag;
	
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
								

		currentMillisTag = System.currentTimeMillis();							//Set Current Millis
	}	
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static double rnJesus(double prayer) {
		double judgement = Math.random() * prayer + 1;
		return judgement;
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void log(String args) {
    	
		Saving.eventLog += args+ageStamp(vPet.millisBorn)+", ";
		
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static String ageStamp(long millis) {												/**/
		int seconds = (int) (millis / 1000) % 60 ;
		int minutes = (int) ((millis / (1000*60)) % 60);
		int hours   = (int) ((millis / (1000*60*60)) % 24);
		String timestamp = "("+hours+":"+minutes+":"+seconds+")";
		return timestamp;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void messages(String message) {																		//This method provides msgs for a given situation
																														//	message()
		
		switch (message) {
		
		case "stomachCD":																							//Bread Cooldown (UI output and Log)
			System.out.println(vPet.name+" bubbles. (messages)");
			log("Bread Digested");
			break;
			
		case "breadEaten":																								//Eaten Bread (UI output and Log)
			System.out.println(vPet.name+" enjoyed some bread. (messages)");
			log("Bread Eaten:"+Menu.bread+"/10 "+Math.round((vPet.stomach * 100.0) / 100.0));
			break;
			
		case "bloated":																									//Bloated (UI output and Log)
			System.out.println(vPet.name+" is over filled and bloated. (messages)");
			log("Bloated");
			break;
			
		case "stomachCap":																								//Bread Limiter (UI output and Log)
			System.out.println(vPet.name+" has eaten too much bread recently... (messages)");
			log("Stomach Cap");
			break;
			
		case "noBread":																									//No Bread (UI output and Log)
			System.out.println("No more bread left! (messages)");
			log("No Bread");
			break;
			
		case "Mess":																									//Pet Mess (UI output and Log)
			System.out.println(vPet.name+" made a mess... (messages)");
			log("Poo'd");
			break;
			
		case "cleanPoo":																								//Clean Mess (UI output and Log)
		if (vPet.poos>0) {
			log("Cleaned Poo");} 
		else if (vPet.poos<1) {
			log("Cleaned Poo");}
			break;
			
		case "prompt":																									//Hunger Tick (System Output)
			System.out.println("( Stomach: "+vPet.stomach+" stomachMod: "+vPet.stomachMod+" sLoad: "+vPet.stomachLoad+" ) ( Bread: "+Menu.bread+"/10."+Menu.breadTick+" )");		
			break;
			
		case "msg":																									//
			if (Tools.rnJesus(100)>=50) {
				System.out.println(vPet.name+" makes a sighing noise. (messages)");
			} else if (Tools.rnJesus(100)<50) {
				System.out.println(vPet.name+" squelches around. (messages)");
				}
			break;
		
		case "breadCD":	
			System.out.println("A Loaf cooled down... mmm (messages)");	
			log("BreadCD "+Menu.bread+"/10"); 
			break;
		}
	}
}
