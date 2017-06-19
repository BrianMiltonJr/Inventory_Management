package com.johnwillikers.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;
import com.johnwillikers.gui.tables.SoldItemTable;
import com.johnwillikers.inventory.SoldItem;
import com.johnwillikers.io.In;

public class SoldItemTableFrame extends JFrame{

	private static final long serialVersionUID = -3937870382110731483L;
	SoldItemTableFrame frame = this;
	
	public SoldItemTableFrame(){
		
		//Setup table editor
		JPanel tableEditor = new JPanel(new FlowLayout());
		
		//Setup Text Fields
		JTextField idText = new JTextField(7);
		
		//Setup buttons
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JSONObject itemD = In.readItem(new File(Core.soldItemsDir + idText.getText() + ".json"));
					SoldItem item = new SoldItem(itemD.getString("id"), itemD.getString("name"), itemD.getString("desc"), itemD.getString("paidDate"),
							itemD.getString("soldDate"), itemD.getInt("paidPrice"), itemD.getInt("soldPrice"), itemD.getString("buyerName"),
							itemD.getString("notes"));
					item.eraseItem();
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							new SoldItemTableFrame();
						}
					});
					frame.dispose();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		//Add components to table editor
		tableEditor.add(new JLabel("Id"));
		tableEditor.add(idText);
		tableEditor.add(delete);
		
		//Setup Items Table Pane
		JPanel table = new JPanel(new BorderLayout());
		SoldItemTable itemTable = new SoldItemTable();
		itemTable.setOpaque(true);
		table.add(itemTable);

		
		//Setup Split pane 1
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, table, tableEditor);
		
		//Show SplitPane
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(splitPane1);
		pack();
		setTitle(Core.name + " | Sold Items");
		int heightSize = 600;
		int screenWidth = (int) MainFrame.screenSize.getWidth()/2;
		int screenHeight = (int) MainFrame.screenSize.getHeight()/2;
		setLocation(screenWidth, screenHeight - (heightSize/2));
		setSize(screenWidth, heightSize);
		setVisible(true);
	}

}