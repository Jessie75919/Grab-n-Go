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

	
	
	/*--------------------------------------------------------------------------
	 * get a connection object.
	 * */
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
	
	/*--------------------------------------------------------------------------
	 *   inflater member data
	 * */
	public int insertMemberTable() {
		getDataSource();
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
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
				String encrypedString = GlobalService.encryptString(segment[1]); // m_password with encryption
				pst.setString(2, GlobalService.getMD5Endocing(encrypedString));  // all passwords are "1111"
				
				System.out.println("encrypedString : " + encrypedString);   
				System.out.println("GlobalService.getMD5Endocing  : "
						+ GlobalService.getMD5Endocing(encrypedString));  // show the result of encrypting 

				pst.setString(3, segment[2]); // m_name
				pst.setString(4, segment[3]); // m_phone
				pst.setString(5, segment[4]); // m_email
				pst.setString(6, segment[5]); // m_address
				pst.setDate(7, Date.valueOf(segment[6])); // m_birthday
				System.out.println("image : " + segment[7]);
				InputStream is = new FileInputStream("WebContent/images/userImage/" + segment[7] + ".jpg");
				pst.setBlob(8, is); // m_picture
				pst.setString(9, segment[7]+".jpg"); // m_fileName
				result = pst.executeUpdate();

				if (result == 1)
					System.out.println(segment[0] + " - add success ");
				else
					System.out.println("table gets error");
			}
		} catch (SQLException | IOException e) {
			System.out.println("SQLException | IOException ");
			e.printStackTrace();
		}
		return result;
	}
	
	
	

	
	public int insertRestaurantType(){
		getDataSource();
		String sql = "insert into rest_type values(?)";
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/restaurantType.csv"));) {
			String line = "";
			
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				
				String[] segment = line.split(",");
				pst.setString(1, segment[0]); // rest_type
				
				result =  pst.executeUpdate();
				if (result == 1)
					System.out.println(segment[0] + " - add success ");
				else
					System.out.println("table gets error");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	
	
	
	/*--------------------------------------------------------------------------
	 *  inflater restaurant data
	 * */
	public int insertRestaurantTable() {
		getDataSource();
		String sql = "insert into restaurant values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/restaurantData.csv"));) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] segment = line.split(",");
				pst.setString(1, segment[0]); // rest_type
				pst.setString(2, segment[1]); // rest_name
				pst.setString(3, segment[2]); // rest_branch
				pst.setString(4, segment[3]); // rest_address
				pst.setString(5, segment[4]); // rest_phone
				pst.setString(6, segment[5]); // rest_owner
				pst.setString(7, segment[6]); // rest_email
				pst.setString(8, segment[7]); // rest_username
				String encrypedString = GlobalService.encryptString(segment[8]); // rest_passward  with encryption
				pst.setString(9, GlobalService.getMD5Endocing(encrypedString)); // rest_passward
				pst.setString(10, segment[9]); // rest_url
				pst.setFloat(11, Float.parseFloat(segment[10])); // rest_longitude
				pst.setFloat(12, Float.parseFloat(segment[11])); // rest_latitude
				System.out.println("image : " + segment[12]);
				InputStream is = new FileInputStream("WebContent/images/restImage/" + segment[12] + ".jpg");
				pst.setBlob(13, is); // rest_mainbanner
				is = new FileInputStream("WebContent/images/restImage/" + segment[13] + ".jpg");
				pst.setBlob(14, is); // rest_logo
				is = new FileInputStream("WebContent/images/restImage/" + segment[14] + ".jpg");
				pst.setBlob(15, is); // rest_coverimage
				result = pst.executeUpdate();

				if (result == 1)
					System.out.println(segment[1] + " - add success ");
				else
					System.out.println("table gets error");
			}
		} catch (SQLException | IOException e) {
			System.out.println("SQLException | IOException ");
			e.printStackTrace();
		}
		return result;
	}

	/*--------------------------------------------------------------------------
	 *  inflate productType data
	 * */
	public void insertProductType(){
		getDataSource();
		String sql = "insert into product_type values(?,?,?)";
		int result = -1;
		String store = "";
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/productType.csv"));) {
			String line = "";
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] segment = line.split(",");
				
				if(store.equals(segment[0])){
				}else{
					store = segment[0];
					i = 0;
				}
				pst.setString(1, segment[0]); // rest_name
				pst.setInt(2,  ++i ); // no
				pst.setString(3, segment[1]); // type_name
				
				result =  pst.executeUpdate();
				if (result == 1)
					System.out.println(segment[1] + " - add success ");
				else
					System.out.println("table gets error");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	

	
	
	
	/* 
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
	*/

}
