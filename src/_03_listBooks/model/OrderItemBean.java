package _03_listBooks.model;
//本類別封裝單筆訂單資料
public class OrderItemBean {
	String title;
	String author;
	String companyName;
	int qty = 0 ; 
	int bookID = 0 ;
	double price = 0 ; 
	double discount = 1 ;
	
	public OrderItemBean(int bookID, int qty, double price, double discount, 
			String title, String author, String companyName) {
		this.bookID = bookID;
		this.qty = qty;
		this.price = price;
		this.discount = discount;
		this.title = title;
		this.author = author;
		this.companyName = companyName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public OrderItemBean() {
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}	
}