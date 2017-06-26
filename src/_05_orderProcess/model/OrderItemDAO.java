package _05_orderProcess.model;

import java.sql.Connection;
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
	
	

	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
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
	
}
