package _00_Database_ver01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class TableDAO {

	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
	Connection con;
	PreparedStatement pst;

	private void getDataSource() {
		System.out.println("URL: " + GlobalService.DB_URLMySQL);
		System.out.println("帳號: " + GlobalService.USERID);
		System.out.println("密碼: " + GlobalService.PASSWORD);
		try {
			con = DriverManager.getConnection(GlobalService.DB_URLMySQL, GlobalService.USERID, GlobalService.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException gets error");
			e.printStackTrace();
		}
	}

	public int insertMember() {
		getDataSource();
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/memberData.csv"));) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] segment = line.split(",");
				pst.setString(1, segment[0]); // m_username
				pst.setString(2, segment[1]); // m_password
				pst.setString(3, segment[2]); // m_name
				pst.setString(4, segment[3]); // m_phone
				pst.setString(5, segment[4]); // m_email
				pst.setString(6, segment[5]); // m_address
				pst.setDate(7, Date.valueOf(segment[6])); // m_birthday
				System.out.println("image : " + segment[7]);
				InputStream is = new FileInputStream("WebContent/images/userImage/" + segment[7] + ".jpg");
				pst.setBlob(8, is); // m_picture
				result = pst.executeUpdate();

				if (result == 1)
					System.out.println(segment[0] + " - 新增成功 ");
				else
					System.out.println("表格新增失敗");
			}
		} catch (SQLException | IOException e) {
			System.out.println("SQLException | IOException ");
			e.printStackTrace();
		}
		return result;
	}

	private String[] readSqlFiles(String sqlFileName) {

		try (FileInputStream fis = new FileInputStream(sqlFileName);
				InputStreamReader isr = new InputStreamReader(fis, "UFT-8");
				BufferedReader br = new BufferedReader(isr);) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
