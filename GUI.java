package vPetSrc;

import java.awt.EventQueue;
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
import javax.swing.JFrame;

public class GUI {
	
	public static int frame;
	public static int lblIconMidFrame;
	public static int lblImageOlayFrame;
	public static int frameSlide;
	public static boolean vPetBlink = false;
	public static boolean stink = false;
	
	public static JFrame frmGUI;
	public static JLabel lblImageMain;
	public static JLabel lblImageOlay;
	public static JLabel lblInfoOne;
	public static JButton btnLoad;
	public static JButton btnSave;
	public static JButton btnFeed;
	public static JButton btnClean;
	public static JLabel lblIconlower;
	public static JLabel lblIconMid;
	private static JButton btnScoop;
	private static JTextField textField;
	private static JLabel lblBanner;
	public static JLabel lblInsightOne;
	public static JLabel lblInsightTwo;
	public static JLabel lblInsightThree;
	public static JLabel lblStomach;
	public static JLabel lblHygiene;
	public static JLabel lblBreadCount;
	public static JLabel lblBed;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public GUI() {
		
		System.out.println("gui top");
		
		initialize();
		imgCycle.vPetAnim();
		
		System.out.println("gui bottom");
	}
	
	public static void initialize() {

		System.out.println("init top");
		/*------------------------------------------------------------------------------------------------------------*/
		frmGUI = new JFrame();											//Frame
		frmGUI.getContentPane().setBackground(new Color(118, 214, 127));
		frmGUI.setResizable(false);
		//frmGUI.setTitle("vPet ("+ownerName+") main");
		frmGUI.setBounds(100, 100, 616, 400);
		frmGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGUI.getContentPane().setLayout(null);
		/*------------------------------------------------------------------------------------------------------------*/
		lblImageOlay = new JLabel("");									//Main image 'animation' area (Center Left)
		lblImageOlay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImageOlay.setBounds(55, 139, 99, 71);
		frmGUI.getContentPane().add(lblImageOlay);
		lblImageMain = new JLabel("");									//Main image 'animation' area (Center Left)
		lblImageMain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImageMain.setBounds(55, 139, 99, 71);
		frmGUI.getContentPane().add(lblImageMain);
		/*------------------------------------------------------------------------------------------------------------*/
		btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/save.png")));
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false); 
		btnSave.setFocusPainted(true); 
		btnSave.setOpaque(false);
		btnSave.addMouseListener(new MouseAdapter() {					//	Reacts to a click
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Saving.save();				
			}
		});
		/*------------------------------------------------------------------------------------------------------------*/
		
		lblBed = new JLabel("bed");
		lblBed.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Environment/bed.png")));
		lblBed.setBounds(100, 120, 94, 67);
		frmGUI.getContentPane().add(lblBed);
		textField = new JTextField();
		textField.setBounds(10, 340, 300, 20);
		frmGUI.getContentPane().add(textField);
		textField.setColumns(10);
		/*------------------------------------------------------------------------------------------------------------*/
		lblInsightOne = new JLabel("insightOne");
		lblInsightOne.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInsightOne.setBounds(15, 311, 300, 25);
		frmGUI.getContentPane().add(lblInsightOne);
		/*------------------------------------------------------------------------------------------------------------*/
		lblInsightTwo = new JLabel("insightTwo");
		lblInsightTwo.setBounds(15, 289, 300, 25);
		frmGUI.getContentPane().add(lblInsightTwo);
		/*------------------------------------------------------------------------------------------------------------*/
		lblInsightThree = new JLabel("insightThree");
		lblInsightThree.setBounds(15, 264, 300, 25);
		frmGUI.getContentPane().add(lblInsightThree);
		btnSave.setBounds(506, 11, 34, 34);
		frmGUI.getContentPane().add(btnSave);
		/*------------------------------------------------------------------------------------------------------------*/
		btnLoad = new JButton("");								
		btnLoad.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/load.png")));
		btnLoad.setBorderPainted(false);
		btnLoad.setContentAreaFilled(false); 
		btnLoad.setFocusPainted(false); 
		btnLoad.setOpaque(false);
		btnLoad.addMouseListener(new MouseAdapter() {					//	Reacts to a click
			@Override
			public void mouseClicked(MouseEvent e) {
				Saving.readSave('C');
				Saving.readSave('L');
			}
		});
		btnLoad.setBounds(550, 11, 34, 34);
		frmGUI.getContentPane().add(btnLoad);
		/*------------------------------------------------------------------------------------------------------------*/
		btnFeed = new JButton("");										
		btnFeed.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/feed.png")));
		btnFeed.addMouseListener(new MouseAdapter() {					//	Clicked to initiate feed
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Interaction.feed("Bread");
			}
		});
		btnFeed.setBounds(518, 257, 66, 32);
		frmGUI.getContentPane().add(btnFeed);

	/*------------------------------------------------------------------------------------------------------------*/		
	btnClean = new JButton("");
	btnClean.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/clean.png")));
	btnClean.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Interaction.clean();
		}
	});
	btnClean.setBounds(518, 328, 66, 32);
	frmGUI.getContentPane().add(btnClean);
	/*------------------------------------------------------------------------------------------------------------*/
	btnScoop = new JButton("");
	btnScoop.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Interaction.scoop();
		}
	});
	btnScoop.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/scoop.png")));
	btnScoop.setBounds(518, 293, 66, 32);
	frmGUI.getContentPane().add(btnScoop);
	/*------------------------------------------------------------------------------------------------------------*/
	JButton btnLog = new JButton("");
	btnLog.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/log.png")));
	btnLog.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Saving.readSave('C');
			Tools.logReader(Saving.vPetValue.get(0));
		}
	});
	btnLog.setFont(new Font("Tahoma", Font.PLAIN, 9));
	btnLog.setBounds(10, 9, 66, 32);
	frmGUI.getContentPane().add(btnLog);
	/*------------------------------------------------------------------------------------------------------------*/
	lblBreadCount = new JLabel("breadCount");
	lblBreadCount.setHorizontalAlignment(SwingConstants.LEFT);
	lblBreadCount.setBounds(474, 259, 32, 32);
	GUI.lblBreadCount.setText(Menu.bread+" X ");
	frmGUI.getContentPane().add(lblBreadCount);
	/*------------------------------------------------------------------------------------------------------------*/
	lblHygiene = new JLabel("Hygiene");
	lblHygiene.setBounds(325, 343, 139, 14);
	frmGUI.getContentPane().add(lblHygiene);
	/*------------------------------------------------------------------------------------------------------------*/
	lblStomach = new JLabel("Stomach");
	lblStomach.setBounds(325, 269, 139, 14);
	frmGUI.getContentPane().add(lblStomach);
	/*------------------------------------------------------------------------------------------------------------*/
	lblIconMid = new JLabel("");
	lblIconMid.setBounds(10, 153, 35, 24);
	frmGUI.getContentPane().add(lblIconMid);
	/*------------------------------------------------------------------------------------------------------------*/		
	lblIconlower = new JLabel("");
	lblIconlower.setBounds(10, 186, 35, 24);
	frmGUI.getContentPane().add(lblIconlower);
	/*------------------------------------------------------------------------------------------------------------*/
	lblInfoOne = new JLabel("");
	lblInfoOne.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Panels/panelOne.png")));
	lblInfoOne.setBounds(0, 221, 600, 150);
	frmGUI.getContentPane().add(lblInfoOne);
	/*------------------------------------------------------------------------------------------------------------*/
	lblBanner = new JLabel("");
	lblBanner.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Environment/bannerHome.png")));
	lblBanner.setBounds(493, 56, 107, 39);
	frmGUI.getContentPane().add(lblBanner);
	/*------------------------------------------------------------------------------------------------------------*/
	System.out.println("init bottom");
	}
	/*------------------------------------------------------------------------------------------------------------*/
	
	public static void messCheck() {
		if (vPet.poos>0) {
			stink=true;
		} 
		else if (vPet.poos==0) {
			stink=false;
			GUI.lblIconlower.setIcon(null);
			GUI.lblIconMid.setIcon(null);
		}
	}
}
