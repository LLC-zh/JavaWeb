package com.web.model;

public class Play {
	//主键id
	private int id;
	//景点编号
	private String scenicNumber;
	//游玩攻略内容
	private String playIntroduction;
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
	public String getPlayIntroduction() {
		return playIntroduction;
	}
	public void setPlayIntroduction(String playIntroduction) {
		this.playIntroduction = playIntroduction;
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
		return "Play [id=" + id + ", scenicNumber=" + scenicNumber + ", playIntroduction=" + playIntroduction
				+ ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}

}
