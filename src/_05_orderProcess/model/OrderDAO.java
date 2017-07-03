package _05_orderProcess.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class OrderDAO {

	private DataSource ds;
	private String username;
	private String restUsername;
	private int ord_id;
	
	
	
	public int getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setRestUsername(String restUsername){
		this.restUsername = restUsername;
	}

	public OrderDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	
	public int insertOrder(OrderBean ob){
		int n = -1;
		String sqlOrder = "insert into order01 values (null,?,?,?,?,?,?,?)";
		String sqlOrderItem = "insert into order_item values (null,?,?,?,?,?,?,?)";
		try(
				Connection con = ds.getConnection();
				PreparedStatement pst1 = con.prepareStatement(sqlOrder,Statement.RETURN_GENERATED_KEYS);
				
		){
			ResultSet rsGenerateKeys = null ;
			try{
				con.setAutoCommit(false);
				System.out.println(ob.getM_username());
				System.out.println(ob.getM_pickupname());
				System.out.println(ob.getOrd_time());
				System.out.println(ob.getOrd_pickuptime());
				System.out.println(ob.getRest_id());
				System.out.println(ob.getOrd_totalPrice());
				System.out.println(ob.getOrd_status());
			//==============================================
				pst1.setString(1, ob.getM_username());
				pst1.setString(2, ob.getM_pickupname());
				pst1.setTimestamp(3, ob.getOrd_time());
				pst1.setTimestamp(4, ob.getOrd_pickuptime());
				pst1.setInt(5, ob.getRest_id());
				pst1.setInt(6, ob.getOrd_totalPrice());
				pst1.setString(7, ob.getOrd_status());
				n = pst1.executeUpdate();
				int orderId_Pk = 0; 
				rsGenerateKeys = pst1.getGeneratedKeys();
				if(rsGenerateKeys.next()){
					orderId_Pk = rsGenerateKeys.getInt(1);
				}else{
					throw new SQLException("create fail,no generateKey");
				}
				
				PreparedStatement pst2 = con.prepareStatement(sqlOrderItem);
				int k = 0;
				List<OrderItemBean> list = ob.getItems();
				for(OrderItemBean oib:list){
					pst2.setInt(1	, orderId_Pk);
					pst2.setInt(2	, oib.getProd_id());
					pst2.setString(3, oib.getItem_name());
					pst2.setInt(4, oib.getItem_price());
					pst2.setInt(5, oib.getItem_amount());
					pst2.setString(6, oib.getItem_note());
					pst2.setString(7, ob.getM_username());
					k = pst2.executeUpdate();
					pst2.clearParameters();
				}
				
				con.commit();
				con.setAutoCommit(true);
				if(k==1){
					n = 1;
				}
			}catch (Exception e) {
				System.out.println("Rollback");
				if(con!=null){
					con.rollback();
				}
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	

	public Collection<OrderBean> getMemberOrders() {
		Connection conn = null;
		Collection<OrderBean> coll = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.ord_id, a.ord_time, b.rest_name, a.ord_totalPrice, a.ord_status"
				+ " FROM order01 a JOIN restaurant b ON a.rest_id = b.rest_id"
				+ " WHERE m_username = ?";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrd_id(rs.getInt("ord_id"));
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				ob.setRest_name(rs.getString("rest_name"));
				ob.setOrd_totalPrice(rs.getInt("ord_totalPrice"));
				ob.setOrd_status(rs.getString("ord_status"));

				coll.add(ob);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return coll;
	}
	
	public Collection<OrderBean> getStoreOrdersInPgogress() {
		Collection<OrderBean> coll = new ArrayList<>();
		String sql = " SELECT a.ord_time, a.ord_pickuptime, a.m_pickupname, a.ord_id, a.ord_totalPrice, a.ord_status "
				+ " FROM order01 a INNER JOIN restaurant b ON a.rest_id = b.rest_id " 
				+ " WHERE a.ord_status = 'inprogress' and b.rest_username = ? ";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setString(1, restUsername);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				ob.setOrd_pickuptime(rs.getTimestamp("ord_pickuptime"));
				ob.setM_pickupname(rs.getString("m_pickupname"));
				ob.setOrd_id(rs.getInt("ord_id"));
				ob.setOrd_totalPrice(rs.getInt("ord_totalPrice"));
				ob.setOrd_status(rs.getString("ord_status"));

				coll.add(ob);
			}
			System.out.println(rs);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return coll;
	}
	
	public Collection<OrderBean> getStoreOrdersUnpaid() {
		Collection<OrderBean> coll = new ArrayList<>();
		String sql = " SELECT a.ord_time, a.ord_pickuptime, a.m_pickupname, a.ord_id, a.ord_totalPrice, a.ord_status "
				+ " FROM order01 a INNER JOIN restaurant b ON a.rest_id = b.rest_id " 
				+ " WHERE a.ord_status = 'unpaid' and b.rest_username = ? ";
		try (Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setString(1, restUsername);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				ob.setOrd_pickuptime(rs.getTimestamp("ord_pickuptime"));
				ob.setM_pickupname(rs.getString("m_pickupname"));
				ob.setOrd_id(rs.getInt("ord_id"));
				ob.setOrd_totalPrice(rs.getInt("ord_totalPrice"));
				ob.setOrd_status(rs.getString("ord_status"));

				coll.add(ob);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return coll;
	}
	
	public Collection<OrderBean> getStoreOrdersPaid() {
		Collection<OrderBean> coll = new ArrayList<>();
		String sql = " SELECT a.ord_time, a.ord_pickuptime, a.m_pickupname, a.ord_id, a.ord_totalPrice, a.ord_status "
				+ " FROM order01 a INNER JOIN restaurant b ON a.rest_id = b.rest_id " 
				+ " WHERE a.ord_status = 'paid' and b.rest_username = ? ";
		try (Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			) {
			stmt.setString(1, restUsername);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				ob.setOrd_pickuptime(rs.getTimestamp("ord_pickuptime"));
				ob.setM_pickupname(rs.getString("m_pickupname"));
				ob.setOrd_id(rs.getInt("ord_id"));
				ob.setOrd_totalPrice(rs.getInt("ord_totalPrice"));
				ob.setOrd_status(rs.getString("ord_status"));

				coll.add(ob);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return coll;
	}
	
	
	
	public OrderBean getStoreOrdersById() {
		String sql = " SELECT * from order01  WHERE ord_id = ? ";
		OrderBean ob = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setInt(1, ord_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ob = new OrderBean();
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				ob.setOrd_pickuptime(rs.getTimestamp("ord_pickuptime"));
				ob.setM_pickupname(rs.getString("m_pickupname"));
				ob.setOrd_id(rs.getInt("ord_id"));
				ob.setOrd_totalPrice(rs.getInt("ord_totalPrice"));
				ob.setOrd_status(rs.getString("ord_status"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return ob;
	}

}
