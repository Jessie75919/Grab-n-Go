package _01_App_register.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class RestaurantTypeDAO {
	private DataSource ds = null;
	
	public RestaurantTypeDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			System.out.println("JNDI gets wrong ");
			e.printStackTrace();
		}
	}
	
	public List<String> getRestaurantType() {
		List<String> list = new ArrayList<>();
		try (
			Connection con = ds.getConnection();
		){
			String sql = "Select * from rest_type";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String restType = rs.getString("type_name");
				list.add(restType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	
}
