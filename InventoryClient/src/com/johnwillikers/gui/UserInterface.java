package com.johnwillikers.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.johnwillikers.Core;

@SuppressWarnings("serial")
public class UserInterface extends JFrame{

	public UserInterface(){
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("Hello World"));
		cp.add(new JButton("Button"));
		ItemTable itemTable = new ItemTable();
		itemTable.setOpaque(true);
		setContentPane(itemTable);
		pack();
		setTitle(Core.name);
		setSize(800, 600);
		setVisible(true);
	}

}