package _01_Store_register.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		for (StoreBean sb : storeList) {
			if (sb.getRest_username().equals(username.trim()))
				exist = true;
		}
		return exist;
	}
	
	/*---------------------------------------------------------------------------------
	 * */

	synchronized public int insertShopData(StoreBean sb, InputStream banner, InputStream logo, InputStream cover,
			long bannerSize, long logoSize, long coverSize) {
		int result = 0;
		String sql = "insert into restaurant values" + "(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			pst.setString(++i, sb.getRest_typeId());
			pst.setString(++i, sb.getRest_name());
			pst.setString(++i, sb.getRest_branch());
			pst.setString(++i, sb.getRest_address());
			pst.setString(++i, sb.getRest_phone());
			pst.setString(++i, sb.getRest_owner());
			pst.setString(++i, sb.getRest_email());
			pst.setString(++i, sb.getRest_username());
			String pwEncryped = GlobalService.encryptString(sb.getRest_passward());
			pst.setString(++i, GlobalService.getMD5Endocing(pwEncryped));
			pst.setString(++i, sb.getRest_url());
			pst.setFloat(++i, sb.getRest_longitude()); // get values from google
														// api ??
			pst.setFloat(++i, sb.getRest_latitude()); // get values from google
														// api ??
			pst.setBinaryStream(++i, banner, bannerSize);
			pst.setBinaryStream(++i, logo, logoSize);
			pst.setBinaryStream(++i, cover, coverSize);

			result = pst.executeUpdate();

			if (result == 1) {
				System.out.println(sb.getRest_name() + " insert successfully ");
				sb.setRest_passward(GlobalService.getMD5Endocing(pwEncryped));
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
		String sql = "update restaurant set ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			pst.setInt(++i, sb.getRest_id());
			pst.setString(++i, sb.getRest_typeId());
			pst.setString(++i, sb.getRest_name());
			pst.setString(++i, sb.getRest_branch());
			pst.setString(++i, sb.getRest_address());
			pst.setString(++i, sb.getRest_phone());
			pst.setString(++i, sb.getRest_owner());
			pst.setString(++i, sb.getRest_email());
			pst.setString(++i, sb.getRest_username());
			String pwEncryped = GlobalService.encryptString(sb.getRest_passward());
			pst.setString(++i, GlobalService.getMD5Endocing(pwEncryped));
			pst.setString(++i, sb.getRest_url());
			pst.setFloat(++i, sb.getRest_longitude());
				// get values from google api ??
			pst.setFloat(++i, sb.getRest_latitude());
				// get values from google api ??
			pst.setBinaryStream(++i, banner, bannerSize);
			pst.setBinaryStream(++i, logo, logoSize);
			pst.setBinaryStream(++i, cover, coverSize);

			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(sb.getRest_name() + " update successfully ");
			} else
				System.out.println(sb.getRest_name() + " gets wrong ");

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
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()){
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
			
				StoreBean sb = new StoreBean(
						rest_id,rest_typeId,rest_name,rest_branch,rest_address,
						rest_phone,rest_owner,rest_email,rest_username,rest_passward,
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
	
	
}