package com.johnwillikers.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	/*
	 * This function is in charge of saving both Items and SoldItems. It checks to see if soldPrice == Core.saveItemCode
	 * if it is it will save the information as an Item and anything else as a SoldItem. I.e use Core.saveItemCode if you
	 * intend to save an Item and not a SoldItem. - John <3
	 */
	public static boolean saveItem(String id, String name, String desc, int paidPrice, int soldPrice){
		//Saving Item
		if(soldPrice == 20000000){
			File save = new File(Core.itemsDir.toString() + "/" + id + ".json");
			if(save.exists())
				save.delete();
			try{
				JSONObject item = new JSONObject().put("id", id).put("name", name).put("desc", desc).put("paidPrice", paidPrice);
				print(save, item.toString());
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
				JSONObject item = new JSONObject().put("id", id).put("name", name).put("desc", desc).put("paidPrice", paidPrice).put("soldPrice", "soldPrice");
				print(save, item.toString());
				return true;
			}catch(IOException e){
				return false;
			}
		}
	}
}
