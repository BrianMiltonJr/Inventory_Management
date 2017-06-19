package com.johnwillikers.inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;
import com.johnwillikers.io.In;
import com.johnwillikers.io.Out;

public class Item {
	
	public String id;
	public String name;
	public String desc;
	public String paidDate;
	public int paidPrice;
	public int price;
	
	public Item(String id, String name, String desc, String paidDate ,int paidPrice, int price){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.paidDate = paidDate;
		this.paidPrice = paidPrice;
		this.price = price;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public String getPaidDate(){
		return this.paidDate;
	}
	
	public int getPaid(){
		return this.paidPrice;
	}
	
	public void saveItem(){
		Out.saveItem(id, name, desc, paidDate, Core.saveItemCodeString, paidPrice, price, "DERPHERP", "HERPDERP");
	}
	
	public void eraseItem(){
		File item = new File(Core.itemsDir + this.id + ".json");
		item.delete();
		try {
			JSONObject itemsRegistry = In.readItem(Core.itemsFile);
			JSONArray items = itemsRegistry.getJSONArray("Items");
			List<String> derps = new ArrayList<String>();
			for(int i = 0; i < items.length(); i++){
				derps.add(items.getString(i));
			}
			derps.remove(this.id);
			JSONArray newItems = new JSONArray();
			for(int i = 0; i < derps.size(); i++){
				newItems.put(derps.get(i));
			}
			itemsRegistry.put("Items", newItems);
			Core.itemsFile.delete();
			Out.print(Core.itemsFile, itemsRegistry.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sellItem(String buyerName, String soldDate, int soldPrice, String notes){
		SoldItem item = new SoldItem(this.id, this.name, this.desc, this.paidDate, soldDate, this.paidPrice, soldPrice, buyerName, notes);
		item.saveItem();
		this.eraseItem();
	}
	
	public void destroy() throws Throwable{
		this.finalize();
	}
}
