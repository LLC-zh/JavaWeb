package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.dao.ScenicDao;
import com.web.model.Scenic;
import com.web.util.DateUtil;
import com.web.util.MyBatisUtil;

public class ScenicDaoImpl implements ScenicDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Scenic> scenicList(String scenicLocation, int currentPageNo, int pageSize) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			currentPageNo = (currentPageNo -1) * pageSize;
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("scenicLocation", scenicLocation);
			map.put("currentPageNo",currentPageNo);
			map.put("pageSize",pageSize);
			List<Scenic> list = session.selectList("com.web.mapper.ScenicMapper.selectScenic",map);
			session.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int selectScenicCountByLocation(String scenicLocation) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.selectOne("com.web.mapper.ScenicMapper.selectScenicCountByLocation", scenicLocation);
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public Scenic selectScenicById(int id) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			Scenic scenic = session.selectOne("com.web.mapper.ScenicMapper.selectScenicById", id);
			session.close();
			return scenic;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> selectScenicAll() {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			List<Map<String, Object>> list = session.selectList("com.web.mapper.ScenicMapper.selectScenicAll");
			session.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Map<String, Object>> selectScenicNumber(String scenicLocation){
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			List<Map<String, Object>> list = session.selectList("com.web.mapper.ScenicMapper.selectScenicNumber", scenicLocation);
			session.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertScenic(Scenic scenic) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			//Date date = new Date(new java.util.Date().getTime());
			scenic.setGmtCreate(DateUtil.getCurDateTime());
			rows = session.insert("com.web.mapper.ScenicMapper.insertScenic", scenic);
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
	public int updateScenic(Scenic scenic) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			//Date date = new Date(new java.util.Date().getTime());
			scenic.setGmtModified(DateUtil.getCurDateTime());
			rows = session.update("com.web.mapper.ScenicMapper.updateScenic",scenic);
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
	public int deleteScenic(int id) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.delete("com.web.mapper.ScenicMapper.deleteScenic", id);
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
	public List<Map<String, Object>> selectScenicCount() {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			List<Map<String, Object>> list = session.selectList("com.web.mapper.ScenicMapper.selectScenicCount");
			session.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
