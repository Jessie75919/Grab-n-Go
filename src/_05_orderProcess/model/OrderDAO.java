package _05_orderProcess.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public Collection<OrderBean> getOrdersDate(String rest_name, 
			Date now, Date monthsAgo) {
		Collection<OrderBean> coll = new ArrayList<>();
		try (Connection con = ds.getConnection();) {
			String sql = "SELECT Date_format(a.ord_time, '%Y-%c-%d')"
					+ " FROM order01 a JOIN restaurant b ON a.rest_id = b.rest_id"
					+ " WHERE b.rest_name = ? WHERE a.ord_time BETWEEN ? AND ?"
					+ " WHERE a.ord_status = 'paid' ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, rest_name);
			stmt.setDate(2, now);
			stmt.setDate(3, monthsAgo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				coll.add(ob);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return coll;
	}
	
	public Collection<OrderBean> getStoreOrders(){
		Connection conn = null;
		Collection<OrderBean> coll = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT a.ord_time, a.ord_pickuptime, a.m_pickupname, a.ord_id, a.ord_totalPrice, a.ord_status"
					+ "FROM order01 a JOIN restaurant b ON a.rest_id = b.rest_id"
					+ "WHERE b.rest_username = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, restUsername);
			System.out.println("Hello");
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrd_time(rs.getTimestamp("ord_time"));
				ob.setOrd_pickuptime(rs.getTimestamp("ord_pickuptime"));
				ob.setM_pickupname(rs.getString("m_pickuptime"));
				ob.setOrd_id(rs.getInt("ord_id"));
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

}
