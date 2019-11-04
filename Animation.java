package vPetRes;

import javax.swing.ImageIcon;

public class Animation {
	
	public static int framerate;
	
	public static void hasBread() {
		
		framerate = 150;
		
		switch(vPetGui.vPetFrame) {
		case 0:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/eatingBread/slimeBreadOne.png")));
			vPetGui.vPetFrame++;
			break;
		case 1:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/eatingBread/slimeBreadTwo.png")));
			vPetGui.vPetFrame++;
			break;
		case 2:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/eatingBread/slimeBreadThree.png")));
			vPetGui.vPetFrame++;
			break;
		case 3:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/eatingBread/slimeBreadFour.png")));
			vPetGui.vPetFrame++;
			break;
		case 4:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/eatingBread/slimeBreadSix.png")));
			vPetGui.vPetFrame++;
			break;
		case 5:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/eatingBread/slimeBreadFive.png")));
			vPetGui.vPetFrame=0;
			break;
		}
	}
	
	public static void idle() {
		
		framerate = 100;

		switch(vPetGui.vPetFrame) {
		case 0:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/idle/slimeIdleOne.png")));
			vPetGui.vPetFrame++;
			break;
		case 1:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/idle/slimeIdleTwo.png")));
			vPetGui.vPetFrame++;
			break;
		case 2:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/idle/slimeIdleThree.png")));
			vPetGui.vPetFrame++;
			break;
		case 3:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/idle/slimeIdleFour.png")));
			vPetGui.vPetFrame++;
			break;
		case 4:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/idle/slimeIdleFive.png")));
			vPetGui.vPetFrame++;
			break;
		case 5:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/idle/slimeIdleSix.png")));
			vPetGui.vPetFrame=0;
			break;
		}
	}
	
	public static void blink() {
		
		framerate = 150;
		
		switch(vPetGui.vPetFrame) {
		case 0:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/blink/slimeBlinkOne.png")));
			vPetGui.vPetFrame++;
			break;
		case 1:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/blink/slimeBlinkTwo.png")));
			vPetGui.vPetFrame++;
			break;
		case 2:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/blink/slimeBlinkThree.png")));
			vPetGui.vPetFrame++;
			break;
		case 3:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/blink/slimeBlinkTwo.png")));
			vPetGui.vPetFrame++;
			break;
		case 4:
			vPetGui.lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/blink/slimeBlinkOne.png")));
			vPetGui.vPetFrame=0;
			vPetGui.vPetBlink=false;
			break;
		}
	}
	
	public static void stink() {

		switch(vPetGui.vPetFrame) {
		case 0:
			vPetGui.lblIconMid.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/stink/standard/stinkLinesOne.png")));
			//vPetGui.vPetFrame++;
			break;
		case 1:
			vPetGui.lblIconMid.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/stink/standard/stinkLinesTwo.png")));
			//vPetGui.vPetFrame++;
			break;
		case 2:
			vPetGui.lblIconMid.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/stink/standard/stinkLinesThree.png")));
			//vPetGui.vPetFrame++;
			break;
		case 3:
			vPetGui.lblIconMid.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/stink/standard/stinkLinesFour.png")));
			//vPetGui.vPetFrame++;
			break;
		case 4:
			vPetGui.lblIconMid.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/stink/standard/stinkLinesFive.png")));
			//vPetGui.vPetFrame++;
			break;
		case 5:
			vPetGui.lblIconMid.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/stink/standard/stinkLinesSix.png")));
			//vPetGui.vPetFrame=0;
			break;
		}
	}
}