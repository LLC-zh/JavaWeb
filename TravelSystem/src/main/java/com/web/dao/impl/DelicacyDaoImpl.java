package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.dao.DelicacyDao;
import com.web.model.Delicacy;
import com.web.util.DateUtil;
import com.web.util.MyBatisUtil;

public class DelicacyDaoImpl implements DelicacyDao {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Delicacy> selectDelicacyList(String delicacyName, int currentPageNo, int pageSize) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			currentPageNo = (currentPageNo -1)*pageSize;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("delicacyName",delicacyName);
			map.put("currentPageNo",currentPageNo);
			map.put("pageSize",pageSize);
			List<Delicacy> delicacyList = session.selectList("com.web.mapper.DelicacyMapper.selectDelicacyList",map);
			session.close();
			return delicacyList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Delicacy selectDelicacyById(int id) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			Delicacy delicacy = session.selectOne("com.web.mapper.DelicacyMapper.selectDelicacyById",id);
			session.close();
			return delicacy;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int selectDelicacyByName(String delicacyName) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.selectOne("com.web.mapper.DelicacyMapper.selectDelicacyByName", delicacyName);
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int insertDelicacy(Delicacy delicacy) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			delicacy.setGmtCreate(DateUtil.getCurDateTime());
			rows = session.insert("com.web.mapper.DelicacyMapper.insertDelicacy",delicacy);
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
	public int updateDelicacy(Delicacy delicacy) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.update("com.web.mapper.DelicacyMapper.updateDelicacy",delicacy);
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
	public int deleteDelicacy(int id) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.delete("com.web.mapper.DelicacyMapper.deleteDelicacy",id);
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
