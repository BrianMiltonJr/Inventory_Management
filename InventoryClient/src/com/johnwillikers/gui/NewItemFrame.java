package com.johnwillikers.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.johnwillikers.inventory.Item;

public class NewItemFrame extends JFrame{

	private static final long serialVersionUID = 4340362860056121655L;
	//private TableModel tm;
	
	NewItemFrame frame0 = this;
	
	public NewItemFrame(ItemTableFrame frame){
		
		//this.tm = tm;
		
		//Create Labels
		JLabel idLabel = new JLabel("Id             ");
		JLabel nameLabel = new JLabel("Name   ");
		JLabel descLabel = new JLabel("Description");
		JLabel dateLabel = new JLabel("               Date");
		JLabel paidLabel = new JLabel("Paid               ");
		JLabel priceLabel = new JLabel("          Price");
		
		//Create Text Fields
		JTextField idText = new JTextField(9);
		JTextField nameText = new JTextField(25);
		JTextArea descText = new JTextArea(10, 25);
		descText.setLineWrap(true);
		descText.setWrapStyleWord(true);
		JTextField dateText = new JTextField(10);
		JTextField paidText = new JTextField(9);
		JTextField priceText = new JTextField(10);
		
		//Create Button
		JButton submit = new JButton("Create");
		submit.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Item item = new Item(idText.getText(), nameText.getText(), descText.getText(), dateText.getText(), Float.valueOf(paidText.getText()), Integer.valueOf(priceText.getText()));
				item.saveItem();
				frame0.dispose();
					
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						new ItemTableFrame();
					}
				});
				frame.dispose();
			}
			
		});
		
		//Element Panels
		JPanel idDateLabelPanel = new JPanel(new FlowLayout());
		idDateLabelPanel.add(idLabel);
		idDateLabelPanel.add(dateLabel);
		JPanel idDateTextPanel = new JPanel(new FlowLayout());
		idDateTextPanel.add(idText);
		idDateTextPanel.add(new JLabel("     "));
		idDateTextPanel.add(dateText);
		JPanel nameLabelPanel = new JPanel(new FlowLayout());
		nameLabelPanel.add(nameLabel);
		JPanel nameTextPanel = new JPanel(new FlowLayout());
		nameTextPanel.add(nameText);
		JPanel paidPriceLabelPanel = new JPanel(new FlowLayout());
		paidPriceLabelPanel.add(paidLabel);
		paidPriceLabelPanel.add(priceLabel);
		JPanel paidPriceTextPanel = new JPanel(new FlowLayout());
		paidPriceTextPanel.add(paidText);
		paidPriceTextPanel.add(new JLabel("     "));
		paidPriceTextPanel.add(priceText);
		JPanel descLabelPanel = new JPanel(new FlowLayout());
		descLabelPanel.add(descLabel);
		JPanel descTextPanel = new JPanel(new GridLayout());
		JScrollPane textScrollPane = new JScrollPane(descText);
		descTextPanel.add(textScrollPane);
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(submit);
		JPanel spacerPanel = new JPanel(new FlowLayout());
		spacerPanel.add(new JLabel("                                                                   "));
		
		//Setup Label + Text Fields
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(idDateLabelPanel);
		panel.add(idDateTextPanel);
		panel.add(nameLabelPanel);
		panel.add(nameTextPanel);
		panel.add(paidPriceLabelPanel);
		panel.add(paidPriceTextPanel);
		panel.add(descLabelPanel);
		panel.add(descTextPanel);
		panel.add(spacerPanel);
		panel.add(buttonPanel);
		
		//Setup details
		setContentPane(panel);
		setTitle("Create a new Item");
		int widthSize = 300;
		int heightSize = 500;
		int screenWidth = (int) MainFrame.screenSize.getWidth()/2;
		int screenHeight = (int) MainFrame.screenSize.getHeight()/2;
		setLocation(screenWidth - (widthSize/2), screenHeight - (heightSize/2));
		setSize(widthSize, heightSize);
		setVisible(true);
	}

}
