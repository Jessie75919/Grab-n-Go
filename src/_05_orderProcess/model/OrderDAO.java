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

public class OrderDAO {

	private DataSource ds;
	private String username;
	
	public void setUsername(String username) {
		this.username = username;
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
				ob.setOrd_time(rs.getDate("ord_time"));
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

}
