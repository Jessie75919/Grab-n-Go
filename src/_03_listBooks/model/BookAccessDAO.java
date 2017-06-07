package _03_listBooks.model;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

public interface BookAccessDAO {
	public int getTotalPages() throws SQLException ;
	public Collection<BookBean> getPageBooks() throws SQLException ;
	public int getPageNo();
	public void setPageNo(int pageNo);
	public int getRecordsPerPage() ;
	public void setRecordsPerPage(int recordsPerPage);
	public int getRecordCounts() throws SQLException;
	public void setBookId(int bookId);
	public BookBean getBook() throws SQLException;
	public Collection<BookBean> getAllBooks() throws SQLException;
	public int updateBook(BookBean bean, InputStream is, long sizeInBytes) throws SQLException;
	public int updateBook(BookBean bean) throws SQLException;
	public BookBean queryBook(int bookID) throws SQLException;
	public int deleteBook(int no) throws SQLException;
	public int insertBook(BookBean bean, InputStream is, long size) throws SQLException;
	public void createBookTable() throws SQLException;
}
