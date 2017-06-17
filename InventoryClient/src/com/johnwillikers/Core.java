package com.johnwillikers;

import java.io.File;

import javax.swing.SwingUtilities;

import com.johnwillikers.gui.UserInterface;
import com.johnwillikers.io.In;

public class Core {
	
	//Authoring information
	public static String Author = "Harlan Brian Milton Jr. | JohnWillikers";
	public static String name = "A.E. Carlisle Group Inventory Tool";
	public static String version = "0.0.1";
	public static String website = "aecarlisle.com";
	
	/*
	 * This is a ghetto rigged solution for merging two methods into one method.
	 * Instead of having one method for saving Items and another for SoldItems.
	 * I'm making 20,000,000 a special value that makes the method save as an Item.
	 * Any other value will result in the method saving a SoldItem. And we could just
	 * make the check call for <= 0 but fuck that. The sale could be 0 or less due to
	 * real life circumstances, so Might as well do this. And hey, all they user has to
	 * do is sell the item for any other number but 20,000,000. So fuck you world haha.
	 * 
	 * Reference to: com.johnwillikers.io.Out().saveItem()
	 */
	public static int saveItemCode = 20000000;
	public static String saveItemCodeString = "00/00/0000";
	
	//File System variables
	public static String inventoryDir ="./Inventory/";
	public static String itemsDir = inventoryDir + "Items/";
	public static String soldItemsDir = inventoryDir + "Sold_Items/";
	public static String settingsDir = "./Settings/";
	public static File settingsFile = new File(settingsDir + "settings.json");
	public static File itemsFile = new File(inventoryDir + "Items_Registry.json");
	public static File soldItemsFile = new File(inventoryDir + "Sold_Items_Registry.json");
	
	public static void firstLaunch(){
		File items = new File(itemsDir);
		File soldItems = new File(soldItemsDir);
		File settings = new File(settingsDir);
		if(!items.exists())
			items.mkdirs();
		if(!soldItems.exists())
			soldItems.mkdirs();
		if(!settings.exists())
			settings.mkdirs();
	}
	
	public static void main(String[] args){
		firstLaunch();
		In.setSettings();
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new UserInterface();
			}
		});
	}
}
