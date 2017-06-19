package com.johnwillikers.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.johnwillikers.Core;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = -3101382095506189484L;

	public MainFrame(){
		
		JButton openItemTable = new JButton("Open UnSold Items Table.");
		openItemTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						new ItemTableFrame();
					}
				});
			}
			
		});
		
		JButton openSoldItemTable = new JButton("Open Sold Items Table.");
		openSoldItemTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						new SoldItemTableFrame();
					}
				});
			}
			
		});
		
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(openItemTable);
		panel.add(openSoldItemTable);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		pack();
		setTitle(Core.name);
		setSize(500, 75);
		setVisible(true);
	}
	
}
