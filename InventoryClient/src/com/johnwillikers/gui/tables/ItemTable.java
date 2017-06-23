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
public class ItemTable extends JPanel{

	public static Object[][] itemsData;
	
	public ItemTable(){
		super(new GridLayout(1,0));
		
		String[] columnNames = {"Id", "Name", "Paid", "Date", "Description", "Price", "Expected Profit"};
		
		try {
			//Get the List<Object> from the Mysql DB
			DbCon connection = new DbCon("test", "items", "localhost", "root", "lonely4life99");
			List<Object> items = connection.retrieveItems();
			
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
			
			/*
			 * There are 6 elements per row. So this means I can pull the data out in pathes of six. You will see what i mean.
			 */
			int index = 0;
			System.out.println(items.toString());
			System.out.println(items.size());
			while(index < items.size()){
				Object[] derp = {items.get(index + 0), items.get(index + 1), items.get(index + 2), items.get(index + 3), items.get(index + 4), items.get(index + 5), (float) items.get(index + 5) -  (float) items.get(index + 2)};
				index = index + 6;
				tm.addRow(derp);
			}
			
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
						DbCon connection = new DbCon("test", "items", "localhost", "root", "lonely4life99");
						connection.updateItem(id, name, paid, date, desc, price);
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
		}
	}
}