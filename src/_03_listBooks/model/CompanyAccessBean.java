package _03_listBooks.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;
// 本類別負責讀取資料庫內eBookCompany表格內的紀錄
// 

public class CompanyAccessBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private DataSource ds = null;
	private String tagName = "";
	//private int companyId = 0;
	private int selected = -1;

	public CompanyAccessBean() throws ServletException, IOException,
			NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	}

	public Collection<CompanyBean> getCompany() throws SQLException {
		String queryPageString = "SELECT id, name  FROM  eBookCompany ";
		Collection<CompanyBean> coll = new ArrayList<CompanyBean>();
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryPageString);
			rs = pStmt.executeQuery();
			// 如果ResultSet內仍有紀錄
			while (rs.next()) {
				// 準備一個新的CompanyBean
				CompanyBean bean = new CompanyBean();
				// 將此紀錄的資料放入CompanyBean
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				// 將CompanyBean物件放入大的容器內
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

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		// System.out.println("In CompanyAccessBean, selected="+selected);
		this.selected = selected;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getSelectTag() throws SQLException {
		String ans = "";
		Collection<CompanyBean> cb = getCompany();
		ans += "<SELECT name='" + getTagName() + "'>";
		for (CompanyBean bean : cb) {
			int id = bean.getId();
			String name = bean.getName().substring(0, 4);
			if (id == selected) {
				ans += "<option value='" + id + "' selected>" + name
						+ "</option>";
			} else {
				ans += "<option value='" + id + "'>" + name + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;
	}
}