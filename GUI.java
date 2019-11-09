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
	
	static int frame;
	static int frameSlide;
	static boolean vPetBlink = false;
	
	public static JFrame frmGUI;
	public static JLabel lblImageMain;
	public static JButton btnLoad;
	public static JButton btnSave;
	public static JButton btnFeed;
	
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

		intro.introQuiz();
	}
	
	public GUI() {
		initialize();
		imgCycle.vPetAnim();
	}
	
	private void initialize() {
		frmGUI = new JFrame();											//Frame
		frmGUI.getContentPane().setBackground(Color.lightGray);
		frmGUI.setResizable(false);
		//frmGUI.setTitle("vPet ("+ownerName+") main");
		frmGUI.setBounds(100, 100, 500, 300);
		frmGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGUI.getContentPane().setLayout(null);
		
		lblImageMain = new JLabel("");									//Main image 'animation' area (Center Left)
		lblImageMain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImageMain.setBounds(20, 128, 62, 59);
		frmGUI.getContentPane().add(lblImageMain);
		lblImageMain.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Slime/slimeEgg.png")));
		
		btnSave = new JButton("S");										
		btnSave.addMouseListener(new MouseAdapter() {					//	Reacts to a click
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Saving.save();				
			}
		});
		btnSave.setBounds(428, 11, 23, 23);
		frmGUI.getContentPane().add(btnSave);
		/*------------------------------------------------------------------------------------------------------------*/
		btnLoad = new JButton("L");								
		btnLoad.addMouseListener(new MouseAdapter() {					//	Reacts to a click
			@Override
			public void mouseClicked(MouseEvent e) {
				Saving.readSave('C');
				Saving.readSave('L');
			}
		});
		btnLoad.setBounds(461, 11, 23, 23);
		frmGUI.getContentPane().add(btnLoad);
		/*------------------------------------------------------------------------------------------------------------*/
		btnFeed = new JButton("");										
		btnFeed.setIcon(new ImageIcon(GUI.class.getResource("/resource/images/Buttons/breadButton.png")));
		btnFeed.addMouseListener(new MouseAdapter() {					//	Clicked to initiate feed
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Interaction.feed("Bread");
			}
		});
		btnFeed.setBounds(203, 121, 40, 32);
		frmGUI.getContentPane().add(btnFeed);

	}

}
