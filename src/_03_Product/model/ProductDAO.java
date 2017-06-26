package _03_Product.model;

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

public class ProductDAO implements ProductInterface{
	private DataSource ds = null;
	
	private int rest_id;
	private String typeName;
	

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

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
			System.out.println("GOOd");

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
	public int updateProduct(Product prod, InputStream is ,int mode) {
		String sql = "";
		if(mode==1){
			sql = "update product set prod_name=?,type_name=?"
					+ ",prod_price=?,prod_desc=?,prod_filename=?,prod_img=? "
					+ "where prod_id=? ";
		}else{
			sql = "update product set prod_name=?,type_name=?"
					+ ",prod_price=?,prod_desc=? "
					+ "where prod_id=? ";
		}
		
		int result = -1;

		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			int i=0;
			pst.setString(++i,prod.getProd_name());
			pst.setString(++i,prod.getType_name());
			pst.setInt(++i,prod.getProd_price());
			pst.setString(++i,prod.getProd_desc());
			if(mode==1){
				pst.setString(++i, prod.getProd_filename());
				pst.setBlob(++i, is);
			}
			pst.setInt(++i, prod.getProd_id());
			
			System.out.println("in updateProduct");
			
				result = pst.executeUpdate();
				
			if (result == 1) {
				System.out.println(prod.getProd_name() + ": 更新成功");
			} else {
				System.out.println(prod.getProd_name() + ": 更新失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteProduct(String prod_name,String type_name) {
		String sql = "delete from product where prod_name=? and type_name = ?";
		int result = -1;

		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1,prod_name);
			pst.setString(2,type_name);
			result = pst.executeUpdate();
			if (result == 1) {
				System.out.println(prod_name + ": 刪除成功");
			} else {
				System.out.println(prod_name + ": 刪除失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Product> getProductByType(){
		List<Product> ptList = queryProducts(rest_id,typeName);
		return ptList;
	}

	@Override
	public List<Product> queryProducts(int rest_id, String typeName) {
		List<Product> ptList = new ArrayList<>();
		String sql = "";
		
		if(typeName.equals("no")){
			 sql = "select * from product where rest_id=? order by type_name";
		}else{
			 sql = "select * from product where rest_id=? and type_name=? order by type_name";
		}

		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1,rest_id);
			if(!typeName.equals("no")){
				pst.setString(2,typeName);
			}
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				int prodId = rs.getInt(1);
				int restId = rs.getInt(2);
				String typeNameA = rs.getString(3);
				String prodName = rs.getString(4);
				int prodPrice = rs.getInt(5);
				String prodDesc = rs.getString(6);
				Blob prodImg = rs.getBlob(7);
				String fileNmae = rs.getString(8);
				
				Product pro = new Product(prodId, restId, typeNameA, prodName, 
						prodPrice, prodDesc, prodImg, fileNmae);
				ptList.add(pro);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ptList;
	}
	
	
}
