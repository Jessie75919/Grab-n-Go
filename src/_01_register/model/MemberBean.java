package _01_register.model;

import java.io.*;
import java.sql.*;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int   pkey;
	String memberId;
	String password;
	String name;
	String address;
	String email;
	String tel;
	String userType;
	int experience;
	Timestamp ts;
	double totalAmt;
	Blob memberImage;
	Clob comment;
	String fileName;
	
	public int getPkey() {
		return pkey;
	}

	public void setPkey(int pkey) {
		this.pkey = pkey;
	}

	public Blob getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(Blob memberImage) {
		this.memberImage = memberImage;
	}

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public MemberBean(int pKey, String memberId, String name, String password, String address,  String mail,
			String tel, String userType ,int experience, Timestamp ts, double totalAmt) {
		super();
		this.pkey = pKey;
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = mail;
		this.tel = tel;
		this.userType = userType;		
		this.experience = experience;
		this.ts = ts;
		this.totalAmt = totalAmt;
	}
	
	public MemberBean(String memberId, String name, String password, String address,  String mail,
			String tel, int expericnce) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = mail;
		this.tel = tel;
		this.experience = expericnce;
	}
	

	public MemberBean() {
		super();
	}	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		email = mail;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String toString() {
		return "userid=" + memberId + "   password="+password;
	}

	public String getUserType() {
		return userType;
	}

	public Timestamp getTs() {
		return ts;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

}
