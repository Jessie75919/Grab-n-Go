package _04_ShoppingCart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.*;
// 本類別
//   1.新增一筆訂單到orders表格
//   2.查詢orders表格內的單筆訂單
//   3.查詢orders表格內的所有訂單

public class OrderDAO {
	private static final long serialVersionUID = 1L;
	private String memberId = null;
	private DataSource ds = null;

	public OrderDAO() throws NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	}

	public void insertOrder(OrderBean ob) throws SQLException {
		String sqlOrder = "Insert Into orders "
				+ " (userID, totalAmount, shippingAddress, BNO, InvoiceTitle, orderDate) "
				+ " values(?, ?, ?, ?, ?, ?) ";
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet generatedKeys = null;
		PreparedStatement pStmt2 = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);  // 開啟JDBC Transaction
			pStmt = conn.prepareStatement(sqlOrder,
					Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, ob.getUserId());
			pStmt.setDouble(2, ob.getTotalAmount());
			pStmt.setString(3, ob.getShippingAddress());
			pStmt.setString(4, ob.getBno());
			pStmt.setString(5, ob.getInvoiceTitle());
			Timestamp ts = new Timestamp(ob.getOrderDate().getTime());
			pStmt.setTimestamp(6, ts);
			pStmt.executeUpdate();
			int id = 0;
			generatedKeys = pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new SQLException(
						"Creating user failed, no generated key obtained.");
			}
			String sqlItem = "Insert Into OrderItems (orderNo, bookID, description, amount, unitPrice, discount) "
					+ " values(?, ?, ?, ?, ?, ?) ";
			List<OrderItemDAOBean> items = ob.getItems();
			pStmt2 = conn.prepareStatement(sqlItem);
			int n=0;
			for (OrderItemDAOBean oib : items) {
//              下列四個敘述為交易測試而編寫	
//				n++;
//				if (n > 1) {
//					System.out.println("發生例外 n>2");
//					throw new SQLException("JDBC交易測試用");
//				}
				pStmt2.setInt(1, id);
				pStmt2.setInt(2, oib.getBookId());
				pStmt2.setString(3, oib.getDescription());
				pStmt2.setDouble(4, oib.getAmount());
				pStmt2.setDouble(5, oib.getUnitPrice());
				pStmt2.setDouble(6, oib.getDiscount());
				int count = pStmt2.executeUpdate();
				pStmt2.clearParameters();
			}
			conn.commit();  
		} catch(SQLException ex){
			System.out.println("資料還原");
			if(conn!=null) conn.rollback();
		} finally {
			
			if (pStmt != null) {
				pStmt.close();
			}
			if (pStmt2 != null) {
				pStmt2.close();
			}
			if(conn!=null)  conn.setAutoCommit(true);
			if (conn != null) {
				conn.close();
			}
		}
	}

	public OrderBean getOrder(int orderNo) throws SQLException {
		String sqlOrder = "SELECT * FROM orders WHERE orderNo = ? ";
		String sqlOrderItems = "SELECT * FROM orderItems WHERE orderNo = ? order by seqNo";
		List<OrderItemDAOBean> items = new ArrayList<OrderItemDAOBean>();
		Connection conn = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		OrderBean ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			pStmt.setInt(1, orderNo);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				ob = new OrderBean();
				ob.setOrderNo(rs.getInt(1));
				ob.setUserId(rs.getString(2));
				ob.setTotalAmount(rs.getDouble(3));
				ob.setShippingAddress(rs.getString(4));
				ob.setBno(rs.getString(5));
				ob.setInvoiceTitle(rs.getString(6));
				ob.setOrderDate(rs.getDate(7));
				ob.setShippingDate(rs.getDate(8));
			}
			if (ob == null) {
				throw new SQLException("資料庫邏輯錯誤：無此紀錄, 訂單編號=" + orderNo);
			} else {
				pStmt2 = conn.prepareStatement(sqlOrderItems);
				pStmt2.setInt(1, orderNo);
				rs2 = pStmt2.executeQuery();
				OrderItemDAOBean oib = null;
				while (rs2.next()) {
					oib = new OrderItemDAOBean();
					oib.setSeqno(rs2.getInt(1));
					oib.setOrderNo(rs2.getInt(2));
					oib.setBookId(rs2.getInt(3));
					oib.setDescription(rs2.getString(4));
					oib.setAmount(rs2.getInt(5));
					oib.setUnitPrice(rs2.getDouble(6));
					oib.setDiscount(rs2.getDouble(7));
					items.add(oib);
				}
			}
			ob.setItems(items);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (pStmt2 != null) {
				pStmt2.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return ob;
	}

	public Collection<OrderBean> getAllOrders() throws SQLException {
		Collection<OrderBean> coll = new ArrayList<OrderBean>();
		String sqlOrder = "SELECT * FROM orders Order by orderDate desc, orderNo desc ";
		PreparedStatement pStmt = null;
		Connection conn = null;
		ResultSet rs = null;
		OrderBean ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				ob = new OrderBean();
				ob.setOrderNo(rs.getInt(1));
				ob.setUserId(rs.getString(2));
				ob.setTotalAmount(rs.getDouble(3));
				ob.setShippingAddress(rs.getString(4));
				ob.setBno(rs.getString(5));
				ob.setInvoiceTitle(rs.getString(6));
				ob.setOrderDate(rs.getDate(7));
				ob.setShippingDate(rs.getDate(8));
				coll.add(ob);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		//System.out.println("OrderDAO   coll.size()=" + coll.size());
		return coll;
	}
	
	public Collection<OrderBean> getMemberOrders() throws SQLException {
		Collection<OrderBean> coll = new ArrayList<OrderBean>();
		String sqlOrder = "SELECT * FROM orders Order by orderDate desc where userId = ?";
		PreparedStatement pStmt = null;
		Connection conn = null;
		ResultSet rs = null;
		OrderBean ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			pStmt.setString(1, memberId);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				ob = new OrderBean();
				ob.setUserId(rs.getString(2));
				ob.setTotalAmount(rs.getDouble(3));
				ob.setShippingAddress(rs.getString(4));
				ob.setBno(rs.getString(5));
				ob.setInvoiceTitle(rs.getString(6));
				ob.setOrderDate(rs.getDate(7));
				ob.setShippingDate(rs.getDate(8));
				coll.add(ob);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return coll;
	}
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}