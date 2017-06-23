package com.johnwillikers.gui.tables;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import com.johnwillikers.mysql.DbCon;

@SuppressWarnings("serial")
public class SoldItemTable extends JPanel{

	public static Object[][] itemsData;
	
	public SoldItemTable(){
		super(new GridLayout(1,0));
		
		String[] columnNames = {"Id", "Name", "Notes", "Date", "Description", "Sold Date", "Buyer Name", "Paid", "Sold", "Profit"};
		
		try {
			//Load sol_items table to List<String>
			DbCon connection = new DbCon("test", "sold_items", "localhost", "root", "lonely4life99");
			List<Object> items = connection.retrieveSoldItems();
			
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
			tm.addColumn(columnNames[7]);
			tm.addColumn(columnNames[8]);
			tm.addColumn(columnNames[9]);
			
			/*
			 * Add all the Items from the list into the table rows
			 */
			int index = 0;
			System.out.println(items.toString());
			System.out.println(items.size());
			while(index < items.size()){
				Object[] derp = {items.get(index + 0), items.get(index + 1), items.get(index + 8), items.get(index + 3), items.get(index + 4),
						items.get(index + 7), items.get(index + 6), items.get(index + 2), items.get(index + 5),
						(float) items.get(index + 5) -  (float) items.get(index + 2)};
				index = index + 9;
				tm.addRow(derp);
			}
			
			//Setup JTable
			final JTable table = new JTable(tm);
			table.getModel().addTableModelListener(new TableModelListener(){

				@Override
				public void tableChanged(TableModelEvent e) {
					if(e.getColumn() == 9){
						System.out.println("Row " + e.getFirstRow() + "'s profit has been updated.");
					}else{
						DefaultTableModel tm = (DefaultTableModel) e.getSource();
						int row = e.getFirstRow();
						String id = tm.getValueAt(row, 0).toString();
						String name = tm.getValueAt(row, 1).toString();
						float paidPrice = Float.valueOf(tm.getValueAt(row, 2).toString());
						String paidDate = tm.getValueAt(row, 3).toString();
						String desc = tm.getValueAt(row, 4).toString();
						String soldDate = tm.getValueAt(row, 5).toString();
						String buyerName = tm.getValueAt(row, 6).toString();
						String notes = tm.getValueAt(row, 7).toString();
						float soldPrice = Float.valueOf(tm.getValueAt(row, 8).toString());
						DbCon connection = new DbCon("test", "sold_items", "localhost", "root", "lonely4life99");
						connection.updateSoldItem(id, name, paidPrice, paidDate, desc, soldPrice, buyerName, soldDate, notes);
						Object aValue = soldPrice - paidPrice;
						tm.setValueAt(aValue, row, 9);
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
		}
	}
}