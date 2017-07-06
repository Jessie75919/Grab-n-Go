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

public class OrderItemDAO {
	private DataSource ds;
	private int ord_id;
	private String restUsername;
	private String ordPickuptime;

	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}

	public void setRestUsername(String restUsername) {
		this.restUsername = restUsername;
	}
	
	public void setOrdPickuptime(String ordPickuptime){
		this.ordPickuptime = ordPickuptime;
	}

	public OrderItemDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public Collection<OrderItemBean> getOrderItemById() {
		Connection conn = null;
		Collection<OrderItemBean> coll = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM order_item WHERE ord_id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ord_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderItemBean oib = new OrderItemBean();
				oib.setItem_name(rs.getString("item_name"));
				oib.setProd_id(rs.getInt("prod_id"));
				oib.setItem_price(rs.getInt("item_price"));
				oib.setItem_amount(rs.getInt("item_amount"));
				oib.setItem_note(rs.getString("item_note"));

				coll.add(oib);
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

	public List<OrderItemBean> getOrdersItemDataForApp(String rest_name ,String interval) {
		List<OrderItemBean> coll = new ArrayList<>();
		OrderItemBean oib = null;
		String sql1 = " SELECT a.prod_id, a.item_name, SUM(a.item_price), SUM(a.item_amount) ";
		String sql2 = "";
		String sql3 = " FROM order01 b JOIN order_item a ON a.ord_id = b.ord_id "
					+ " JOIN restaurant r ON b.rest_id = r.rest_id "
					+ " WHERE r.rest_name = ? AND b.ord_status = 'paid' "
					+ " GROUP BY a.prod_id ";
		String sql4 = "";
		if (interval.equals("daily")){
			sql2 = " , DATE_FORMAT(b.ord_pickuptime, '%Y-%c-%e') daily ";
			sql4 = " , daily ; ";
		} else if (interval.equals("mobthly")) {
			sql2 = " , DATE_FORMAT(b.ord_pickuptime, '%Y-%c') monthly ";
			sql4 = " , monthly ; ";
		} else if (interval.equals("yearly")) {
			sql2 = " , DATE_FORMAT(b.ord_pickuptime, '%Y') yearly ";
			sql4 = " , yearly ; ";
		}
		String sql = sql1 + sql2 + sql3 + sql4;
		try (
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
		) {
			stmt.setString(1, rest_name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int prod_id = rs.getInt("a.prod_id");
				String item_name = rs.getString("a.item_name");
				int item_price_sum = rs.getInt("SUM(a.item_price)");
				int item_amount_sum = rs.getInt("SUM(a.item_amount)");
				String s = "";
				if (interval.equals("daily")){
					s = rs.getString("daily");
				} else if (interval.equals("mobthly")) {
					s = rs.getString("mobthly");
				} else if (interval.equals("yearly")) {
					s = rs.getString("yearly");
				}
				oib = new OrderItemBean(prod_id, item_name, item_price_sum, item_amount_sum, s);
				coll.add(oib);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return coll;
	}
	
	public List<OrderItemBean> getOrdersRevenueDataForApp(String rest_name ,String interval) {
		List<OrderItemBean> coll = new ArrayList<>();
		OrderItemBean oib = null;
		String sql1 = " SELECT SUM(a.item_price), SUM(a.item_amount) ";
		String sql2 = "";
		String sql3 = " FROM order01 b JOIN order_item a ON a.ord_id = b.ord_id "
					+ " JOIN restaurant r ON b.rest_id = r.rest_id "
					+ " WHERE r.rest_name = ? AND b.ord_status = 'paid' ";
		String sql4 = "";
		if (interval.equals("monthly")){
			sql2 = " , DATE_FORMAT(b.ord_pickuptime, '%e') date_monthly "
					+ " , DATE_FORMAT(b.ord_pickuptime, '%Y-%c') monthly ";
			sql4 = " GROUP BY date_monthly ; ";
		} else if (interval.equals("yearly")) {
			sql2 = " , DATE_FORMAT(b.ord_pickuptime, '%c') month_yearly "
					+ " , DATE_FORMAT(b.ord_pickuptime, '%Y') yearly ";
			sql4 = " GROUP BY month_yearly ; ";
		}
		String sql = sql1 + sql2 + sql3 + sql4;
		try (
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
		) {
			stmt.setString(1, rest_name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int item_price_sum = rs.getInt("SUM(a.item_price)");
				int item_amount_sum = rs.getInt("SUM(a.item_amount)");
				String s = "";
				if (interval.equals("monthly")){
					s = rs.getString("date_monthly") + "/" + rs.getString("monthly");
				} else if (interval.equals("yearly")) {
					s = rs.getString("month_yearly") + "/" + rs.getString("yearly");
				}
				oib = new OrderItemBean(item_price_sum, item_amount_sum, s);
				coll.add(oib);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return coll;
	}

	//
	public Collection<OrderItemBean> getOrderDetailsForStore() {
		Collection<OrderItemBean> coll = new ArrayList();
		// String sql = " SELECT m_pickupname, item_name, prod_id, item_note,
		// item_amount, item_price, b.ord_id "
		// + " FROM Grab_n_Go.restaurant a JOIN Grab_n_Go.order01 b ON a.rest_id
		// = b.rest_id "
		// + " JOIN Grab_n_Go.order_item c on b.ord_id = c.ord_id "
		// + " WHERE b.ord_id = ? ";

		String sql = " SELECT b.m_pickupname, c.item_name, c.prod_id, c.item_note, c.item_amount, c.item_price, b.ord_id "
				+ " FROM order_item c Join order01 b ON c.ord_id = b.ord_id where b.ord_id = ? ";

		try (Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
		) 
		{
			System.out.println("Hello!!");
			// stmt.setString(1, restUsername);
			stmt.setInt(1, ord_id);
			ResultSet rs = stmt.executeQuery();

			if (rs == null) {
				System.out.println("not found");
			}
			while (rs.next()) {
				OrderItemBean oib = new OrderItemBean();
				oib.setM_pickupname(rs.getString("m_pickupname"));
				oib.setItem_name(rs.getString("item_name"));
				oib.setProd_id(rs.getInt("prod_id"));
				oib.setItem_note(rs.getString("item_note"));
				oib.setItem_amount(rs.getInt("item_amount"));
				oib.setItem_price(rs.getInt("item_price"));
				oib.setOrd_id(rs.getInt("ord_id"));
				System.out.println(oib);
				coll.add(oib);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}
	
	public Collection<OrderItemBean> getOrderItemsByDate(){
		Collection<OrderItemBean> coll = new ArrayList();
		String sql = " SELECT b.ord_pickuptime, c.type_name, a.item_name, a.item_amount, a.item_price "
				+ " FROM order_item a JOIN order01 b on a.ord_id = "
				+ " JOIN product c on a.prod_id = c.prod_id"
				+ " WHERE b.ord_pickuptime like '?%' ";
//		SELECT b.ord_pickuptime, c.type_name, a.item_name, a.item_amount, a.item_price 
//		FROM Grab_n_Go.order_item a join Grab_n_Go.order01 b on a.ord_id = b.ord_id
//									join Grab_n_Go.product c on a.prod_id = c.prod_id
//		Where b.ord_pickuptime like '2017-06-01%';
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			System.out.println("Hello, OrderItemDAO");
			stmt.setString(1, ordPickuptime);
			ResultSet rs = stmt.executeQuery();
			if(rs == null){
				System.out.println("not found");
			}
			while(rs.next()){
				OrderItemBean oib = new OrderItemBean();
				oib.setItem_name(rs.getString("item_name"));
				oib.setItem_amount(rs.getInt("item_amount"));
				oib.setItem_price(rs.getInt("item_price"));
				System.out.println(oib);
				coll.add(oib);	
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return coll;
	}
}
