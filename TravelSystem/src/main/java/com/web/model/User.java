package com.web.model;

public class User {
	//主键id
	private int id;
	//用户uuid
	private String userUUid;
	//用户名
	private String userName;
	//用户密码
	private String userPassword;
	//权限标志(0:普通用户,1:管理员)
	private int isAdmin;
	//创建时间
	private String gmtCreate;
	//修改时间
	private String gmtModified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserUUid() {
		return userUUid;
	}
	public void setUserUUid(String userUUid) {
		this.userUUid = userUUid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userUUid=" + userUUid + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", isAdmin=" + isAdmin + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
	
}
