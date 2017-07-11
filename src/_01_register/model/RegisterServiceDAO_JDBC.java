package _01_register.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import org.apache.log4j.Logger;

import _00_init.*;
import _02_login.model.*;

public class RegisterServiceDAO_JDBC implements RegisterServiceDAO {
	Logger lg = Logger.getLogger(ValidateServlet.class);
	
	private List<MemberBean> memberList;
	LoginServiceDB lsdb;
	private DataSource ds = null;

	public RegisterServiceDAO_JDBC() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
			lsdb = new LoginServiceDB();
			memberList = lsdb.getMemberList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public boolean idExists(String id) throws IOException {
		boolean exist = false; // 檢查id是否已經存在
		for (MemberBean mb : memberList) {
			if (mb.getMemberId().equals(id.trim())) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	synchronized public int saveMember(MemberBean mb, InputStream is,
			long size, String filename) throws SQLException {
		PreparedStatement pstmt1 = null;
		Connection conn = ds.getConnection();
		int r = 0;
		try {
			String sql1 = "insert into Member "
					+ " (m_username, m_password, m_name, m_phone, m_email, m_address, m_birthday, "
					+ " m_picture, m_fileName,m_validate) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, mb.getMemberId());
			
			String encrypedString = GlobalService.encryptString(mb.getPassword());
			pstmt1.setString(2, GlobalService.getMD5Endocing(encrypedString));
			pstmt1.setString(3, mb.getName());
			pstmt1.setString(4, mb.getPhone());
			pstmt1.setString(5, mb.getEmail());
			pstmt1.setString(6, mb.getAddress());
			pstmt1.setDate(7, mb.getBirthday());
//			java.sql.Timestamp now = new java.sql.Timestamp(
//					System.currentTimeMillis());
//			pstmt1.setTimestamp(9, now);
//			pstmt1.setDouble(10, mb.totalAmt);
			// 設定Image欄位
			// pstmt1.setBlob(11, is, size); // 此方法目前未支援
			pstmt1.setBinaryStream(8, is, size);
			pstmt1.setString(9, filename);
			pstmt1.setInt(10, 0);
			r = pstmt1.executeUpdate();
			if (r == 1) {
				// 寫入成功，應該將MemberBean mem立即加入LoginService的memberList內
				// 否則，最新的User將無法登入
				mb.setPassword(GlobalService.getMD5Endocing(encrypedString));
				memberList.add(mb);
			} else {
				throw new SQLException("RegisterServiceDB:新增記錄數 : 0");
			}
			// System.out.println("新增一筆eMember紀錄，是否成功=" + r);
		} finally {
			// 關閉相關的物件
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("關閉相關物件時發生例外: " + e);
				}
			}
		}
		return r;
	}
	
	public int validate(String username,int mode){
		int n =-1;
		String sql ="";
		if(mode==1){
			 sql = "update Member set m_validate = 1 where m_username = ?";
		}else if(mode==2){
			 sql = "update Member set rest_validate = 1 where rest_id = ?";
		}
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				){
			
			// 使用者
			if (mode==1) {
				pstmt1.setString(1, username);
				n = pstmt1.executeUpdate();
				if (n == 1) {
					lg.info("驗證成功");
				} else {
					lg.error("驗證失敗");
				} 
			// 商家
			}else if(mode==2){
				pstmt1.setString(1, username);
				n = pstmt1.executeUpdate();
				if (n == 1) {
					lg.info("驗證成功");
				} else {
					lg.error("驗證失敗");
				} 
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}
}
