package com.web.model;

public class Scenic {
	//主键id
	private int id;
	//景点编号
	private String scenicNumber;
	//景点名称
	private String scenicName;
	//景点照片
	private String scenicImage;
	//景点位置
	private String scenicLocation;
	//景点简述
	private String scenicDescribe;
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
	public String getScenicNumber() {
		return scenicNumber;
	}
	public void setScenicNumber(String scenicNumber) {
		this.scenicNumber = scenicNumber;
	}
	public String getScenicName() {
		return scenicName;
	}
	public void setScenicName(String scenicName) {
		this.scenicName = scenicName;
	}
	public String getScenicImage() {
		return scenicImage;
	}
	public void setScenicImage(String scenicImage) {
		this.scenicImage = scenicImage;
	}
	public String getScenicLocation() {
		return scenicLocation;
	}
	public void setScenicLocation(String scenicLocation) {
		this.scenicLocation = scenicLocation;
	}
	public String getScenicDescribe() {
		return scenicDescribe;
	}
	public void setScenicDescribe(String scenicDescribe) {
		this.scenicDescribe = scenicDescribe;
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
		return "Scenic [id=" + id + ", scenicNumber=" + scenicNumber + ", scenicName=" + scenicName + ", scenicImage="
				+ scenicImage + ", scenicLocation=" + scenicLocation + ", scenicDescribe=" + scenicDescribe
				+ ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}

	
}
