package _03_Product.model;

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

public class ProductTypeDAO {
	private DataSource ds = null;

	public ProductTypeDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);

		} catch (NamingException e) {
			System.out.println("JNDI gets wrong ");
			e.printStackTrace();
		}
	}

	public void insertProductType(ProductType type) {

		String sql = "insert into product_type values(?,?)";
		int result = -1;

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, type.getProd_typeName());
			pst.setString(2, type.getRest_name());
			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(type + " : 新增成功");
			} else {
				System.out.println(type + " : 新增失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteProductType(String typeName) {

		String sql = "delete from product_type where type_name=?";
		int result = -1;

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1,typeName);
			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(typeName + ": 刪除成功");
			} else {
				System.out.println(typeName + ": 刪除失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> queryAllProductType(String restName) {
		List<String> ptList = new ArrayList<String>();
		String sql = "select type_name from product_type where rest_name=?";

		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1,restName);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String typeName = rs.getString(1);
//				System.out.println("in queryAllProductType = "+typeName);
				ptList.add(typeName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ptList;
	}
	
	public int updateAllProductType(String typeName) {
		
		String sql = "update product_type set type_name where type_name=?";
		int result = -1;
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1,typeName);
			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(typeName + ": 修改成功");
			} else {
				System.out.println(typeName + ": 修改失敗");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
