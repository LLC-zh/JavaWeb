package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.dao.NoteDao;
import com.web.model.Note;
import com.web.util.DateUtil;
import com.web.util.MyBatisUtil;

public class NoteDaoImpl implements NoteDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Note> selectNoteList() {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			List<Note> list = session.selectList("com.web.mapper.NoteMapper.selectNote");
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> selectNoteAllList() {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			List<Map<String, Object>> list = session.selectList("com.web.mapper.NoteMapper.selectNoteAll");
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Note selectNoteById(int id) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			Note note = session.selectOne("com.web.mapper.NoteMapper.selectNoteById", id);
			return note;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertNote(Note note) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			note.setGmtCreate(DateUtil.getCurDateTime());
			rows = session.insert("com.web.mapper.NoteMapper.insertNote", note);
			session.commit();
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateNote(Note note) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			note.setGmtModified(DateUtil.getCurDateTime());
			rows = session.update("com.web.mapper.NoteMapper.updateNote", note);
			session.commit();
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteNote(int id) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.delete("com.web.mapper.NoteMapper.deleteNote", id);
			session.commit();
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

}
