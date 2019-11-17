package vPetSrc;

public class Menu {
	
	public static int bread;
	public static double breadTick;
	public static double breadValue = 10 * vPet.stomachMod;
    
	
	
	public static void breadCheck() {

		breadValue = 10 * vPet.stomachMod;
		if (bread==10) {			
			breadTick=0;
			}
		if (bread<10 && breadTick >= breadValue) {			
			bread++;
			breadTick -= (breadValue) * 1.2;
			Tools.messages("breadCD");
			}
		else {
			breadTick += vPet.stomachMod;
		}
	}

}
