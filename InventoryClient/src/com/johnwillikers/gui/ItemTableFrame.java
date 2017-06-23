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

import org.json.JSONException;

import com.johnwillikers.Core;
import com.johnwillikers.gui.tables.ItemTable;
import com.johnwillikers.mysql.DbCon;

public class ItemTableFrame extends JFrame{

	private static final long serialVersionUID = -3870038291107315483L;
	ItemTableFrame frame = this;
	
	public ItemTableFrame(){
		
		//Setup Items Table Pane
		JPanel table = new JPanel(new BorderLayout());
		ItemTable itemTable = new ItemTable();
		itemTable.setOpaque(true);
		table.add(itemTable);				
		
		//Setup Sell editor
		JPanel sellEditor = new JPanel(new FlowLayout());
		
		//Initialize TextFields
		JTextField idText = new JTextField(8);
		JTextField buyerText = new JTextField(18);
		JTextField sellDateText = new JTextField(7);
		JTextField sellPriceText = new JTextField(7);
		JTextField notesText = new JTextField(30);
		
		//Initialize Buttons
		JButton submitSold = new JButton("Sell");
		submitSold.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DbCon connection = new DbCon("test", "sold_items", "localhost", "root", "lonely4life99");
				String[] itemDetails = connection.getItem(idText.getText().toString());
				//System.out.println(itemDetails.length);
				connection.createSoldItem(itemDetails[0], itemDetails[1], Float.valueOf(itemDetails[2]), itemDetails[3], itemDetails[4],
										Float.valueOf(sellPriceText.getText()), buyerText.getText().toString(), sellDateText.getText().toString(),
											notesText.getText().toString());
				connection.deleteItem(idText.getText(), "items");
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						new ItemTableFrame();
						new SoldItemTableFrame();
					}
				});
				frame.dispose();
			}
			
		});
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DbCon connection = new DbCon("test", "items", "localhost", "root", "lonely4life99");
					connection.deleteItem(idText.getText().toString(), "items");
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							new ItemTableFrame();
						}
					});
					frame.dispose();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		//Setup Component panels
		JPanel idPanel = new JPanel(new FlowLayout());
		idPanel.add(new JLabel("Id:"));
		idPanel.add(idText);
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
		sellEditor.add(delete);
		sellEditor.add(idPanel);
		sellEditor.add(buyerPanel);
		sellEditor.add(sellDatePanel);
		sellEditor.add(sellPricePanel);
		sellEditor.add(notesTextPanel);
		sellEditor.add(submitSold);

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
						new NewItemFrame(frame);
					}
				});
				
			}
			
		});
		menu.add(menuItem);
		
		int heightSize = 600;
		int screenWidth = (int) MainFrame.screenSize.getWidth()/2;
		int screenHeight = (int) MainFrame.screenSize.getHeight()/2;
		
		//Setup Split pane 1
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, table, sellEditor);
		splitPane1.setDividerLocation((int) ((float) heightSize/1.33));

		//Show SplitPane
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setJMenuBar(menuBar);
		setContentPane(splitPane1);
		pack();
		setTitle(Core.name + " | UnSold Items");
		setLocation(0, screenHeight - (heightSize/2));
		setSize(screenWidth, heightSize);
		setVisible(true);
	}

}