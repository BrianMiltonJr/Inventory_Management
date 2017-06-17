package com.johnwillikers.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class In {
	
	public static JSONObject readItem(File file) throws JSONException, IOException{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		JSONObject item = new JSONObject(br.readLine());
		br.close();
		return item;
	}
}
