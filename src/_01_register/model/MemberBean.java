package _01_register.model;

import java.io.*;
import java.sql.*;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String memberId;
	String password;
	String name;
	String phone;
	String email;
	String address;
	Date birthday;
	Blob memberImage;
	String fileName;
	
	public MemberBean() {
		super();
	}

	public MemberBean(String memberId, String password, String name, String phone, String email, String address,
			Date birthday, Blob memberImage, String fileName) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.memberImage = memberImage;
		this.fileName = fileName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Blob getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(Blob memberImage) {
		this.memberImage = memberImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString() {
		return "userid=" + memberId + "   password="+password + " name=" + name;
	}

	

	

}
