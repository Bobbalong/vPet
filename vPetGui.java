package vPetRes;

import javax.swing.*;
import java.util.*;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class vPetGui {
	
	int clockTick = 0;
	long clockCycles;
	
	static String fieldOneLive;
	static String FieldOneSelection;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static String ownerName = "";		
	static String vPetName;
	static boolean vPetActive;
	static String vPetDOBt;
	static String vPetDOBd;
	static long vPetMillisBorn;
	static double vPetStomach;
	static double vPetStomachMod;
	static String[] vPetSaveStates = {"Active", "Pet Name", "PetMillisBorn", "DOB time", "DOB", "Stomach", "Stomach Mod", "Owner Name", "Loaves", "Bread Tick", "Bread Limit", "Poop", "eventLog"};
	static ArrayList<String> vPetValue = new ArrayList<String>();
	static String vPetStomachGauge;
	static long vPetMillisAlive;
	public static int vPetPoop;
	static int vPetPood = 0;
	static double vPetCleanliness;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static long currentMillisTag;
	static String nowTime;
	static int vPetFrame;
	static int vPetFrameSlide;
	static char vPetFrameSlideDir = 'R';
	static double vPetRng = Tools.rnJesus(100);
	static boolean vPetBlink = false;
	static boolean vPetStink = false;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static JFrame frmVpet;
	public static  JLabel lblImagemain;
	private JLabel lblClock;
	public static JLabel lblLivedata;
	public static JLabel lblvPetInsight;
	private JTextField textFieldOne;
	public static JLabel lblH;
	public static JLabel lblbreadCount;
	public static JLabel lblAge;
	public static JLabel lblDob;
	public static JLabel lblWeight;
	public static JLabel lblIconlower;
	public static JButton btnClean;
	public static JLabel lblPetName;
	public static JLabel lblIconMid;
	public static JLabel lblBanner;
	public static JButton btnSave;
	public static JButton btnFeed;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------main
	public static void main(String[] args) {		
		
		System.out.println("Main/top");

		for(int i=0;i<vPetSaveStates.length;i++) {vPetValue.add("zero");};
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPetGui window = new vPetGui();
					window.frmVpet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("Main/end");
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------clock
	public void clock() {

		Thread clock = new Thread() {				//This thread is used to cycle the pet and event variables at a regular interval (1000ms)
			public void run() {
				try {
					
					while(true) {
						
					vPetEvents.intro();														//Temp jank, triggers intro
					
					if (clockTick<240) {													//This is used to slow  down certain pet calculations
						clockTick++;														//Altered interval (15s)
						} else if (clockTick==240){	
							vPetEvents.dirtTick();
							vPetEvents.hungerTick();										//Hunger Tick
							vPetRng = Math.round((Tools.rnJesus(100) * 100.0) / 100.0);		//Set pet random source
							clockCycles++;													//Count cycles (1cyc = ~1min)
							clockTick = 0;						
							}
					
					currentMillisTag = System.currentTimeMillis();							//Set Current Millis
					double stomachStamp = Math.round(vPetStomach * 100.0) / 100.0;			//Get precise stomach content
					
					Tools.vPetStomachGaugeChk();
					vPetGui.lblH.setText("Hunger: "+vPetStomachGauge);											//Set UI Stomach view to a verbal rep'
					vPetMillisAlive = currentMillisTag - vPetGui.vPetMillisBorn;			//Set pets current age (Millis)
					
					lblClock.setText("("+currentMillisTag+")");		
					
					lblbreadCount.setText("x"+Tools.loaves);
					lblAge.setText("Age: "+Tools.vPetAgeStamp());
					
					if (vPetStomachMod<0.75) {	lblWeight.setText("Wgt: D");					
					} else if (vPetStomachMod>1.25) {lblWeight.setText("Wgt: U");				
					} else if (vPetStomachMod>0.75 && vPetStomachMod<1.25) {lblWeight.setText("Wgt: N");				
					}
					
					Tools.toiletCheck();
					Tools.breadCheck();												//Update UI... clock, then pet insight string, then live data.
					
					lblLivedata.setText("s: "+stomachStamp+" sM: "+Math.round(vPetStomachMod * 100.0) / 100.0+"  pRng: "+Math.round(vPetRng * 100.0) / 100.0+"  ( cC: "+clockCycles+":"+clockTick+" )  ( "+Math.round(Tools.breadTick * 100.0) / 100.0+" / "+Math.round(Tools.breadValue * 100.0) / 100.0+" / "+Tools.breadLimit+" )  pP: "+vPetPoop);								
					
					vPetEvents.eventCalc(vPetMillisAlive);									//Send pet age to event calculator
					
					sleep(250);																//The Interval, 250ms
					
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------vPetAnim	
	public void vPetAnim() {

		vPetFrame = 0;
		vPetFrameSlide = 20;
		
		Thread vPetAnim = new Thread() {
			public void run() {
				try {
					
					while(true) {
						
						if (!vPetActive) {
							lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/slimeEgg.png")));
						} else {
						
						if (vPetStink) {
							Animation.stink();
						}
						if (Tools.breadLimit>0) {
							Animation.hasBread();
						}
						
						else if (Tools.rnJesus(100)>97 && !vPetBlink) {
							vPetBlink=true;vPetFrame = 0;
						}
						
						else if (vPetBlink) {Animation.blink();}
						
						else {Animation.idle();}
					
					lblImagemain.setBounds(vPetFrameSlide, 128, 62, 59);
					
					if (vPetFrameSlideDir == 'R') {
						if (vPetFrameSlide<36) {
							vPetFrameSlide++;}
						else {vPetFrameSlideDir = 'L';}
					} else if (vPetFrameSlideDir == 'L') {
						if (vPetFrameSlide>19) {
							vPetFrameSlide--;}
						 else {vPetFrameSlideDir = 'R';}
					}
						}
					
					sleep(Animation.framerate);
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		vPetAnim.start();
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------vPetGui	
	public vPetGui() {
		initialize();
		vPetAnim();
		clock();
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------Initialise
	private void initialize () {
		
		frmVpet = new JFrame();											//Frame
		frmVpet.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmVpet.setResizable(false);
		frmVpet.setTitle("vPet ("+ownerName+") main");
		frmVpet.setBounds(100, 100, 500, 300);
		frmVpet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVpet.getContentPane().setLayout(null);
		/*------------------------------------------------------------------------------------------------------------*/
		lblClock = new JLabel("Clock");									//Clock Label (Bottom Right)
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);			//	Displays current millis
		lblClock.setBounds(318, 12, 100, 20);
		frmVpet.getContentPane().add(lblClock);
		/*------------------------------------------------------------------------------------------------------------*/
		btnFeed = new JButton("");										//Feed Button (Top Right)
		btnFeed.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Buttons/breadButton.png")));
		btnFeed.addMouseListener(new MouseAdapter() {					//	Clicked to initiate feed
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Tools.feed("Bread");
			}
		});
		btnFeed.setBounds(203, 121, 40, 32);
		//frmVpet.getContentPane().add(btnFeed);
		/*------------------------------------------------------------------------------------------------------------*/
		lblLivedata = new JLabel("liveData");							//Live Data label (Bottom left)
		lblLivedata.setBounds(10, 238, 474, 20);						//	Displays current variable values
		frmVpet.getContentPane().add(lblLivedata);
		/*------------------------------------------------------------------------------------------------------------*/
		lblImagemain = new JLabel("");									//Main image 'animation' area (Center Left)
		lblImagemain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImagemain.setBounds(20, 128, 62, 59);
		frmVpet.getContentPane().add(lblImagemain);
		//lblImagemain.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Slime/slimeEgg.png")));
		/*------------------------------------------------------------------------------------------------------------*/
		textFieldOne = new JTextField("Type Here");						//Text Field One (Center Right)
		textFieldOne.addCaretListener(new CaretListener() {				//	Used to answer queries
			public void caretUpdate(CaretEvent arg0) {					//	variable updated per character
				fieldOneLive = textFieldOne.getText();
			}
		});
		textFieldOne.setBounds(296, 164, 89, 23);
		frmVpet.getContentPane().add(textFieldOne);
		textFieldOne.setColumns(10);
		textFieldOne.setText("");
		/*------------------------------------------------------------------------------------------------------------*/
		JButton btnSelectbuttonOne = new JButton("Select");				//Select Button (Center Right)
		btnSelectbuttonOne.addMouseListener(new MouseAdapter() {		//	Reacts to a click
			@Override													//	Used to assess entries in Text Field One
			public void mouseClicked(MouseEvent arg0) {			
				if (vPetEvents.waitForResponse) {						//Limiter to stop unwanted clicks
					System.out.println("Button pressed");				//Debug
					FieldOneSelection = textFieldOne.getText();			//Capture Text Field One
					vPetEvents.eventCalc(vPetMillisAlive);				//Send pet age to event calculator
					vPetEvents.setup++;									//Increment setup process *possible jank, linked to intro
					}
			}
		});
		btnSelectbuttonOne.setBounds(395, 164, 89, 23);
		frmVpet.getContentPane().add(btnSelectbuttonOne);
		/*------------------------------------------------------------------------------------------------------------*/
		btnSave = new JButton("S");										//Save Button (Center)
		btnSave.addMouseListener(new MouseAdapter() {					//	Reacts to a click
			@Override
			public void mouseClicked(MouseEvent arg0) {
				saving.save();				
			}
		});
		btnSave.setBounds(428, 11, 23, 23);
		//frmVpet.getContentPane().add(btnSave);
		/*------------------------------------------------------------------------------------------------------------*/
		JButton btnLoad = new JButton("L");								//Load Button (Center)
		btnLoad.addMouseListener(new MouseAdapter() {					//	Reacts to a click
			@Override
			public void mouseClicked(MouseEvent e) {
				saving.readSave('C');
				saving.readSave('L');
				vPetEvents.setup = 5;
			}
		});
		btnLoad.setBounds(461, 11, 23, 23);
		frmVpet.getContentPane().add(btnLoad);
		/*------------------------------------------------------------------------------------------------------------*/
		JPanel panel = new JPanel();									//Panel
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));	//	UI test.
		panel.setBounds(10, 195, 474, 39);								//	Used as a background for the Notification Label
		frmVpet.getContentPane().add(panel);
		panel.setLayout(null);
		/*------------------------------------------------------------------------------------------------------------*/
		lblvPetInsight = new JLabel("vPetInsight");						//Pet Insight Label
		lblvPetInsight.setHorizontalAlignment(SwingConstants.LEFT);		//	Displays various pet stats, verbose where possible
		lblvPetInsight.setBounds(10, 11, 454, 17);
		panel.add(lblvPetInsight);
		/*------------------------------------------------------------------------------------------------------------*/		
		lblH = new JLabel("Hunger:");
		lblH.setBounds(296, 128, 188, 14);
		vPetGui.lblH.setText("Hunger: ");		
		//frmVpet.getContentPane().add(lblH);
		/*------------------------------------------------------------------------------------------------------------*/		
		lblbreadCount = new JLabel("x10");
		lblbreadCount.setBounds(246, 121, 40, 32);
		//frmVpet.getContentPane().add(lblbreadCount);
		/*------------------------------------------------------------------------------------------------------------*/		
		lblDob = new JLabel("DOB");
		lblDob.setHorizontalAlignment(SwingConstants.LEFT);
		lblDob.setBounds(296, 96, 188, 14);
		//frmVpet.getContentPane().add(lblDob);
		/*------------------------------------------------------------------------------------------------------------*/		
		lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setBounds(296, 71, 110, 14);
		//frmVpet.getContentPane().add(lblAge);
		/*------------------------------------------------------------------------------------------------------------*/		
		lblWeight = new JLabel("Weight");
		lblWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeight.setBounds(416, 71, 68, 14);
		//frmVpet.getContentPane().add(lblWeight);
		/*------------------------------------------------------------------------------------------------------------*/		
		lblIconlower = new JLabel("");
		//lblIconlower.setIcon(null);
		lblIconlower.setBounds(92, 163, 35, 24);
		frmVpet.getContentPane().add(lblIconlower);
		/*------------------------------------------------------------------------------------------------------------*/		
		btnClean = new JButton("Clean");
		btnClean.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vPetEvents.clean();
			}
		});
		btnClean.setBounds(203, 164, 83, 23);
		//frmVpet.getContentPane().add(btnClean);
		/*------------------------------------------------------------------------------------------------------------*/
		lblPetName = new JLabel("petName");
		lblPetName.setBounds(296, 46, 92, 14);
		//frmVpet.getContentPane().add(lblPetName);
		/*------------------------------------------------------------------------------------------------------------*/
		lblIconMid = new JLabel("");
		lblIconMid.setBounds(92, 121, 35, 24);
		frmVpet.getContentPane().add(lblIconMid);
		/*------------------------------------------------------------------------------------------------------------*/
		JButton btnLog = new JButton("log");
		btnLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				saving.readSave('C');
				//System.out.println("Test "+vPetGui.vPetValue.get(12));
				Tools.logReader(vPetGui.vPetValue.get(12));
			}
		});
		btnLog.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLog.setBounds(10, 9, 51, 23);
		frmVpet.getContentPane().add(btnLog);
		/*------------------------------------------------------------------------------------------------------------Banner*/
		lblBanner = new JLabel("");
		lblBanner.setIcon(new ImageIcon(vPetGui.class.getResource("/vPetRes/images/Environment/bannerHome.png")));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setBounds(20, 46, 107, 39);
		//frmVpet.getContentPane().add(lblBanner);
		/*------------------------------------------------------------------------------------------------------------*/
	}
}
