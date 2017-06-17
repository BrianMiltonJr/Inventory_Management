package com.johnwillikers.inventory;

import com.johnwillikers.Core;
import com.johnwillikers.io.Out;

public class Item {
	
	public String id;
	public String name;
	public String desc;
	public int paidPrice;
	
	public Item(String id, String name, String desc, int paidPrice){
		this.id = id;
		this.name = name;
		this.desc = desc;
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
	
	public int getPaid(){
		return this.paidPrice;
	}
	
	public void saveItem(){
		Out.saveItem(id, name, desc, paidPrice, Core.saveItemCode);
	}
	
	public void destroy(){
		this.destroy();
	}
}
