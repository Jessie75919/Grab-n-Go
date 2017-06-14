package _02_Store_login.model;

import java.sql.Blob;
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
import _01_Store_register.model.StoreBean;

public class StoreLoginServiceDB {

	static private List<StoreBean> storeList = new ArrayList<StoreBean>();
	private DataSource ds = null;

	public StoreLoginServiceDB() {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
			if (storeList.isEmpty()) {
				populateStoreList();
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void populateStoreList() {
		String sql = "select * from restaurant";

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int rest_id = rs.getInt("rest_id");
				String rest_typeId = rs.getString("rest_typeId");
				String rest_name = rs.getString("rest_name");
				String rest_branch = rs.getString("rest_branch");
				String rest_address = rs.getString("rest_address");
				String rest_phone = rs.getString("rest_phone");
				String rest_owner = rs.getString("rest_owner");
				String rest_email = rs.getString("rest_email");
				String rest_username = rs.getString("rest_username");
				String rest_passward = rs.getString("rest_passward");
				String rest_url = rs.getString("rest_url");
				float rest_longitude = rs.getFloat("rest_longitude");
				float rest_latitude = rs.getFloat("rest_latitude");
				Blob rest_mainbanner = rs.getBlob("rest_mainbanner");
				Blob rest_logo = rs.getBlob("rest_logo");
				Blob rest_coverimage = rs.getBlob("rest_coverimage");

				StoreBean sb = new StoreBean(rest_id, rest_typeId, rest_name, rest_branch, rest_address, rest_phone,
						rest_owner, rest_email, rest_username, rest_passward, rest_url, rest_longitude, rest_latitude,
						rest_mainbanner, rest_logo, rest_coverimage);

				storeList.add(sb);

			}

		} catch (Exception e) {
			System.out.println("populateStoreList() gots wrong");
			System.out.println(e.getMessage());;
		}

	}

	public List<StoreBean> getAllStoreList() {
		return storeList;
	}

	
	public StoreBean checkPW(String userId, String password) {
		StoreBean sb = null;
		String sql = "select * from restaurant where rest_username = ? and rest_passward = ? ";

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int rest_id = rs.getInt("rest_id");
				String rest_typeId = rs.getString("rest_typeId");
				String rest_name = rs.getString("rest_name");
				String rest_branch = rs.getString("rest_branch");
				String rest_address = rs.getString("rest_address");
				String rest_phone = rs.getString("rest_phone");
				String rest_owner = rs.getString("rest_owner");
				String rest_email = rs.getString("rest_email");
				String rest_username = rs.getString("rest_username");
				String rest_passward = rs.getString("rest_passward");
				String rest_url = rs.getString("rest_url");
				float rest_longitude = rs.getFloat("rest_longitude");
				float rest_latitude = rs.getFloat("rest_latitude");
				Blob rest_mainbanner = rs.getBlob("rest_mainbanner");
				Blob rest_logo = rs.getBlob("rest_logo");
				Blob rest_coverimage = rs.getBlob("rest_coverimage");

				sb = new StoreBean(rest_id, rest_typeId, rest_name, rest_branch, rest_address, rest_phone, rest_owner,
						rest_email, rest_username, rest_passward, rest_url, rest_longitude, rest_latitude,
						rest_mainbanner, rest_logo, rest_coverimage);

			}
		} catch (Exception e) {
			System.out.println("checkPW() gots wrong");
			System.out.println(e.getMessage());;
		}
		return sb;

	}

}
