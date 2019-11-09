package vPetSrc;

import javax.swing.ImageIcon;

public class imgCycle {
	
	public static int framerate;
	static boolean vPetBlink = false;
	static boolean vPetStink = false;
	static char vPetFrameSlideDir = 'R';
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------vPetAnim	
		public static void vPetAnim() {

			GUI.frame = 0;
			GUI.frameSlide = 20;
			
			Thread vPetAnim = new Thread() {
				public void run() {
					try {
						
						while(true) {
							
							/*if (!vPet.isActive) {
								GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/vPetRes/images/Slime/slimeEgg.png")));
							} else {
							
							if (vPetStink) {
								stink();
							}
							if (Tools.breadLimit>0) {
								hasBread();
							}
							
							else if (Tools.rnJesus(100)>97 && !vPetBlink) {
								vPetBlink=true;GUI.frame = 0;
							}
							
							else if (vPetBlink) {blink();}
							
							else {idle();}*/
							
							idle();
						
						GUI.lblImageMain.setBounds(GUI.frameSlide, 128, 62, 59);
						
						if (vPetFrameSlideDir == 'R') {
							if (GUI.frameSlide<36) {
								GUI.frameSlide++;}
							else {vPetFrameSlideDir = 'L';}
						} else if (vPetFrameSlideDir == 'L') {
							if (GUI.frameSlide>19) {
								GUI.frameSlide--;}
							 else {vPetFrameSlideDir = 'R';}
						}
						
						
						sleep(framerate);
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

			vPetAnim.start();
		}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------vPetGui	

	
	public static void hasBread() {
		
		framerate = 150;
		
		switch(GUI.frame) {
		case 0:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/eatingBread/slimeBreadOne.png")));
			GUI.frame++;
			break;
		case 1:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/eatingBread/slimeBreadTwo.png")));
			GUI.frame++;
			break;
		case 2:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/eatingBread/slimeBreadThree.png")));
			GUI.frame++;
			break;
		case 3:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/eatingBread/slimeBreadFour.png")));
			GUI.frame++;
			break;
		case 4:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/eatingBread/slimeBreadSix.png")));
			GUI.frame++;
			break;
		case 5:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/eatingBread/slimeBreadFive.png")));
			GUI.frame=0;
			break;
		}
	}
	
	public static void idle() {
		
		framerate = 100;

		switch(GUI.frame) {
		case 0:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/idle/slimeIdleOne.png")));
			GUI.frame++;
			break;
		case 1:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/idle/slimeIdleTwo.png")));
			GUI.frame++;
			break;
		case 2:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/idle/slimeIdleThree.png")));
			GUI.frame++;
			break;
		case 3:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/idle/slimeIdleFour.png")));
			GUI.frame++;
			break;
		case 4:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/idle/slimeIdleFive.png")));
			GUI.frame++;
			break;
		case 5:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/idle/slimeIdleSix.png")));
			GUI.frame=0;
			break;
		}
	}
	
	public static void blink() {
		
		framerate = 150;
		
		switch(GUI.frame) {
		case 0:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/blink/slimeBlinkOne.png")));
			GUI.frame++;
			break;
		case 1:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/blink/slimeBlinkTwo.png")));
			GUI.frame++;
			break;
		case 2:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/blink/slimeBlinkThree.png")));
			GUI.frame++;
			break;
		case 3:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/blink/slimeBlinkTwo.png")));
			GUI.frame++;
			break;
		case 4:
			GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/blink/slimeBlinkOne.png")));
			GUI.frame=0;
			GUI.vPetBlink=false;
			break;
		}
	}
	
	/*public static void stink() {

		switch(GUI.vPetFrame) {
		case 0:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesOne.png")));
			//vPetGui.vPetFrame++;
			break;
		case 1:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesTwo.png")));
			//vPetGui.vPetFrame++;
			break;
		case 2:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesThree.png")));
			//vPetGui.vPetFrame++;
			break;
		case 3:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesFour.png")));
			//vPetGui.vPetFrame++;
			break;
		case 4:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesFive.png")));
			//vPetGui.vPetFrame++;
			break;
		case 5:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesSix.png")));
			//vPetGui.vPetFrame=0;
			break;
		}
	}*/
}
