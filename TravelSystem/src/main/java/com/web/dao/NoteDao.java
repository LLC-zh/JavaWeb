package com.web.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.web.model.Note;

public interface NoteDao extends Serializable {
	//查询全部游记
	public List<Note> selectNoteList();
	//查询游记全部相关的
	public List<Map<String, Object>> selectNoteAllList();
	//根据id查询相应的游记
	public Note selectNoteById(int id);
	//插入游记信息
	public int insertNote(Note note);
	//修改游记
	public int updateNote(Note note);
	//删除游记
	public int deleteNote(int id);
}
