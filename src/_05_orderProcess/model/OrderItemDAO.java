package _05_orderProcess.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class OrderItemDAO {
	private DataSource ds;
	private int ord_id;
	private String restUsername;

	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}

	public void setRestUsername(String restUsername) {
		this.restUsername = restUsername;
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

	public Collection<OrderItemBean> getOrdersItemDataForApp(String rest_name) {
		Collection<OrderItemBean> coll = new ArrayList<>();
		OrderItemBean oib = null;
		String sql = "SELECT a.prod_id, a.item_name, a.item_price, a.item_amount "
				+ " FROM order01 b JOIN order_item a ON a.ord_id = b.ord_id "
				+ " JOIN restaurant r ON b.rest_id = r.rest_id " + " WHERE r.rest_name = ? ";
		try (Connection con = ds.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, rest_name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int prod_id = rs.getInt("prod_id");
				String item_name = rs.getString("item_name");
				int item_price = rs.getInt("item_price");
				int item_amount = rs.getInt("item_amount");

				oib = new OrderItemBean(prod_id, item_name, item_price, item_amount);
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
}
