package com.johnwillikers;

import java.io.File;

import com.johnwillikers.inventory.Item;

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
	
	//File System variables
	public static File inventoryDir = new File("./Inventory/");
	public static File itemsDir = new File(inventoryDir + "Items/");
	public static File soldItemsDir = new File(inventoryDir + "Sold_Items/");
	
	public static void firstLaunch(){
		if(!itemsDir.exists())
			itemsDir.mkdirs();
		if(!soldItemsDir.exists())
			soldItemsDir.mkdirs();
		Item computer = new Item("CVC0001", "Computer", "High Tech Computer. Prtetty nice.", 2500);
		computer.saveItem();
	}
	
	public static void main(String[] args){
		firstLaunch();
	}
}
