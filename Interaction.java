package vPetSrc;

public class Interaction {
	public static boolean cleaning = false;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void feed(String food) {													//feed() This method is used when the pet is given food
		
			switch (food) {																	//Food Choice
			
			case "Bread":																	//Bread
				
				if (vPet.stomachLoad == vPet.stomachCap) {									//Check if stomach available 
					Tools.messages("stomachCap");											//msg
					}
				else if (vPet.stomachLoad < vPet.stomachCap 
						&& vPet.stomach < 99 
						&& Menu.bread < 11 
						&& Menu.bread >0 ) {												//Checks bread stock and stomach cooldown
					
					vPet.stomach += Menu.breadValue;									//Stomach contents increase
					vPet.stomach = Math.max(0, Math.min(100, vPet.stomach));				//Constrains stomach (0-100)
					Menu.bread--;															//Bread Stock decreases
					vPet.stomachLoad++;														//Stomach cooldown increase
					Tools.messages("breadEaten");											//Msg
					} 
				else if (vPet.stomachLoad < vPet.stomachCap 
						&& vPet.stomach > 99) {												//Check if stocmach full
					Tools.messages("bloated");												//Msg
					} 
				else if (Menu.bread == 0) {													//Check Loaf stock
					Tools.messages("noBread");
					}
				GUI.lblBreadCount.setText(Menu.bread+" X ");
				
					break;
				case "Cake":																//Bread
			
					break;
				}																				
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void clean() {															//Clean() used to remove unhygienic things from environment or pet
		
		if (vPet.hygiene<100) {																//Check for Poo
			vPet.hygiene = 100;																	//Poo stack decreases
			Tools.messages("cleanPet");														//msg
			cleaning = true;
			}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
		public static void scoop() {															//Clean() used to remove unhygienic things from environment or pet
			
			if (vPet.poos>0) {																	//Check for Poo
				vPet.poos--;																	//Poo stack decreases
				Tools.messages("cleanPoo");														//msg
				}
		}
}
