package _09_notification.model;

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

public class NotificationDAO {
	DataSource ds;

	public NotificationDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int insertNotification(NotificationBean nb) {
		int n = -1;
		String sql = "insert into notification values(null,?,?,?,?)";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nb.getM_username());
			pst.setInt(2, nb.getRest_id());
			pst.setString(3, nb.getMsg());
			pst.setInt(4, nb.getIs_readed());
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

	public List<NotificationBean> queryNoticationByUser(String username) {
		List<NotificationBean> list = new ArrayList<>();
		String sql = "select * from notification where m_username = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NotificationBean nb = new NotificationBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getByte(5));
				list.add(nb);
			}
			if (list.size()==0) {
				System.out.println("get Nothing in NotificationDAO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NotificationBean> queryNoticationByUserNoRead(String username) {
		List<NotificationBean> list = new ArrayList<>();
		String sql = "select * from notification where m_username = ? and is_readed = 0";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NotificationBean nb = new NotificationBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getByte(5));
				list.add(nb);
			}
			if (list.size()==0) {
				System.out.println("get Nothing in NotificationDAO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int queryNoticationCountByUserNoRead(String username) {
		String sql = "select count(*) from notification where m_username = ? and is_readed = 0";
		int count = 0 ;
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
