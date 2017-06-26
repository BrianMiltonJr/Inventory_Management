package com.johnwillikers.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GetReceiptFrame extends JFrame{
	
	private static final long serialVersionUID = -8404105289159851422L;
	
	public GetReceiptFrame frame = this;
	
	public GetReceiptFrame(String id){
		
		//Create Labels
		JLabel emailLabel = new JLabel("Enter the Customer's Email     ");
		
		//Create Texts
		JTextField emailText = new JTextField();
		
		//Create Button
		JButton generateReceipt = new JButton("Generate Receipt");
		generateReceipt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						new GenerateReceiptFrame(id, emailText.getText());
						frame.dispose();
					}
				});
			}
			
		});
		
		//Setup component Panels
		JPanel emailPanel = new JPanel(new GridLayout());
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		emailPanel.add(generateReceipt);
		
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.add(emailPanel);
		
		//Setup Frame
		setContentPane(flowPanel);
		pack();
		setSize(800, 75);
		setVisible(true);
	}
}
