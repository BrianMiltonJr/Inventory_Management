package com.johnwillikers.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;

public class In {
	
	public static JSONObject readItem(File file) throws JSONException, IOException{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		JSONObject item = new JSONObject(br.readLine());
		br.close();
		return item;
	}
	
	public static Object[] getSoldItems(String item){
		Object[] fail = {null};
		try {
			JSONObject itemDetailsRaw = In.readItem(new File(Core.soldItemsDir + item + ".json"));
			Object [] itemDetails = {itemDetailsRaw.get("id"), itemDetailsRaw.get("name"), itemDetailsRaw.get("notes"),
					itemDetailsRaw.get("paidDate"), itemDetailsRaw.get("desc"), itemDetailsRaw.get("soldDate"),
					itemDetailsRaw.getString("buyerName"), itemDetailsRaw.getFloat("paidPrice") ,itemDetailsRaw.getFloat("soldPrice"),
					itemDetailsRaw.getFloat("soldPrice") - itemDetailsRaw.getFloat("paidPrice")};
			return itemDetails;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail;
		}
	}
	
	public static Object[] getItems(String item){
		Object[] fail = {null};
		try {
			JSONObject itemDetailsRaw = In.readItem(new File(Core.itemsDir + item + ".json"));
			Object [] itemDetails = {itemDetailsRaw.get("id"), itemDetailsRaw.get("name"), itemDetailsRaw.get("paidPrice"),
					itemDetailsRaw.get("paidDate"), itemDetailsRaw.get("desc"), itemDetailsRaw.get("price"),
					itemDetailsRaw.getFloat("price") - itemDetailsRaw.getFloat("paidPrice")};
			return itemDetails;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail;
		}
	}
	
	@SuppressWarnings("unused")
	public static void setSettings(){
		if(!Core.settingsFile.exists())
			try {
				JSONObject settings = new JSONObject().put("derp", "herp");
				Out.print(Core.settingsFile, settings.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			JSONObject settings = readItem(Core.settingsFile);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}