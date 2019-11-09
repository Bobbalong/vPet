package vPetSrc;

import java.util.*;

public class Menu {
	
	public static int bread = 10;
	public static double breadTick;
	public static double breadValue;
    
	public static void breadCheck() {

		breadValue = 10 * vPet.stomachMod;
		if (bread==10) {			
			breadTick=0;
			}
		if (bread<10 && breadTick >= breadValue) {			
			bread++;
			breadTick -= breadValue;
			Tools.messages("breadCD");
			}
		else {
			breadTick += vPet.stomachMod;
		}
	}

}
