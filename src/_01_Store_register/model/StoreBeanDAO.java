package _01_Store_register.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _01_Store_login.model.StoreLoginServiceDB;

public class StoreBeanDAO {
	private List<StoreBean> storeList ;
	private DataSource ds = null;
	StoreLoginServiceDB slsdb;
	
	public StoreBeanDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
			storeList = new ArrayList<>();
			storeList = slsdb.getAllStoreList();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized public boolean isUserExists(String username){
		boolean exist = false;
		for(StoreBean sb : storeList){
			if(sb.getRest_username().equals(username.trim())){
				exist = true;
			}
		}
		return exist;
	}
	
	synchronized public int insertShop(StoreBean sb){
		int result = 0;
		String sql = "insert into restaurant values"
				+ "(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(
				Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
			) {
			int i=0;
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
			pst.setFloat(++i, sb.getRest_latitude());
			pst.setBlob(++i, sb.getRest_mainbanner());
			pst.setBlob(++i, sb.getRest_logo());
			pst.setBlob(++i, sb.getRest_coverimage());
			
			result = pst.executeUpdate();
			
			if(result == 1){
				System.out.println( sb.getRest_name() +" insert successfully ");
				sb.setRest_passward( GlobalService.getMD5Endocing(pwEncryped));
				storeList.add(sb);
			}
			else System.out.println( sb.getRest_name() +" gets wrong ");
			
		} catch (SQLException e) {
			System.out.println("I am a SQLException !");
			e.printStackTrace();
		return result;
		
	}
	
	
	
}
