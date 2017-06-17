package com.johnwillikers.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;
import com.johnwillikers.gui.ItemTable;

public class In {
	
	public static JSONObject readItem(File file) throws JSONException, IOException{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		JSONObject item = new JSONObject(br.readLine());
		br.close();
		return item;
	}
	
	public static void getItems(String item){
		try {
			JSONObject itemDetailsRaw = In.readItem(new File(Core.itemsDir + item + ".json"));
			Object [] itemDetails = {itemDetailsRaw.get("id"), itemDetailsRaw.get("name"), itemDetailsRaw.get("paidDate"), itemDetailsRaw.get("desc")};
			int index = ItemTable.itemsData.length;
			ItemTable.itemsData[index] = itemDetails;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
