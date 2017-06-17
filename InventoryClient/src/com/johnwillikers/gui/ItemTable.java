package com.johnwillikers.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;
import com.johnwillikers.io.In;

@SuppressWarnings("serial")
public class ItemTable extends JPanel{

	public static Object[][] itemsData;
	
	public ItemTable(){
		super(new GridLayout(1,0));
		
		String[] columnNames = {"Id", "Name", "Paid", "Date", "Description"};
		
		try {
			//Load the Items_Registry.jon then get the Items array out
			JSONObject itemsFile = In.readItem(Core.itemsFile);
			JSONArray items = itemsFile.getJSONArray("Items");
			
			//Iterate through every element and search for it's ID in the Items folder then load the information into an Object[]
			items.forEach(id -> {
				String item = (String) id;
				In.getItems(item);
			});
			final JTable table = new JTable(itemsData, columnNames);
			table.setPreferredScrollableViewportSize(new Dimension(500, 70));
			table.setFillsViewportHeight(true);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}