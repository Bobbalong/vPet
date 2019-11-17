package vPetSrc;

import javax.swing.ImageIcon;

public class Interaction {
	public static boolean cleaning = false;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void feed(String food) {													//feed() This method is used when the pet is given food
		
			switch (food) {																	//Food Choice
			
			case "Bread":																	//Bread				
				if (Home.bowl[2]<1 && Menu.bread > 0) {					
					GUI.lblBowl.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Environment/bowl/bowlFull.png")));				
					Menu.bread--;															//Bread Stock decreases
					Home.bowl[2] += 3;
					GUI.lblBreadCount.setText(Menu.bread+" X ");
				} 
				else {
					Tools.messages("noBread");
					}				
					break;
				case "Cake":																//Bread
			
					break;
				}																				
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void clean() {															//Clean() used to remove unhygienic things from pet
		
		if (vPet.hygiene<100) {																//Check if pet is dirty
			Tools.messages("petCleaned");													//msgs
			vPet.hygiene = 100;																//Cleans pet
			cleaning = true;																//Queue cleaning animation
			}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
		public static void scoop() {														//scoop() used to remove unhygienic things from environment
			
			if (vPet.poos>0) {																//Check for Poo
				vPet.poos--;																//Poo stack decreases
				Tools.messages("cleanPoo");													//msg
				}
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
}
