package com.johnwillikers.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;

public class Out {
	
	/*
	 * Written to keep me from adding so much every-time I needed to print. - John <3 
	 */
	public static void print(File file, String string)throws IOException{
		PrintWriter pr = new PrintWriter(file);
		pr.println(string);
		pr.close();
	}
	
	public static boolean appendItemsFile(String id){
		if(!Core.itemsFile.exists()){
			JSONArray items = new JSONArray().put(id);
			JSONObject itemsFile = new JSONObject().put("Items", items);
			try {
				print(Core.itemsFile, itemsFile.toString());
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			try {
				JSONObject itemsFile = In.readItem(Core.itemsFile);
				JSONArray items = itemsFile.getJSONArray("Items");
				items.put(id);
				itemsFile.put("Items", items);
				print(Core.itemsFile, itemsFile.toString());
				return true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public static boolean appendSoldItemsFile(String id){
		if(!Core.soldItemsFile.exists()){
			JSONArray items = new JSONArray().put(id);
			JSONObject itemsFile = new JSONObject().put("Items", items);
			try {
				print(Core.soldItemsFile, itemsFile.toString());
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			try {
				JSONObject itemsFile = In.readItem(Core.soldItemsFile);
				JSONArray items = itemsFile.getJSONArray("Items");
				items.put(id);
				itemsFile.put("Items", items);
				Core.soldItemsFile.delete();
				print(Core.soldItemsFile, itemsFile.toString());
				return true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/*
	 * This function is in charge of saving both Items and SoldItems. It checks to see if soldPrice == Core.saveItemCode
	 * if it is it will save the information as an Item and anything else as a SoldItem. I.e use Core.saveItemCode if you
	 * intend to save an Item and not a SoldItem. - John <3
	 */
	public static boolean saveItem(String id, String name, String desc, String paidDate, String soldDate, int paidPrice, int soldPrice, String buyerName, String notes){
		//Saving Item
		if(soldPrice == 20000000 || soldDate == "00/00/0000" || notes == "HERPDERP" || buyerName == "DERPHERP"){
			File save = new File(Core.itemsDir.toString() + "/" + id + ".json");
			if(save.exists())
				save.delete();
			try{
				JSONObject item = new JSONObject().put("id", id).put("name", name).put("desc", desc).put("paidDate", paidDate).put("paidPrice", paidPrice).put("price", soldPrice);
				print(save, item.toString());
				JSONObject itemsFile = In.readItem(Core.itemsFile);
				JSONArray items = itemsFile.getJSONArray("Items");
				List<String> derps = new ArrayList<String>();
				for(int i = 0; i < items.length(); i++){
					derps.add(items.getString(i));
				}
				if(!derps.contains(id)){
					appendItemsFile(id);
				}
				return true;
			}catch(IOException e){
				return false;
			}
		//Saving SoldItem
		}else{
			File save = new File(Core.soldItemsDir.toString() + "/" + id + ".json");
			if(save.exists())
				save.delete();
			try{
				JSONObject item = new JSONObject().put("id", id).put("name", name).put("desc", desc).put("paidDate", paidDate).put("soldDate", soldDate)
						.put("paidPrice", paidPrice).put("soldPrice", soldPrice).put("notes", notes).put("buyerName", buyerName);
				print(save, item.toString());
				JSONObject soldItemsFile = In.readItem(Core.soldItemsFile);
				JSONArray soldItems = soldItemsFile.getJSONArray("Items");
				List<String> derps = new ArrayList<String>();
				for(int i = 0; i < soldItems.length(); i++){
					derps.add(soldItems.getString(i));
				}
				if(!derps.contains(id)){
					appendSoldItemsFile(id);
				}
				return true;
			}catch(IOException e){
				return false;
			}
		}
	}
	
	/*
	 * Written to save the settings file. Returns true on success false on failure
	 */
	public static boolean saveSettings(){
		JSONObject settings = new JSONObject();
		try{
			print(Core.settingsFile, settings.toString());
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}catch(JSONException e){
			e.printStackTrace();
			return false;
		}
	}
}
