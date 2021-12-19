package com.web.model;

public class Delicacy {

	//主键id
	private int id;
	//美食名称
	private String delicacyName;
	//美食价格
	private String delicacyPrice;
	//美食介绍
	private String delicacyIntroduction;
	//美食地址
	private String delicacyImage;
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
	public String getDelicacyName() {
		return delicacyName;
	}
	public void setDelicacyName(String delicacyName) {
		this.delicacyName = delicacyName;
	}
	public String getDelicacyPrice() {
		return delicacyPrice;
	}
	public void setDelicacyPrice(String delicacyPrice) {
		this.delicacyPrice = delicacyPrice;
	}
	public String getDelicacyIntroduction() {
		return delicacyIntroduction;
	}
	public void setDelicacyIntroduction(String delicacyIntroduction) {
		this.delicacyIntroduction = delicacyIntroduction;
	}
	public String getDelicacyImage() {
		return delicacyImage;
	}
	public void setDelicacyImage(String delicacyImage) {
		this.delicacyImage = delicacyImage;
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
		return "Delicacy [id=" + id + ", delicacyName=" + delicacyName + ", delicacyPrice=" + delicacyPrice
				+ ", delicacyIntroduction=" + delicacyIntroduction + ", delicacyImage=" + delicacyImage + ", gmtCreate="
				+ gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
