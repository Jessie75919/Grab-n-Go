package _03_Product.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _01_Store_register.model.StoreBean;
import _02_Store_login.model.StoreLoginServiceDB;

public class ProductDAO implements ProductInterface{
	private DataSource ds = null;

	public ProductDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
			
		} catch (NamingException e) {
			System.out.println("JNDI gets wrong ");
			e.printStackTrace();
		}
	}

	@Override
	public int insertProduct(Product prod, InputStream is) {
		int result = 0;
		System.out.println("------------------------------------");
		System.out.println(prod.toString());
		
		String sql = "insert into product "
				+ "values (null,?,?,?,?,?,?,?)";
		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 0;
			System.out.println("------------------------------------");
			pst.setInt(++i, prod.getRest_id());
			pst.setString(++i, prod.getType_name());
			pst.setString(++i, prod.getProd_name());
			pst.setInt(++i, prod.getProd_price());
			pst.setString(++i, prod.getProd_desc());
			pst.setBlob(++i, is);
			pst.setString(++i, prod.getProd_filename());
			

			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(prod.getProd_name() + " insert successfully ");
			} else
				System.out.println(prod.getProd_name() + " gets wrong ");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("I am a SQLException !");
		}
		return result;
	
	}

	@Override
	public int updateProduct(Product prod, InputStream is) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(int prod_id,int rest_id) {
		String sql = "delete from product where prod_id=? and rest_id = ?";
		int result = -1;

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1,prod_id);
			pst.setInt(2,rest_id);
			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(rest_id + ": 刪除成功");
			} else {
				System.out.println(rest_id + ": 刪除失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Product queryAllProduct(int rest_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> queryProductByType(int rest_id, String typeName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
