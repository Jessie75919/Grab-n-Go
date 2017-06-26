package _01_Store_register.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _02_Store_login.model.StoreLoginServiceDB;

public class StoreBeanDAO {
	
	private List<StoreBean> storeList;
	private DataSource ds = null;
	StoreLoginServiceDB slsdb;

	public StoreBeanDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
			slsdb = new StoreLoginServiceDB();
			storeList = slsdb.getAllStoreList();

		} catch (NamingException e) {
			System.out.println("JNDI gets wrong ");
			e.printStackTrace();
		}
	}
	
	/*---------------------------------------------------------------------------------
	 * */

	synchronized public boolean isUserExists(String username) {
		boolean exist = false;
//		System.out.println(username);
		for (StoreBean sb : storeList) {
			System.out.println(sb.getRest_username());
			if (sb.getRest_username().equals(username.trim()))
				exist = true;
		}
		return exist;
	}
	
	/*---------------------------------------------------------------------------------
	 * */

	synchronized public int insertShopData(StoreBean sb) {
		int result = 0;
		System.out.println("------------------------------------");
		System.out.println(sb.toString());
		System.out.println("owner = "+sb.getRest_owner());
		
		String sql = "insert into restaurant "+
				"(rest_id,rest_type,rest_name,rest_branch,rest_address,rest_phone,rest_owner,rest_email,rest_username,rest_password,rest_url,rest_longitude,rest_latitude,rest_validate)"
				+ "values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			System.out.println("------------------------------------");
			pst.setString(++i, sb.getRest_type());
				System.out.println("getRest_type() = "+sb.getRest_type());
			pst.setString(++i, sb.getRest_name());
				System.out.println("getRest_name() = "+sb.getRest_name());
			pst.setString(++i, sb.getRest_branch());
				System.out.println(sb.getRest_branch());
			pst.setString(++i, sb.getRest_address());
			pst.setString(++i, sb.getRest_phone());
			pst.setString(++i, sb.getRest_owner());
			System.out.println(sb.getRest_owner());
			pst.setString(++i, sb.getRest_email());
			pst.setString(++i, sb.getRest_username());
			String pwEncryped = GlobalService.encryptString(sb.getRest_password());
			pst.setString(++i, GlobalService.getMD5Endocing(pwEncryped));
			pst.setString(++i, sb.getRest_url());
			pst.setDouble(++i, sb.getRest_longitude()); 
			pst.setDouble(++i, sb.getRest_latitude());
			pst.setBoolean(++i, false);		//rest_validate於註冊完成時應為未驗證

			result = pst.executeUpdate();

			if (result == 1) {
				System.out.println(sb.getRest_name() + " insert successfully ");
				sb.setRest_password(GlobalService.getMD5Endocing(pwEncryped));
				storeList.add(sb);
			} else
				System.out.println(sb.getRest_name() + " gets wrong ");

		} catch (SQLException e) {
			System.out.println("I am a SQLException !");
			e.printStackTrace();
		}
		return result;
	}

	/*---------------------------------------------------------------------------
	 * the function doesn't check the validation of data. assume that the data
	 * which been input are correct.
	 */
	public int updateShopData(StoreBean sb, InputStream banner, InputStream logo, InputStream cover, long bannerSize,
			long logoSize, long coverSize) {

		int result = 0;
		
		String sql = "update restaurant set rest_address=?,rest_phone=?,rest_email=? "
				+ ",rest_password =?,rest_url= ?, rest_mainbanner =?,rest_logo=?,rest_coverimage=? where rest_username = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			pst.setString(++i, sb.getRest_address());
			pst.setString(++i, sb.getRest_phone());
			pst.setString(++i, sb.getRest_email());
			String pwEncryped = GlobalService.encryptString(sb.getRest_password());
			pst.setString(++i, GlobalService.getMD5Endocing(pwEncryped));
			pst.setString(++i, sb.getRest_url());
			pst.setBinaryStream(++i, banner, bannerSize);
			pst.setBinaryStream(++i, logo, logoSize);
			pst.setBinaryStream(++i, cover, coverSize);
			pst.setString(++i, sb.getRest_username());

			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(sb.getRest_username() + " update successfully ");
			} else
				System.out.println(sb.getRest_username() + " gets wrong ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	};
	
	
	public int updateShopDataNoImg(StoreBean sb) {
		
		int result = 0;
		
		String sql = "update restaurant set rest_address=?,rest_phone=?,rest_email=? "
				+ ",rest_password =?,rest_url= ? where rest_username = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			pst.setString(++i, sb.getRest_address());
			pst.setString(++i, sb.getRest_phone());
			pst.setString(++i, sb.getRest_email());
			String pwEncryped = GlobalService.encryptString(sb.getRest_password());
			pst.setString(++i, GlobalService.getMD5Endocing(pwEncryped));
			pst.setString(++i, sb.getRest_url());
			pst.setString(++i, sb.getRest_username());
			
			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(sb.getRest_username() + " update successfully ");
			} else
				System.out.println(sb.getRest_username() + " gets wrong ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	};
	
	
	
	
	/* ----------------------------------------------------------------------------------------
	 *  Search by name 
	 * */
	public List<StoreBean> getStoreByName(String rest_nameInput){
		
		String sql = "select * from restaurant where rest_name = ? " ;
		List<StoreBean> listStore = new ArrayList<>();
		
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql);
				) {
			pst.setString(1, rest_nameInput.trim());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				int rest_id = rs.getInt("rest_id");
				String rest_type = rs.getString("rest_type");
				String rest_name = rs.getString("rest_name");
				String rest_branch = rs.getString("rest_branch");
				String rest_address = rs.getString("rest_address");
				String rest_phone = rs.getString("rest_phone");
				String rest_owner = rs.getString("rest_owner");
				String rest_email = rs.getString("rest_email");
				String rest_username = rs.getString("rest_username");
				String rest_password = rs.getString("rest_password");
				String rest_url = rs.getString("rest_url");
				double rest_longitude = rs.getDouble("rest_longitude");
				double rest_latitude = rs.getDouble("rest_latitude");
				Blob rest_mainbanner = rs.getBlob("rest_mainbanner");
				Blob rest_logo = rs.getBlob("rest_logo");
				Blob rest_coverimage = rs.getBlob("rest_coverimage");
			
				StoreBean sb = new StoreBean(
						rest_id,rest_type,rest_name,rest_branch,rest_address,
						rest_phone,rest_owner,rest_email,rest_username,rest_password,
						rest_url,rest_longitude,rest_latitude,rest_mainbanner,rest_logo,
						rest_coverimage);
				
				listStore.add(sb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStore;
	}

	public int insertShopData(StoreBean sb, InputStream banner, InputStream logo, InputStream cover, long bannerSize,
			long logoSize, long coverSize) { 
		int result = 0;
		System.out.println("------------------------------------");
		System.out.println(sb.toString());
		System.out.println("owner = "+sb.getRest_owner());
		
		String sql = "insert into restaurant "+
				"(rest_id,rest_type,rest_name,rest_branch,rest_address,rest_phone,rest_owner,"
				+ "rest_email,rest_username,rest_password,rest_url,rest_longitude,rest_latitude,"
				+ "rest_mainbanner,rest_logo,rest_coverimage,rest_validate)"
				+ "values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			System.out.println("------------------------------------");
			pst.setString(++i, sb.getRest_type());
				System.out.println("getRest_type() = "+sb.getRest_type());
			pst.setString(++i, sb.getRest_name());
				System.out.println("getRest_name() = "+sb.getRest_name());
			pst.setString(++i, sb.getRest_branch());
				System.out.println(sb.getRest_branch());
			pst.setString(++i, sb.getRest_address());
			pst.setString(++i, sb.getRest_phone());
			pst.setString(++i, sb.getRest_owner());
			System.out.println(sb.getRest_owner());
			pst.setString(++i, sb.getRest_email());
			pst.setString(++i, sb.getRest_username());
			String pwEncryped = GlobalService.encryptString(sb.getRest_password());
			pst.setString(++i, GlobalService.getMD5Endocing(pwEncryped));
			pst.setString(++i, sb.getRest_url());
			pst.setDouble(++i, sb.getRest_longitude()); 
			pst.setDouble(++i, sb.getRest_latitude());
			pst.setBinaryStream(++i, banner, bannerSize);
			pst.setBinaryStream(++i, logo, logoSize);
			pst.setBinaryStream(++i, cover, coverSize);
			pst.setBoolean(++i, false);		//rest_validate於註冊完成時應為未驗證

			result = pst.executeUpdate();

			if (result == 1) {
				System.out.println(sb.getRest_name() + " insert successfully ");
				sb.setRest_password(GlobalService.getMD5Endocing(pwEncryped));
				storeList.add(sb);
			} else
				System.out.println(sb.getRest_name() + " gets wrong ");

		} catch (SQLException e) {
			System.out.println("I am a SQLException !");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<StoreBean> getNameBranchLogoValidate(String rest_username){
		String sql = "SELECT rest_name, rest_branch, rest_logo, rest_validate "
				+ " FROM restaurant WHERE rest_username = ?" ;
		List<StoreBean> listStore = new ArrayList<>();
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement pst = con.prepareStatement(sql);
		) {
			pst.setString(1, rest_username);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String rest_name = rs.getString("rest_name");
				System.out.println(rest_name);
				String rest_branch = rs.getString("rest_branch");
				Blob rest_logo = rs.getBlob("rest_logo");
				boolean rest_validate = rs.getBoolean("rest_validate");
			
				StoreBean sb = new StoreBean(rest_name,rest_branch,rest_logo,rest_validate);
				
				listStore.add(sb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStore;
	}
	
	
	/* ----------------------------------------------------------------------------------------
	 *  Search store with user's location
	 * */
	public List<StoreBean> getStoreFromUser(double lat,double lng){
		
		String sql = "CALL get_Rest(?,?);";
				
		List<StoreBean> listStore = new ArrayList<>();
		System.out.println(lat+ "  " +lng);
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql);
				) {
			pst.setDouble(1, lat);
			pst.setDouble(2, lng);
			Statement stmt = con.createStatement();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				int rest_id = rs.getInt(1);
				System.out.println("rest_id = " + rest_id);
				String rest_type = rs.getString("a.rest_type");
				String rest_name = rs.getString("rest_name");
				System.out.println("rest_name = " + rest_name);
				String rest_branch = rs.getString("rest_branch");
				String rest_address = rs.getString("rest_address");
				String rest_phone = rs.getString("rest_phone");
				String rest_owner = rs.getString("rest_owner");
				String rest_email = rs.getString("rest_email");
				String rest_username = rs.getString("rest_username");
				String rest_password = rs.getString("rest_password");
				String rest_url = rs.getString("rest_url");
				double rest_longitude = rs.getDouble("rest_longitude");
				double rest_latitude = rs.getDouble("rest_latitude");
				Blob rest_mainbanner = rs.getBlob("rest_mainbanner");
				Blob rest_logo = rs.getBlob("rest_logo");
				Blob rest_coverimage = rs.getBlob("rest_coverimage");
				double distance = Math.round(rs.getDouble("distance_in_km")*1000.0); // toMeter
				System.out.println(distance);
			
				StoreBean sb = new StoreBean(
						rest_id,rest_type,rest_name,rest_branch,rest_address,
						rest_phone,rest_owner,rest_email,rest_username,rest_password,
						rest_url,rest_longitude,rest_latitude,rest_mainbanner,rest_logo,
						rest_coverimage);
				listStore.add(sb);
				
				for(StoreBean sbn : listStore){
					System.out.println( "in StoreBeanDAO : "+sbn);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStore;
	}
	
	
}