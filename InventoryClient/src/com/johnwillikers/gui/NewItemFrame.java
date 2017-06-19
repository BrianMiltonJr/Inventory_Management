package com.johnwillikers.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		JLabel idLabel = new JLabel("Id");
		JLabel nameLabel = new JLabel("Name");
		JLabel descLabel = new JLabel("Description");
		JLabel dateLabel = new JLabel("Date");
		JLabel paidLabel = new JLabel("Paid");
		JLabel priceLabel = new JLabel("Price");
		
		//Create Text Fields
		JTextField idText = new JTextField(5);
		JTextField nameText = new JTextField(10);
		JTextField descText = new JTextField(20);
		JTextField dateText = new JTextField(7);
		JTextField paidText = new JTextField(7);
		JTextField priceText = new JTextField(7);
		
		//Create Button
		JButton submit = new JButton("Create");
		submit.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Item item = new Item(idText.getText(), nameText.getText(), descText.getText(), dateText.getText(), Integer.valueOf(paidText.getText()), Integer.valueOf(priceText.getText()));
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
		
		//Create Pane
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(idLabel);
		panel.add(idText);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(descLabel);
		panel.add(descText);
		panel.add(dateLabel);
		panel.add(dateText);
		panel.add(paidLabel);
		panel.add(paidText);
		panel.add(priceLabel);
		panel.add(priceText);
		panel.add(submit);
		
		//Setup details
		setContentPane(panel);
		setTitle("Create a new Item");
		setSize(300, 500);
		setVisible(true);
	}

}
