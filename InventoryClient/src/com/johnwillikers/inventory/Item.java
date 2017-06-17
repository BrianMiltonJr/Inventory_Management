package com.johnwillikers.inventory;

import com.johnwillikers.Core;
import com.johnwillikers.io.Out;

public class Item {
	
	public String id;
	public String name;
	public String desc;
	public String paidDate;
	public int paidPrice;
	
	public Item(String id, String name, String desc, String paidDate ,int paidPrice){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.paidDate = paidDate;
		this.paidPrice = paidPrice;
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
		Out.saveItem(id, name, desc, paidDate, Core.saveItemCodeString, paidPrice, Core.saveItemCode);
	}
	
	public void destroy() throws Throwable{
		this.finalize();
	}
}
