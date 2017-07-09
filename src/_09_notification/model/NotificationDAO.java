package _09_notification.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import _00_init.GlobalService;

public class NotificationDAO {
	DataSource ds;
	public Logger log = Logger.getLogger(NotificationDAO.class);
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public NotificationDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int insertNotification(int ordId) {
		int k = -1;
		PreparedStatement pst2 = null;
		String getOrderInfo = "select * from order01 where ord_id = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(getOrderInfo);) {

			try {
				con.setAutoCommit(false);
				pst.setInt(1, ordId);
				ResultSet rs = pst.executeQuery();
				String username = "";
				int rest_id = 0;
				String msg = "您預定的餐點已完成，請盡快來領取喔!";
				Timestamp noti_time = new Timestamp(System.currentTimeMillis());
				while (rs.next()) {
					username = rs.getString("m_username");
					rest_id = rs.getInt("rest_id");
				}

				String sql = "insert into notification values(null,?,?,?,?,?,?)";
				pst2 = con.prepareStatement(sql);
				pst2.setString(1, username);
				pst2.setInt(2, rest_id);
				pst2.setString(3, msg);
				pst2.setInt(4, 0);
				pst2.setTimestamp(5, noti_time);
				pst2.setInt(6, ordId);
				k = pst2.executeUpdate();
				log.info("k = " + k);
				con.commit();
				con.setAutoCommit(true);
				
			} catch (Exception e) {
				if (con != null)
					con.rollback();
				log.info("訊息新增失敗 ");
				e.printStackTrace();
			} finally {
				pst2.close();
			}

			if ( k == 1) {
				log.info("訊息新增成功  ");
			} else
				log.info("訊息新增失敗  ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
	}

	public int insertNotification(NotificationBean nb) {
		int n = -1;
		String sql = "insert into notification values(null,?,?,?,?,?,?)";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nb.getM_username());
			pst.setInt(2, nb.getRest_id());
			pst.setString(3, nb.getMsg());
			pst.setInt(4, nb.getIs_readed());
			pst.setTimestamp(5, nb.getNoti_time());
			pst.setInt(6, nb.getOrd_id());
			n = pst.executeUpdate();

			if (n == 1)
				System.out.println("訊息新增成功  in NotificationDAO");
			else
				System.out.println("訊息新增失敗  in NotificationDAO");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public List<NotificationBean> getQueryNoticationByUser() {
		List<NotificationBean> list = new ArrayList<>();
		String sql = "select * from notification where m_username = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NotificationBean nb = new NotificationBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getByte(5), rs.getTimestamp(6), rs.getInt(7));
				list.add(nb);
			}
			if (list.size() == 0) {
				System.out.println("get Nothing in NotificationDAO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NotificationBean> getQueryNoticationByUserNoRead() {
		List<NotificationBean> list = new ArrayList<>();
		String sql = "select * from notification where m_username = ? and is_readed = 0";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NotificationBean nb = new NotificationBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getByte(5), rs.getTimestamp(6), rs.getInt(7));
				list.add(nb);
			}
			if (list.size() == 0) {
				System.out.println("get Nothing in NotificationDAO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getQueryNoticationCountByUserNoRead() {
		String sql = "select count(*) from notification where m_username = ? and is_readed = 0 and noti_time <= now() " ;
		int count = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
