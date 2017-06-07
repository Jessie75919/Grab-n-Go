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
		String sql = "SELECT * From eMember where memberId = ? and password = ? ";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		MemberBean mb = null;
		try {
			connection = ds.getConnection();
			System.out.println("sql=" + sql);
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1,  user);
			pStmt.setString(2,  password);
			rs = pStmt.executeQuery();
			
			if (rs.next()) {
				int pKey = rs.getInt("seqNo");
				String id = rs.getString("memberID").trim(); // 必須確定
																// rs.getString("memberID")
																// 不是null才能
																// .trim()
				String name = rs.getString("name").trim();
				String pswd = rs.getString("password").trim();
				String addr = rs.getString("address");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				String userType = rs.getString("userType");
				int exp = rs.getInt("experience");
				java.sql.Timestamp ts = rs.getTimestamp("register");
				double totalAmt = rs.getDouble("totalAmt");
				mb = new MemberBean(pKey, id, name, pswd, addr,
						email, tel, userType, exp, ts, totalAmt);
				
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
	public void populateMemberList() throws SQLException {
		// 由Database讀取會員資料
		String sql = "SELECT * From eMember";
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				int pKey = rs.getInt("seqNo");
				String id = rs.getString("memberID").trim(); // 必須確定
																// rs.getString("memberID")
																// 不是null才能
																// .trim()
				String name = rs.getString("name").trim();
				String pswd = rs.getString("password").trim();
				String addr = rs.getString("address");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				String userType = rs.getString("userType");
				int exp = rs.getInt("experience");
				java.sql.Timestamp ts = rs.getTimestamp("register");
				double totalAmt = rs.getDouble("totalAmt");
				MemberBean mb = new MemberBean(pKey, id, name, pswd, addr,
						email, tel, userType, exp, ts, totalAmt);
				memberList.add(mb);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
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

	public List<MemberBean> getMemberList() {
		return memberList;
	}
	public void addNewMember(MemberBean mb) {
		memberList.add(mb);
	}
	
}
