package com.johnwillikers.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.johnwillikers.Core;
import com.johnwillikers.gui.tables.ItemTable;

public class ItemTableFrame extends JFrame{

	private static final long serialVersionUID = -3870038291107315483L;

	public ItemTableFrame(){
		
		//Setup Sell editor
		JPanel sellEditor = new JPanel(new FlowLayout());
		
		//Initialize TextFields
		JTextField buyerText = new JTextField(12);
		JTextField sellDateText = new JTextField(7);
		JTextField sellPriceText = new JTextField(7);
		JTextField notesText = new JTextField(20);
		
		//Initialize Buttons
		JButton submitSold = new JButton("Sell");
		
		//Setup Component panels
		JPanel buyerPanel = new JPanel(new FlowLayout());
		buyerPanel.add(new JLabel("Buyer's Name"));
		buyerPanel.add(buyerText);
		JPanel sellDatePanel = new JPanel(new FlowLayout());
		sellDatePanel.add(new JLabel("Date"));
		sellDatePanel.add(sellDateText);
		JPanel sellPricePanel = new JPanel(new FlowLayout());
		sellPricePanel.add(new JLabel("Price"));
		sellPricePanel.add(sellPriceText);
		JPanel notesTextPanel = new JPanel(new FlowLayout());
		notesTextPanel.add(new JLabel("Notes"));
		notesTextPanel.add(notesText);
		
		//Add Component panels to main panel
		sellEditor.add(buyerPanel);
		sellEditor.add(sellDatePanel);
		sellEditor.add(sellPricePanel);
		sellEditor.add(notesTextPanel);
		sellEditor.add(submitSold);
		
		//Setup Items Table Pane
		JPanel table = new JPanel(new BorderLayout());
		ItemTable itemTable = new ItemTable();
		itemTable.setOpaque(true);
		table.add(itemTable);
		
		//Setup Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Create a new Item", KeyEvent.VK_F1);
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected Create an item.");
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						new NewItemFrame();
					}
				});
				
			}
			
		});
		menu.add(menuItem);
		
		//Setup Split pane 1
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, table, sellEditor);
		
		//Show SplitPane
		setJMenuBar(menuBar);
		setContentPane(splitPane1);
		pack();
		setTitle(Core.name);
		setSize(800, 600);
		setVisible(true);
	}

}