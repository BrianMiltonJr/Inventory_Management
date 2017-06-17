package com.johnwillikers.inventory;

import com.johnwillikers.io.Out;

public class SoldItem {
	
	public String id;
	public String name;
	public String desc;
	public String paidDate;
	public String soldDate;
	public int paidPrice;
	public int soldPrice;
	
	public SoldItem(String id, String name, String desc, String paidDate, String soldDate, int paidPrice, int soldPrice){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.paidDate = paidDate;
		this.soldDate = soldDate;
		this.paidPrice = paidPrice;
		this.soldPrice = soldPrice;
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
	
	public void saveItem(){
		Out.saveItem(id, name, desc, paidDate, soldDate ,paidPrice, soldPrice);
	}
	
	public void destroy(){
		this.destroy();
	}
	
}
