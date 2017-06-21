package _22_searchRest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import _00_init.GlobalService;
import _03_listBooks.model.CompanyBean;

public class RestDAO {
	
	private String foodKind;
	private String storeName;
	private String foodName;
	private String tagName ="";
	
	int count;
	DataSource ds;
	
	public String getFoodKind() {
		return foodKind;
	}

	public void setFoodKind(String foodKind) {
		this.foodKind = foodKind;
	}
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public RestDAO() {
		try {
			Context ctx = new InitialContext();
			//DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Grab_n_Go");
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	//給jsp網頁的下拉選單
	public String getSelectTag() throws SQLException {
		String ans = "";
		Collection<String> list = getRestType();
		ans += "<SELECT name='" + tagName + "'>";
		ans += "<option value='" + "" + "'>" + "請選擇餐廳類別" + "</option>";
		for (String type : list) {
				ans += "<option value='" + type + "'>" + type + "</option>";
			}
				ans += "</SELECT>";
		return ans;
	}
	
	//從restaurant表格取出現有餐廳的餐廳種類
	public Collection<String> getRestType(){
		Connection conn = null;
		Collection<String> list = new ArrayList<>();
		PreparedStatement stmt = null;
		String sql = "";
		try {
			conn = ds.getConnection();
			sql = "SELECT DISTINCT rest_type FROM restaurant";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String rest_type = rs.getString("rest_type");
				list.add(rest_type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public Collection<RestBean> searchRest() {
		Connection conn = null;
		Collection<RestBean> list = new ArrayList<>();
		PreparedStatement stmt = null;
		String sql = "";
		try {
			conn = ds.getConnection();
			//3個欄位排列組合後共以下7種case，對應到各自的sql指令
			switch(count){
			case 1:
				sql = "SELECT * FROM restaurant WHERE rest_type = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, foodKind);
				break;
			case 2:
				sql = "SELECT * FROM restaurant WHERE rest_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, storeName);
				break;
			case 3:
				sql = "SELECT * FROM restaurant WHERE rest_type = ? AND rest_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, foodKind);
				stmt.setString(2, storeName);
				break;
			case 4:
				sql = "SELECT * FROM restaurant a JOIN product b ON a.rest_id = b.rest_id " +
						" WHERE b.prod_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, foodName);
				break;
			case 5:
				sql = "SELECT * FROM restaurant a JOIN product b ON a.rest_id = b.rest_id " +
						" WHERE a.rest_type = ? AND b.prod_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, foodKind);
				stmt.setString(2, foodName);
				break;
			case 6:
				sql = "SELECT * FROM restaurant a JOIN product b ON a.rest_id = b.rest_id " +
						" WHERE a.rest_name = ? AND b.prod_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, storeName);
				stmt.setString(2, foodName);
				break;
			case 7:
				sql = "SELECT * FROM restaurant a JOIN product b ON a.rest_id = b.rest_id " +
				          " WHERE a.rest_type = ? AND a.rest_name = ? AND b.prod_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, foodKind);
				stmt.setString(2, storeName);
				stmt.setString(3, foodName);
				break;
			default:
				conn.close();
				return null;
			}
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int rest_Id = rs.getInt("rest_Id");
				String rest_typeId = rs.getString("rest_type");
				String rest_name = rs.getString("rest_name");
				String rest_branch = rs.getString("rest_branch");
				String rest_address = rs.getString("rest_address");
				String rest_phone = rs.getString("rest_phone");
				
				RestBean rb = new RestBean(rest_Id, rest_typeId, rest_name, rest_branch, rest_address, rest_phone);
				list.add(rb);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}


	
	

}
