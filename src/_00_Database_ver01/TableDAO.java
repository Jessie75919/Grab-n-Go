package _00_Database_ver01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import _00_init.GlobalService;

public class TableDAO {

	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
	Connection con;
	PreparedStatement pst;
	
	Logger lg = Logger.getLogger(TableDAO.class);
	
	

	/*--------------------------------------------------------------------------
	 * get a connection object.
	 * */
	
	public TableDAO() {
		super();
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
		
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
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
				File f = new File("./");
				lg.info(f.getAbsolutePath());
				
				pst.setBlob(8, is); // m_picture
				pst.setString(9, segment[7]+".jpg"); // m_fileName
				boolean b = false;
				if (segment.equals("1")) {
					b = true ;
				} else if (segment.equals("0")) {
					b = false;
				}
				pst.setBoolean(10, b); // m_validate
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
		String sql = "insert into restaurant values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
				boolean b = false;
				if (segment[15].equals("1")) {
					b = true ;
				} else if (segment[15].equals("0")) {
					b = false;
				}
				pst.setBoolean(16, b); // rest_validate
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
		String sql = "insert into product_type values(?,?,?)";
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/productType.csv"));) {
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] segment = line.split(",");
				
				pst.setInt(1, ++count);  // type_name
				pst.setString(2, segment[0]);  // type_name
				pst.setString(3, segment[1]);  // rest_name
//				System.out.println("ABCCCCC");
				result =  pst.executeUpdate();
				if (result == 1)
					System.out.println(segment[1] +" : " + segment[0]  + " - add success ");
				else
					System.out.println("table gets error");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	// product data
	public int insertProduct(){
		String sql = "insert into product values(null,?,?,?,?,?,?,?)";
		//prod_id, rest_id, type_id, prod_name, prod_price, prod_desc, prod_ing, prod_filename
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/productData.csv"));) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] segment = line.split(",");
				pst.setString(1, segment[0]); // rest_id
				pst.setString(2, segment[1]); // type_id
				pst.setString(3, segment[2]); // prod_name
				Integer price = Integer.parseInt(segment[3]);
				pst.setInt(4, price); // prod_price
				pst.setString(5, segment[4]); // prod_desc
				System.out.println("image : " + segment[6]);
				if(!segment[6].equals("null")){
					InputStream is = new FileInputStream("WebContent/images/productImageTest/" + segment[6]+".jpg");
					pst.setBlob(6, is);
				}
				
				
				 // 
				pst.setString(7, segment[6]); // prod_filename
				
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
	//insert OrderMain 
	public int insertOrder(){
		String sql = "insert into order01 values(null,?,?,?,?,?,?,?,?,?,?)";
		//ord_id, m_username, m_pickupname, ord_time, ord_pickuptime, rest_id, ord_totalPrice, ord_status
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
			BufferedReader br = new BufferedReader(new FileReader("WebContent/data/OrderMain.csv"));) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] segment = line.split(",");
				pst.setString(1, segment[0]); 		// m_username
				pst.setString(2, segment[1]);       // m_pickupname
				java.sql.Timestamp ts1 = java.sql.Timestamp.valueOf(segment[2]);
				pst.setTimestamp(3, ts1);				//ord_Time
				
				java.sql.Timestamp ts2 = java.sql.Timestamp.valueOf(segment[3]);
				if(!ts2.equals("null")){
				pst.setTimestamp(4, ts2);				//ord_pickuptime
				}
				pst.setString(5, segment[4]); 		// rest_id
				Integer totalPrice = Integer.parseInt(segment[5]);
				pst.setInt(6, totalPrice); 			// ord_totalPrice
				pst.setString(7, segment[6]); 		// ord_status 
				pst.setString(8, segment[7]); 		
				pst.setString(9, segment[8]); 		
				pst.setString(10, segment[9]); 		// ord_evalued
				
				result = pst.executeUpdate();

				if (result == 1)
					System.out.println(ts2 + " - add success ");
				else
					System.out.println("table gets error");
			}
		} catch (SQLException | IOException e) {
			System.out.println("SQLException | IOException ");
			e.printStackTrace();
		} 
		return result ;
	}
	//insert OrderItems
	public int insertOrderItems(){
		String sql = "insert into order_item values(null,?,?,?,?,?,?,?)";
		//serial_no, ord_id, prod_id, item_name, item_price, item_amount, item_note, m_username
		int result = -1;
		try (PreparedStatement pst = con.prepareStatement(sql);
				BufferedReader br = new BufferedReader(new FileReader("WebContent/data/orderItem.csv"));) {
				String line = "";
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] segment = line.split(",");
					pst.setString(1, segment[0]); 	// ord_id
					pst.setString(2, segment[1]); 	// prod_id
					pst.setString(3, segment[2]); 	// item_name
					Integer itemPrice = Integer.parseInt(segment[3]);
					pst.setInt(4, itemPrice); 		// item_price
					Integer itemAmount = Integer.parseInt(segment[4]);
					pst.setInt(5, itemAmount); 		// item_amount
					pst.setString(6, segment[5]);	// item_note
					pst.setString(7, segment[6]);   //m_username
					
					result = pst.executeUpdate();

					if (result == 1)
						System.out.println(segment[2] + " - add success ");
					else
						System.out.println("table gets error");
				}
			} catch (SQLException | IOException e) {
				System.out.println("SQLException | IOException ");
				e.printStackTrace();
			} 
		return result;
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
