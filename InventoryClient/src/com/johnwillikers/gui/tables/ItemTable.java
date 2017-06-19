package com.johnwillikers.gui.tables;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;
import com.johnwillikers.inventory.Item;
import com.johnwillikers.io.In;

@SuppressWarnings("serial")
public class ItemTable extends JPanel{

	public static Object[][] itemsData;
	
	public ItemTable(){
		super(new GridLayout(1,0));
		
		String[] columnNames = {"Id", "Name", "Paid", "Date", "Description", "Price", "Expected Profit"};
		
		try {
			//Load the Items_Registry.jon then get the Items array out
			JSONObject itemsFile = In.readItem(Core.itemsFile);
			JSONArray items = itemsFile.getJSONArray("Items");
			
			//TODO setup a process that dynamically adds columns.
			//Set up Table Model Columns
			DefaultTableModel tm = new DefaultTableModel();
			tm.addColumn(columnNames[0]);
			tm.addColumn(columnNames[1]);
			tm.addColumn(columnNames[2]);
			tm.addColumn(columnNames[3]);
			tm.addColumn(columnNames[4]);
			tm.addColumn(columnNames[5]);
			tm.addColumn(columnNames[6]);
			
			/*Iterate through every element and search for it's ID in the Items folder then load the information 
			 *into an Object[] to be added to the TableModel
			 */
			//Object[] prices = {};
			//int i = 0;
			items.forEach(id -> {
				String item = (String) id;
				Object[] data = In.getItems(item);
				//prices[i] = data[5];
				//i++;
				//prices[i] = data[6];
				//i++;
				tm.addRow(data);
			});
			
			
			//Object[] lastRowData = {"","","","", "", "",""};
			//tm.addRow("");
			
			//Setup JTable
			final JTable table = new JTable(tm);
			table.getModel().addTableModelListener(new TableModelListener(){

				@Override
				public void tableChanged(TableModelEvent e) {
					if(e.getColumn() == 6){
						System.out.println("Row " + e.getFirstRow() + "'s expected profit has been updated");
					}else{
						DefaultTableModel tm = (DefaultTableModel) e.getSource();
						int row = e.getFirstRow();
						String id = tm.getValueAt(row, 0).toString();
						String name = tm.getValueAt(row, 1).toString();
						float paid = Float.valueOf(tm.getValueAt(row, 2).toString());
						String date = tm.getValueAt(row, 3).toString();
						String desc = tm.getValueAt(row, 4).toString();
						float price = Float.valueOf(tm.getValueAt(row, 5).toString());
						Item item = new Item(id, name, desc, date, paid, price);
						item.saveItem();
						Object aValue = price - paid;
						tm.setValueAt(aValue, row, 6);
					}
				}
				
			});
			//table.setEnabled(false);
			table.setRowHeight(30);
			table.setPreferredScrollableViewportSize(new Dimension(500, 300));
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