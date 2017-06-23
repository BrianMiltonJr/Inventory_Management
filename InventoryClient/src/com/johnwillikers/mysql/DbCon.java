package com.johnwillikers.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DbCon {

	public String dB;
	public String tableName;
	public String host;
	public String user;
	public String password;
	
	public DbCon(String dB, String tableName, String host, String user, String password){
		this.dB = dB;
		this.tableName = tableName;
		this.host = host;
		this.user = user;
		this.password = password;
	}
	
	public String[] getItem(String id){
		try{
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM items WHERE id=?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			String[] items = {"derp", "derp", "derp", "derp", "derp", "derp"};
			while(rs.next()){
				items[0] = rs.getString(1);
				items[1] = rs.getString(2);
				items[2] = String.valueOf(rs.getFloat(3)); 
				items[3] = rs.getString(4);
				items[4] = rs.getString(5);
				items[5] = String.valueOf(rs.getFloat(6));
			}
			System.out.println(items.toString());
			rs.close();
			stmt.close();
			con.close();
			return items;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean createItem(String id, String name, float paidPrice, String paidDate, String desc, float price){
		try{
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO " + this.tableName + " (id, name, paid, buy_date, description, price)" + " VALUES(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, id);
			stmt.setString(2, name);
			stmt.setFloat(3, paidPrice);
			stmt.setString(4, paidDate);
			stmt.setString(5, desc);
			stmt.setFloat(6, price);
			stmt.execute();
			stmt.close();
			con.close();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean createSoldItem(String id, String name, float paidPrice, String paidDate, String desc, float price, String buyerName, String soldDate, String notes){
		try{
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO " + this.tableName + " (id, name, paid, buy_date, description, price, buyers_name, sold_date, notes)" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, id);
			stmt.setString(2, name);
			stmt.setFloat(3, paidPrice);
			stmt.setString(4, paidDate);
			stmt.setString(5, desc);
			stmt.setFloat(6, price);
			stmt.setString(7, buyerName);
			stmt.setString(8, soldDate);
			stmt.setString(9, notes);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			deleteItem(id, "items");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteItem(String id, String table){
		try{
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM " + table + " WHERE id = ?");
			stmt.setString(1, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateItem(String id, String name, float paid, String date, String desc, float price){
		try{
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE " + this.tableName + " SET id=?, name=?, paid=?, buy_date=?, description=?, price=? WHERE id=?");
			stmt.setString(1, id);
			stmt.setString(2, name);
			stmt.setFloat(3, paid);
			stmt.setString(4, date);
			stmt.setString(5, desc);
			stmt.setFloat(6, price);
			stmt.setString(7, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateSoldItem(String id, String name, float paid, String date, String desc, float price, String buyerName, String soldDate, String notes){
		try{
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE " + this.tableName + " SET id=?, name=?, paid=?, buy_date=?, description=?, price=?, buyer_name=?"
					+ ", sold_date=?, notes=? WHERE id=?");
			stmt.setString(1, id);
			stmt.setString(2, name);
			stmt.setFloat(3, paid);
			stmt.setString(4, date);
			stmt.setString(5, desc);
			stmt.setFloat(6, price);
			stmt.setString(7, buyerName);
			stmt.setString(8, soldDate);
			stmt.setString(9, notes);
			stmt.setString(10, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Object> retrieveItems(){
		try{
			List<Object> items = new ArrayList<Object>();
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tableName);
			while(rs.next()){
				items.add(rs.getString(1));
				items.add(rs.getString(2));
				items.add(rs.getFloat(3));
				items.add(rs.getDate(4));
				items.add(rs.getString(5));
				items.add(rs.getFloat(6));
			}
			rs.close();
			stmt.close();
			con.close();
			System.out.println("Sending items List<Object>");
			return items;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Sending null List<Object>");
			return null;
		}
	}
	
	public List<Object> retrieveSoldItems(){
		try{
			List<Object> items = new ArrayList<Object>();
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(this.user);
			dataSource.setPassword(this.password);
			dataSource.setServerName(this.host);
			dataSource.setDatabaseName(this.dB);
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tableName);
			while(rs.next()){
				items.add(rs.getString(1));
				items.add(rs.getString(2));
				items.add(rs.getFloat(3));
				items.add(rs.getDate(4));
				items.add(rs.getString(5));
				items.add(rs.getFloat(6));
				items.add(rs.getString(7));
				items.add(rs.getString(8));
				items.add(rs.getString(9));
			}
			rs.close();
			stmt.close();
			con.close();
			System.out.println("Sending sold_items List<Object>");
			return items;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Sending null List<Object>");
			return null;
		}
	}
	
}
