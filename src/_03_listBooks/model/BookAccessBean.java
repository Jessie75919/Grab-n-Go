package _03_listBooks.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.sql.*;

import _00_init.*;
//1.閱讀本範例中，位於insertBook()註解內的錯誤寫法
//
//2.本類別負責讀取資料庫內eBook表格內某一頁的紀錄，並能新增紀錄、修改紀錄、刪除記錄等
//  當pageNo=1時，讀取 1, 2, 3筆記錄
//  當pageNo=2時，讀取 4, 5, 6筆記錄
//  當pageNo=3時，讀取 7, 8, 9筆記錄

public class BookAccessBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private DataSource ds = null;
	private int bookId = 0; // 查詢單筆商品會用到此代號
	private int pageNo = 0;
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 每頁三筆
	private int totalPages = -1;

	public BookAccessBean() throws ServletException, IOException,
			NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	}

	public int getTotalPages() throws SQLException {
		// 計算總共有幾頁
		if (totalPages == -1) {
			// 注意下一列的double型態轉換
			totalPages = (int) (Math.ceil(getRecordCounts()
					/ (double) recordsPerPage));
		}
		return totalPages;
	}

	public Collection<BookBean> getPageBooks() throws SQLException {
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		Collection<BookBean> coll = new ArrayList<BookBean>();
		try {
			String queryPageString = "SELECT  "
					+ " b.BOOKID, b.TITLE, b.author, b.PRICE, b.discount, b.companyID, b.fileName, b.bookNo, bc.name FROM eBook b JOIN eBookCompany bc ON  b.companyID = bc.id "
					+ " limit ?, ?";
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryPageString);
			int startRecordNo = (pageNo - 1) * recordsPerPage ;
			// int recordsPerPage = (pageNo) * recordsPerPage;
			// PreparedStatement物件內所有的問號都要有值，否則執行pStmt.executeQuery()
			// 或pStmt.executeUpdate()時程式一定會掛掉。
			System.out.println("queryPageString==>" + queryPageString);
			pStmt.setInt(1, startRecordNo);
			pStmt.setInt(2, recordsPerPage);
			rs = pStmt.executeQuery();
//			+ " b.BOOKID, b.TITLE, b.author, b.PRICE, b.discount, b.companyID, b.fileName, b.bookNo, bc.name FROM eBook b JOIN eBookCompany bc ON  b.companyID = bc.id "
//			+ " limit ?, ?";
			// 如果ResultSet內含有未讀取的紀錄
			while (rs.next()) {
				// 建立一個新的BookBean物件
				BookBean bean = new BookBean();
				// 將此紀錄內的資料放入BookBean物件
				bean.setBookId(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setPrice(rs.getDouble(4));
				bean.setDiscount(rs.getDouble(5));
				bean.setCompanyId(rs.getInt(6));
				bean.setFileName(rs.getString(7));
				bean.setBookNo(rs.getString(8));
				bean.setCompanyName(rs.getString(9));
				// 最後將BookBean物件放入大的容器內
				coll.add(bean);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return coll;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	// 計算紀錄總筆數
	public int getRecordCounts() throws SQLException {
		String sql = "SELECT count(*) FROM eBook";
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			if (rs != null) {
				try {
				   rs.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public BookBean getBook() throws SQLException {
		BookBean bean = null;
		String queryBookString = "SELECT b.*, bc.name FROM eBook b JOIN eBookCompany  "
				+ "bc  ON b.companyID = bc.id  where bookID = ?";
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryBookString);
			pStmt.setInt(1, bookId);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				bean = new BookBean();
				bean.setBookId(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setPrice(rs.getDouble(4));
				bean.setDiscount(rs.getDouble(5));
				bean.setCompanyId(rs.getInt(6));
				bean.setFileName(rs.getString(7));
				bean.setBookNo(rs.getString(8));
				bean.setCompanyName(rs.getString(9));
			}
		} finally {
			if (rs != null) {
				try {
				   rs.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return bean;

	}

	public Collection<BookBean> getAllBooks() throws SQLException {
		Collection<BookBean> coll = new ArrayList<BookBean>();
		PreparedStatement pStmt = null;
		String queryAllBooksString = "SELECT b.*, bc.name FROM eBook b JOIN eBookCompany  bc  ON b.companyID = bc.id ORDER BY b.bookID";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryAllBooksString);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBookId(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setPrice(rs.getDouble(4));
				bean.setDiscount(rs.getDouble(5));
				bean.setCompanyId(rs.getInt(6));
				bean.setFileName(rs.getString(7));
				bean.setBookNo(rs.getString(8));
				bean.setCompanyName(rs.getString(9));
				coll.add(bean);
			}
		} finally {
			if (rs != null) {
				try {
				   rs.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return coll;
	}

	public int updateBook(BookBean bean, InputStream is, long sizeInBytes) throws SQLException {
		int n = 0;
		PreparedStatement pStmt = null;
		Connection connection = null;
		String updateString = 
		"UPDATE eBook SET title=?,  author=?,  price=?, companyID=?, "
		+ " fileName=?, bookNo=?, CoverImage = ? WHERE bookID = ?";
		if (sizeInBytes == -1) { // 不修改圖片
			n = updateBook(bean);
			return n;
		}
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(updateString);
			pStmt.clearParameters();
			pStmt.setString(1, bean.getTitle());
			pStmt.setString(2, bean.getAuthor());
			pStmt.setDouble(3, bean.getPrice());
			pStmt.setInt(4, bean.getCompanyId());
			pStmt.setString(5, bean.getFileName());
			pStmt.setString(6, bean.getBookNo());
			pStmt.setBinaryStream(7, is, sizeInBytes);
			pStmt.setInt(8, bean.getBookId());
			n = pStmt.executeUpdate();
		} finally {
			
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	// 修改一筆記錄
	public int updateBook(BookBean bean) throws SQLException {
		int n = 0;
		String updateString = "UPDATE eBook SET title=?,  author=?,  price=?, companyID=?,  bookNo=? WHERE bookID = ?";
		PreparedStatement pStmt = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(updateString);
			pStmt.clearParameters();
			pStmt.setString(1, bean.getTitle());
			pStmt.setString(2, bean.getAuthor());
			pStmt.setDouble(3, bean.getPrice());
			pStmt.setInt(4, bean.getCompanyId());
			pStmt.setString(5, bean.getBookNo());
			pStmt.setInt(6, bean.getBookId());
			n = pStmt.executeUpdate();
		} finally {
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	// 依bookID來查詢單筆記錄
	public BookBean queryBook(int bookID) throws SQLException {
		BookBean bean = null;
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM eBook WHERE bookID = ?";
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryString);
			pStmt.setInt(1, bookID);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				bean = new BookBean();
				bean.setBookId(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setPrice(rs.getDouble(4));
				bean.setDiscount(rs.getDouble(5));
				bean.setCompanyId(rs.getInt(6));
				bean.setFileName(rs.getString(7));
				bean.setBookNo(rs.getString(8));
			}
		} finally {
			if (rs != null) {
				try {
				   rs.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	// 依bookID來刪除單筆記錄
	public int deleteBook(int no) throws SQLException {
		int n = 0;

		PreparedStatement pStmt = null;
		Connection connection = null;
		String deleteString = "DELETE FROM eBook WHERE bookID = ?";
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(deleteString);
			pStmt.setInt(1, no);
			n = pStmt.executeUpdate();
		} finally {
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	// 新增一筆記錄
	public int insertBook(BookBean bean, InputStream is, long size) 
	                                   throws SQLException {
		int n = 0;
		Connection connection = null;
		PreparedStatement pStmt = null;
		try {
			String inserteBook = "insert into eBook "
					+ " (title,  author,  price, discount, "
					+ " companyID,  fileName, bookNo, CoverImage) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			connection = ds.getConnection();
			pStmt = connection.prepareStatement(inserteBook);
			pStmt.setString(1, bean.getTitle());
			pStmt.setString(2, bean.getAuthor());
			pStmt.setDouble(3, bean.getPrice());
			pStmt.setDouble(4, bean.getDiscount());
			pStmt.setInt(5, bean.getCompanyId());
			pStmt.setString(6,  bean.getFileName());
			pStmt.setString(7, bean.getBookNo());
			pStmt.setBinaryStream(8, is, size);
			n = pStmt.executeUpdate();
		} finally {
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch(SQLException e){
				   e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	public void createBookTable() throws SQLException {
		String dropString = "Drop Table eBook ";
		String createString = "Create Table eBook "
				+ "(bookID int NOT NULL IDENTITY Primary Key , "
				+ " title			varchar(50), " + " author    	varchar(28), "
				+ " price			money, " + " discount		money, "
				+ " companyID		int, " + " image  		varchar(20), "
				+ " bookNo 		varchar(20), " + " CoverImage   	image " + " ) ";
		PreparedStatement pStmt;
		Connection connection = ds.getConnection();
		try {
			pStmt = connection.prepareStatement(dropString);
			pStmt.executeUpdate();
			System.out.println("表格 eBook 刪除成功");
		} catch (SQLException e) {
			System.err.println("表格 eBook 刪除失敗:" + e.getMessage());
		}
		try {
			pStmt = connection.prepareStatement(createString);
			pStmt.executeUpdate();
			System.out.println("表格 eBook 新建成功");
		} catch (SQLException e) {
			System.err.println("表格 eBook 新建失敗:" + e.getMessage());
		}
		if (connection != null)
			connection.close();
		return;
	}
}