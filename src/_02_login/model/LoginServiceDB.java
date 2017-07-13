package _02_login.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import _00_init.*;
import _01_register.model.MemberBean;

public class LoginServiceDB implements LoginServiceDAO {
	static private List<MemberBean> memberList = new ArrayList<MemberBean>();
	private DataSource ds = null;

	public LoginServiceDB() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		if (memberList.isEmpty()) {
			populateMemberList(); // 此方法只會執行一次
		}
	}

	public MemberBean checkPassword(String user, String password) throws SQLException {
		String sql = "SELECT * From member where m_username = ? and m_password = ? ";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		MemberBean mb = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, user);
			pStmt.setString(2, password);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("m_username").trim();
				// 必須確定rs.getString("memberID") not null
				String pswd = rs.getString("m_password").trim();
				String name = rs.getString("m_name").trim();
				String phone = rs.getString("m_phone").trim();
				String email = rs.getString("m_email").trim();
				String addr = rs.getString("m_address").trim();
				Date birthday = rs.getDate("m_birthday");
				Blob userImage = rs.getBlob("m_picture");
				String filename = rs.getString("m_filename").trim();
				int m_validate = rs.getInt("m_validate");
				mb = new MemberBean(id, pswd, name, phone, email, addr, birthday, userImage, filename);
				mb.setM_validate(m_validate);

			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return mb;
	}

	public MemberBean getMemberFromId(String user) throws SQLException {
		String sql = "SELECT * From member where m_username = ?";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		MemberBean mb = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, user);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("m_username").trim();
				// 必須確定rs.getString("memberID") not null
				String pswd = rs.getString("m_password").trim();
				String name = rs.getString("m_name").trim();
				String phone = rs.getString("m_phone").trim();
				String email = rs.getString("m_email").trim();
				String addr = rs.getString("m_address").trim();
				Date birthday = rs.getDate("m_birthday");
				Blob userImage = rs.getBlob("m_picture");
				String filename = rs.getString("m_filename").trim();
				int m_validate = rs.getInt("m_validate");
				mb = new MemberBean(id, pswd, name, phone, email, addr, birthday, userImage, filename);
				mb.setM_validate(m_validate);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return mb;
	}

	// 由Database讀取會員資料
	public void populateMemberList() throws SQLException {
		String sql = "SELECT * From member";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("m_username").trim(); // 必須確定rs.getString("memberID")
				String pswd = rs.getString("m_password").trim();
				String name = rs.getString("m_name").trim();
				String phone = rs.getString("m_phone").trim();
				String email = rs.getString("m_email").trim();
				String addr = rs.getString("m_address").trim();
				Date birthday = rs.getDate("m_birthday");
				Blob userImage = rs.getBlob("m_picture");
				String filename = rs.getString("m_filename").trim();
				int m_validate = rs.getInt("m_validate");
				MemberBean mb = new MemberBean(id, pswd, name, phone, email, addr, birthday, userImage, filename);
				mb.setM_validate(m_validate);
				memberList.add(mb);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (connection != null)
				connection.close();
		}
	}

	public MemberBean checkIDPassword(String userId, String password) {
		// 檢查userId與password是否正確
		for (MemberBean mb : memberList) {
			if (mb.getMemberId().trim().equals(userId.trim())) {
				String encrypedString = GlobalService.encryptString(password.trim());
				String pswd = GlobalService.getMD5Endocing(encrypedString);
				String mbpswd = mb.getPassword().trim();
				if (mbpswd.equals(pswd)) {
					return mb;
				}
			}
		}
		return null;
	}

	public int checkEmail(String Email) {
		String sql = "SELECT m_email From member where m_email = ?";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int n = 0;
		String email = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, Email);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				email = rs.getString("m_email").trim();
			}
			if (email != null)
				n = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return n;
	}
	
	
	public MemberBean getMemberByEmail(String Email) {
		String sql = "SELECT * From member where m_email = ?";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		MemberBean mb = null;
		int n = 0;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, Email);
			rs = pStmt.executeQuery();
			
			
			while(rs.next()){
				String id = rs.getString("m_username").trim(); // 必須確定rs.getString("memberID")
				String pswd = rs.getString("m_password").trim();
				String name = rs.getString("m_name").trim();
				String phone = rs.getString("m_phone").trim();
				String email = rs.getString("m_email").trim();
				String addr = rs.getString("m_address").trim();
				Date birthday = rs.getDate("m_birthday");
				Blob userImage = rs.getBlob("m_picture");
				String filename = rs.getString("m_filename").trim();
				int m_validate = rs.getInt("m_validate");
				mb = new MemberBean(id, pswd, name, phone, email, addr, birthday, userImage, filename);
				mb.setM_validate(m_validate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return mb;
	}

	public List<MemberBean> getMemberList() {
		return memberList;
	}

	public void addNewMember(MemberBean mb) {
		memberList.add(mb);
	}

}
