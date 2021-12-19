package com.web.model;

public class Note {
	//主键id
	private int id;
	//用户uuid
	private String userUUid;
	//游记标题
	private String noteTitle;
	//游记内容
	private String noteContent;
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
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
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
		return "Note [id=" + id + ", userUUid=" + userUUid + ", noteTitle=" + noteTitle + ", noteContent=" + noteContent + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + "]";
	}
	
}
