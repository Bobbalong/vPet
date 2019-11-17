package vPetSrc;
import java.text.DecimalFormat;
import java.util.*;


public class Tools {	

	static DecimalFormat df = new DecimalFormat("###.###");
	
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
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	public static double rnJesus(double prayer) {
		double judgement = Math.random() * prayer + 1;
		return judgement;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public static String ageStamp() {	
		vPet.millisAlive = System.currentTimeMillis() -  vPet.millisBorn;
		long millis = vPet.millisAlive % 1000;
		long second = (vPet.millisAlive / 1000) % 60;
		long minute = (vPet.millisAlive / (1000 * 60)) % 60;
		long hour = (vPet.millisAlive / (1000 * 60 * 60)) % 24;

		String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
		return time;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------
	public static void messages(String message) {												//This method provides msgs for a given situation
																								//	message()
		double RngMsg = Tools.rnJesus(100);
		
		switch (message) {
	//---------------------------------------------------------------------------------------------------------------------------
			case "prompt":																			//Hunger Tick (System Output)
				
	
				System.out.println(""+
								vPet.stomachGauge+":"+vPet.hygieneGuage+" ( "+
								"s:"+df.format(vPet.stomach)+
								" sM:"+df.format(vPet.stomachMod)+" Tol:"+df.format(vPet.stomachTol)+" sLoad: "+vPet.stomachLoad+" ) ( "+
								"Bread: "+Menu.bread+"/10 Bowl:"+Home.bowl[2]+" "+df.format(Menu.breadTick)+"/"+df.format(Menu.breadValue)+" ) ( "+
								"Poo:"+vPet.poos+" Bowel: "+vPet.stomachBowel+" ) ( "+
								"Hygiene:"+df.format(vPet.hygiene)+" Tol:"+df.format(vPet.hygieneTol)+" Dmg:"+df.format(petSim.dirtDmg)+" ) "+
								ageStamp()
								);
				System.out.println(""+
								"( E:"+df.format(vPet.energy)+" eMod:"+vPet.energyMod+" Tol:"+df.format(vPet.energyTol)+" ) "
								+GUI.frameSlide+"'"+imgCycle.vPetFrameSlideDir+"' "+
								
								" \" "+GUI.userInput+" \" "
								);
				//petSim.needsSleep | petSim.needsFood | petSim.needsToilet | petSim.bowlNeedsFilled
				System.out.println("( Turning:"+imgCycle.vPetTurn+" needsSleep:"+petSim.needsSleep+" needsFood:"+petSim.needsFood+" needsToilet:"+petSim.needsToilet+" bowlNeedsFilled:"+petSim.bowlNeedsFilled
						);
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "stomachCD":																		//Bread Cooldown (UI output and Log)
				System.out.println("stomachCD");			
				Logger.log("stomachCD");
				labeler(vPet.name+" belches crudely.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "breadEaten":																		//Eaten Bread (UI output and Log)
				System.out.println("breadEaten");
				Logger.log("Bread Eaten:"+Menu.bread+"/10 "+Math.round((vPet.stomach * 100.0) / 100.0)+" ");
				labeler(vPet.name+" enjoys some bread.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "bloated":																			//Bloated (UI output and Log)
				System.out.println("bloated");
				Logger.log("Bloated");
				labeler(vPet.name+" is too bloated to eat anymore.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "stomachCap":																		//Bread Limiter (UI output and Log)
				System.out.println("stomachCap");
				Logger.log("Stomach Cap");
				labeler(vPet.name+" has eaten too much right now.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------		
			case "noBread":																			//No Bread (UI output and Log)
				System.out.println("noBread");
				Logger.log("No Bread");
				labeler("There is no bread left");
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "Mess":																			//Pet Mess (UI output and Log)
				System.out.println("Poo'd");
				Logger.log("Poo'd");
				labeler(vPet.name+" made a mess.");
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "cleanPoo":																		//Clean Mess (UI output and Log)
				if (vPet.poos>0) {																		//Pet Mess (UI output and Log)
					System.out.println("Cleaned Poo");
					Logger.log("Cleaned Poo");
					labeler(vPet.name+" wil be happier with less mess");
				} 
				else if (vPet.poos<1) {																	//Pet Mess (UI output and Log)
					System.out.println("Cleaned Poo");
					Logger.log("Cleaned Poo");
					labeler(vPet.name+" wil be happier with no mess");
				}
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "breadCD":																			//breadCD
				System.out.println("breadCD");	
				Logger.log("BreadCD "+Menu.bread+"/10"); 
				labeler("A loaf cools down");
				break;
	//---------------------------------------------------------------------------------------------------------------------------	
			case "bowelMove":																		//bowelMove
				if (RngMsg>=90) {
					//labeler(vPet.name+" twitches.");
				} 
				else if (RngMsg<90) {
					//labeler(vPet.name+"'s stomach rumbles");
				}
				break;
	//---------------------------------------------------------------------------------------------------------------------------
			case "petCleaned":																			//breadCD
				System.out.println("petCleaned");	
				Logger.log("Pet Cleaned "+df.format(vPet.hygiene)); 
				labeler(vPet.name+ " is squeaky clean.");
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

//-------------------------------------------------------------------------------------------------------------------------------------------------
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
