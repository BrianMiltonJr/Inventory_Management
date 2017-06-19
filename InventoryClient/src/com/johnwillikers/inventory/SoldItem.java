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

public class SoldItem {
	
	public String id;
	public String name;
	public String desc;
	public String paidDate;
	public String soldDate;
	public String buyerName;
	public String notes;
	public int paidPrice;
	public int soldPrice;
	
	public SoldItem(String id, String name, String desc, String paidDate, String soldDate, int paidPrice, int soldPrice, String buyerName, String notes){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.paidDate = paidDate;
		this.soldDate = soldDate;
		this.paidPrice = paidPrice;
		this.soldPrice = soldPrice;
		this.buyerName = buyerName;
		this.notes = notes;
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
	
	public String getSoldDate(){
		return this.soldDate;
	}
	
	public int getPaid(){
		return this.paidPrice;
	}
	
	public int getSold(){
		return this.soldPrice;
	}
	
	public int getProfit(){
		return this.soldPrice - this.paidPrice; 
	}
	
	public void eraseItem(){
		File item = new File(Core.soldItemsDir + this.id + ".json");
		item.delete();
		try {
			JSONObject itemsRegistry = In.readItem(Core.soldItemsFile);
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
			Core.soldItemsFile.delete();
			Out.print(Core.soldItemsFile, itemsRegistry.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveItem(){
		Out.saveItem(id, name, desc, paidDate, soldDate ,paidPrice, soldPrice, buyerName, notes);
	}
	
	public void destroy(){
		this.destroy();
	}
	
}
