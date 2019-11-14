package vPetSrc;

import javax.swing.ImageIcon;

public class imgCycle {
	
	public static int framerate = 100;
	static boolean vPetBlink = false, vPetTurn = false,	vPetSleep = false;
	public static int cleaningCycle = 0;
	static char vPetFrameSlideDir = 'R';
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------vPetAnim	
		public static void vPetAnim() {

			GUI.frame = 0;
			GUI.lblIconMidFrame = 0;
			GUI.frameSlide = 20;
			
			Thread vPetAnim = new Thread() {
				public void run() {
					try {
						
						while(true) {
							
							GUI.messCheck();
							
							if (!vPet.isActive) {
								System.out.println("intro loop");
								intro.introQuiz();
								} 
							else {
								
								
								if (petSim.asleep) {
									GUI.lblImageMain.setVisible(false);
									petSleep();
								}
								else {
																	
									if (Interaction.cleaning) {
										cleaning();
										}								
									if (GUI.stink==true) {
										stink();
										GUI.lblIconlower.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/slimePoo.png")));
										}
									if (vPet.stomachLoad>0 && vPet.species.equalsIgnoreCase(vPet.speciesList[1])) {
										hasBread();
									}
									
									
									if (petSim.needsSleep) {
										if ((GUI.frameSlide>=Home.bed[0]) && (GUI.frameSlide<=(Home.bed[1]*.75))) {
											petSim.needsSleep = false;
											petSim.asleep = true;
											GUI.frame = 0;
										} 
										else {
											petNeedsSleep();										
										}
									}
									else if (vPet.stomachBowel>49) {
										petNeedsToilet();
									}
									else if (Tools.rnJesus(100)>99 && !vPetBlink) {
										vPetBlink=true;GUI.frame = 0;
									}							
									if (vPetBlink) {
										petBlink();
									}
									else {									
										changeDir();
										petIdle();
										}						
								
								
																	
								GUI.lblBreadCount.setText(Menu.bread+" X ");

								}
							}
							sleep(framerate);
						
						
						}
					}
					catch (InterruptedException e) {
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
	
	public static void slimeIdle() {
		
		walk();
		
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
	
	public static void slimeBlink() {
		
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
			vPetBlink=false;
			GUI.frame=0;
			break;
		}
	}
	
	public static void stink() {

		switch(GUI.lblIconMidFrame) {
		case 0:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesOne.png")));
			GUI.lblIconMidFrame++;
			break;
		case 1:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesTwo.png")));
			GUI.lblIconMidFrame++;
			break;
		case 2:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesThree.png")));
			GUI.lblIconMidFrame++;
			break;
		case 3:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesFour.png")));
			GUI.lblIconMidFrame++;
			break;
		case 4:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesFive.png")));
			GUI.lblIconMidFrame++;
			break;
		case 5:
			GUI.lblIconMid.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/stink/standard/stinkLinesSix.png")));
			GUI.lblIconMidFrame=0;
			break;
		}
	}

	public static void snekIdle() {
		
		walk();
		
		framerate = 110;
		
		if (vPetFrameSlideDir == 'R') {
			switch(GUI.frame) {
			case 0:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleOne.png")));
				GUI.frame++;
				break;
			case 1:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleTwo.png")));
				GUI.frame++;
				break;
			case 2:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleThree.png")));
				GUI.frame++;
				break;
			case 3:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleFour.png")));
				GUI.frame++;
				break;
			case 4:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleFive.png")));
				GUI.frame++;
				break;
			case 5:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleSix.png")));
				GUI.frame++;
				break;
			case 6:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleSeven.png")));
				GUI.frame++;
				break;
			case 7:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/right/snekIdleEight.png")));
				GUI.frame=0;
				break;
			}
		} else if (vPetFrameSlideDir == 'L') {
			switch(GUI.frame) {
			case 0:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleOne.png")));
				GUI.frame++;
				break;
			case 1:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleTwo.png")));
				GUI.frame++;
				break;
			case 2:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleThree.png")));
				GUI.frame++;
				break;
			case 3:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleFour.png")));
				GUI.frame++;
				break;
			case 4:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleFive.png")));
				GUI.frame++;
				break;
			case 5:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleSix.png")));
				GUI.frame++;
				break;
			case 6:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleSeven.png")));
				GUI.frame++;
				break;
			case 7:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/idle/left/snekIdleEight.png")));
				GUI.frame=0;
				break;
			}
		}
	}

	public static void cleaning() {
		
		GUI.lblImageOlay.setBounds(GUI.frameSlide, 128, 99, 71);

		switch(GUI.lblImageOlayFrame) {
		case 0:
			GUI.lblImageOlay.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Action/cleanOne.png")));
			GUI.lblImageOlayFrame++;
			break;
		case 1:
			GUI.lblImageOlay.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Action/cleanTwo.png")));
			GUI.lblImageOlayFrame++;
			break;
		case 2:
			GUI.lblImageOlay.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Action/cleanThree.png")));
			GUI.lblImageOlayFrame++;
			break;
		case 3:
			GUI.lblImageOlay.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Action/cleanFour.png")));
			GUI.lblImageOlayFrame = 0;
			if (cleaningCycle<3) {
				cleaningCycle++;
				}
			else if (cleaningCycle>2) {
				Interaction.cleaning = false;
				cleaningCycle = 0;
				GUI.lblImageOlay.setIcon(null);
				//GUI.frmGUI.remove(GUI.lblImageOlayFrame);
				}
			break;
		}		
	}

	public static void changeDir() {
		if (!vPetBlink && !vPetTurn && !petSim.needsToilet && !petSim.needsSleep) {
		if (Tools.rnJesus(100)>99) {
			if (vPetFrameSlideDir == 'R') {
				if (GUI.frameSlide>250) {
					vPetFrameSlideDir = 'L';
					vPetTurn = true;
				}
			} else {
				vPetFrameSlideDir = 'R';
				vPetTurn = true;
			}
		}
		}
	}
	
	public static void walk() {
	
		if (!vPetBlink && !vPetTurn) {
		
			if (vPetFrameSlideDir == 'R') {
				if (GUI.frameSlide<501) {
					GUI.frameSlide++;
				}
				else {
					vPetFrameSlideDir = 'L';
				}
			} else if (vPetFrameSlideDir == 'L') {
				if (GUI.frameSlide>19) {
					GUI.frameSlide--;
				}
				else {
					vPetFrameSlideDir = 'R';
				}
			}
		}

		GUI.lblImageMain.setBounds(GUI.frameSlide, 128, 99, 71);
		
	}
	
	public static void petIdle() {
		if (!vPetTurn) {
		if (vPet.species.equalsIgnoreCase(vPet.speciesList[1])) {
			slimeIdle();
	    	}
	    else if (vPet.species.equalsIgnoreCase(vPet.speciesList[0])) {
	    	snekIdle();
	    	}
	    else {System.out.println("no spec");}
		}
		else {petTurn();}
	}
	
	public static void petNeedsToilet () {
		if (GUI.frameSlide>Home.toilet[1]) {
			vPetFrameSlideDir = 'L';
			GUI.frameSlide--;
		}
		else if (GUI.frameSlide<Home.toilet[0]) {
			vPetFrameSlideDir = 'R';
			GUI.frameSlide++;
		}
	}
	
	public static void snekBlink() {
		
		framerate = 150;
		
		if (vPetFrameSlideDir == 'L') {
			switch(GUI.frame) {
			case 0:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkOne.png")));
				GUI.frame++;
				break;
			case 1:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkTwo.png")));
				GUI.frame++;
				break;
			case 2:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkThree.png")));
				GUI.frame++;
				break;
			case 3:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkFour.png")));
				GUI.frame++;
				break;
			case 4:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkFive.png")));
				GUI.frame++;
				break;
			case 5:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkSix.png")));
				GUI.frame++;
				break;
			case 6:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/left/snekBlinkSeven.png")));
				vPetBlink=false;
				GUI.frame=0;
				break;
			}
		}
		else if (vPetFrameSlideDir == 'R') {
			switch(GUI.frame) {
			case 0:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkOne.png")));
				GUI.frame++;
				break;
			case 1:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkTwo.png")));
				GUI.frame++;
				break;
			case 2:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkThree.png")));
				GUI.frame++;
				break;
			case 3:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkFour.png")));
				GUI.frame++;
				break;
			case 4:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkFive.png")));
				GUI.frame++;
				break;
			case 5:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkSix.png")));
				GUI.frame++;
				break;
			case 6:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/blink/right/snekBlinkSeven.png")));
				vPetBlink=false;
				GUI.frame=0;
				break;
			}
		}
	}

	public static void petBlink() {
		if (vPet.species.equalsIgnoreCase(vPet.speciesList[1])) {
			slimeBlink();
	    	}
	    else if (vPet.species.equalsIgnoreCase(vPet.speciesList[0])) {
	    	snekBlink();
	    	}
	}

	public static void snekTurn() {
		
		framerate = 75;
		
		if (vPetFrameSlideDir == 'L') {
			switch(GUI.frame) {
			case 0:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnOne.png")));
				GUI.frame++;
				break;
			case 1:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnTwo.png")));
				GUI.frame++;
				break;
			case 2:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnThree.png")));
				GUI.frame++;
				break;
			case 3:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnFour.png")));
				GUI.frame++;
				break;
			case 4:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnFive.png")));
				GUI.frame++;
				break;
			case 5:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnSix.png")));
				GUI.frame++;
				break;
			case 6:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnSeven.png")));
				GUI.frame++;
				break;
			case 7:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnEight.png")));
				GUI.frame++;
				break;
			case 8:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/right/snekTurnNine.png")));
				GUI.frame=0;
				vPetTurn = false;
				break;
			}
		} else if (vPetFrameSlideDir == 'R') {
			switch(GUI.frame) {
			case 0:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnOne.png")));
				GUI.frame++;
				break;
			case 1:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnTwo.png")));
				GUI.frame++;
				break;
			case 2:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnThree.png")));
				GUI.frame++;
				break;
			case 3:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnFour.png")));
				GUI.frame++;
				break;
			case 4:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnFive.png")));
				GUI.frame++;
				break;
			case 5:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnSix.png")));
				GUI.frame++;
				break;
			case 6:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnSeven.png")));
				GUI.frame++;
				break;
			case 7:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnEight.png")));
				GUI.frame++;
				break;
			case 8:
				GUI.lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/turn/left/snekTurnNine.png")));
				GUI.frame=0;
				vPetTurn = false;
				break;
			}
		}
	}

	public static void petTurn() {
		
		if (vPetTurn) {

		if (vPet.species.equalsIgnoreCase(vPet.speciesList[1])) {
			System.out.println("Turn Sequence");
	    	vPetTurn = false;
	    	}
	    else if (vPet.species.equalsIgnoreCase(vPet.speciesList[0])) {
	    	snekTurn();
	    	}
		}
	}

	public static void petSleep() {

		if (vPet.species.equalsIgnoreCase(vPet.speciesList[1])) {
			System.out.println("Sleep Sequence");
	    	vPetSleep = false;
	    	}
	    else if (vPet.species.equalsIgnoreCase(vPet.speciesList[0])) {
	    	snekSleep();
	    	}
	}
	
	public static void snekSleep() {
		
		framerate = 500;
		
		switch(GUI.frame) {
		case 0:
			GUI.lblBed.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/sleeping/snekSleepOne.png")));
			GUI.frame++;
			break;
		case 1:
			GUI.lblBed.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Snek/sleeping/snekSleepTwo.png")));
			GUI.frame=0;
			break;
		}
	}

	public static void petNeedsSleep () {
		
		if (GUI.frameSlide>Home.bed[1]) {
			vPetFrameSlideDir = 'L';
			GUI.frameSlide--;
		}
		else if (GUI.frameSlide<Home.bed[0]) {
			vPetFrameSlideDir = 'R';
			GUI.frameSlide++;
		}
	}



}
